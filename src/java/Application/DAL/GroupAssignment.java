package Application.DAL;

import Application.BE.Account;
import Application.BE.Group;
import Application.BE.Location;
import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupAssignment
{
    public void create (Group group) throws SQLException
    {
        String sql = """
                INSERT INTO AccountGroup (FK_GroupID, FK_MemberID)
                VALUES (?, ?)
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();

        PreparedStatement ptsm = conn.prepareStatement(sql);

        for (Account account : group.getGroupMembers())
        {
            ptsm.setInt(1, account.getId());
            ptsm.setInt(2, group.getId());
            ptsm.addBatch();

        }
        ptsm.executeBatch();
    }

    public Group read(int group) throws SQLException
    {
        String sql = """
                SELECT * FROM AccountGroup
                JOIN Account ON AccountGroup.FK_MemberID = Account.AID
                JOIN School ON Account.FK_AccountSchool = School.SID
                JOIN Zipcode ON School.FK_Zipcode = Zipcode.Zip
                WHERE FK_GroupID = ?
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();

        PreparedStatement ptsm = conn.prepareStatement(sql);
        ptsm.setInt(1, group);

        ResultSet resultSet = ptsm.executeQuery();

        while (resultSet.next())
        {
            Account account = new Account(
                    resultSet.getInt("AID"),
                    resultSet.getString("username"),
                    resultSet.getString("hashed_pwd"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    new School(
                            resultSet.getInt("SID"),
                            resultSet.getString("schoolName"),
                            new Location(
                                    resultSet.getInt("Zip"),
                                    resultSet.getString("city")
                            )

                    ),
                    resultSet.getInt("privilegeLevel")
            );
        }

        return null;
    }

    public List<Group> readAll() throws SQLException
    {
        List<Group> returnList = new ArrayList<>();

        String sql = """
                SELECT * FROM AccountGroup
                JOIN Account ON AccountGroup.FK_MemberID = Account.AID
                JOIN School ON Account.FK_AccountSchool = School.SID
                JOIN Zipcode ON School.FK_Zipcode = Zipcode.Zip
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();

        PreparedStatement ptsm = conn.prepareStatement(sql);

        ResultSet resultSet = ptsm.executeQuery();

        while (resultSet.next())
        {
            Group newGroup = new Group(


            );

            returnList.add(newGroup);
        }

        return returnList;
    }
}