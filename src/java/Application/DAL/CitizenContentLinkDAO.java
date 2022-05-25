package Application.DAL;

import Application.BE.CitizenContentBinding;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CitizenContentLinkDAO implements IDatabaseActions<CitizenContentBinding>
{

    private void singleBind(int citizenID, int contentID)
    {
        String sql = "INSERT INTO Content (FK_CitizenID, FK_JournalEntry) VALUES (?, ?)";
        Connection conn = DBConnectionPool.getInstance().checkOut();

        try
        {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1,citizenID);
            pstmt.setInt(2, contentID);
            pstmt.execute();

            pstmt.close();
        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
        }
        finally
        {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public CitizenContentBinding create(CitizenContentBinding input)
    {
        for (var contentID : input.contentIDs) {
            singleBind(input.citizenID, contentID);
        }

        return input;
    }

    @Override
    public void update(CitizenContentBinding input) {

    }

    @Override
    public CitizenContentBinding read(int id) {
        return null;
    }

    @Override
    public List<CitizenContentBinding> readAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
