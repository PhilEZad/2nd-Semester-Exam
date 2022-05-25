package Application.DAL;

import Application.BE.Group;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO implements IDatabaseActions<Group>
{

    @Override
    public Group create(Group input) {
        String sql = """
                    INSERT INTO [Group] (groupName, FK_Citizen)
                    VALUES (?, ?)
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, input.getGroupName());
            pstmt.setInt(2, input.getCitizen().getId());

            pstmt.executeBatch();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();

            return new Group(
                    id,
                    input.getGroupMembers(),
                    input.getGroupName()
            );

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void update(Group input)
    {
        String sql =
                """
                UPDATE [Group]
                SET groupName = ?, FK_Citizen = ?
                WHERE GID = ?
                """;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, input.getGroupName());
            pstm.setInt(2, input.getCitizen().getId());
            pstm.setInt(3, input.getId());

            pstm.execute();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Group read(int id) throws SQLException
    {
        return null;
    }

    @Override
    public List<Group> readAll()
    {
        List<Group> returnList = new ArrayList<>();
        String sql = """
                SELECT * FROM [Group]
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {

            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next())
            {

                returnList.add(new Group(
                        resultSet.getInt("GID"),
                        null,
                        resultSet.getString("groupName")
                ));
            }
            return returnList;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return returnList;
        }
    }

    @Override
    public void delete(int id)
    {
        String sql = """
                DELETE FORM  AccountGroup
                IF EXIST (SELECT * FROM AccountGroup WHERE GID = ?
                DELETE FROM [Group]
                WHERE GID = ?
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, id);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
