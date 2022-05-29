package Application.DAL;


import Application.DAL.DBConnector.DBConnectionPool;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class HealthJournalDAO extends TemplatePatternDAO
{

    @Override
    public Object create(Object input)
    {
        return null;
    }

    @Override
    public void update(Object input)
    {

    }

    @Override
    public Object read(int id)
    {
        return null;
    }

    @Override
    public List readAll()
    {
        String sql = """
                SELECT * FROM 
                """;

        List<Object> healthJournalList = FXCollections.observableArrayList();

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                healthJournalList.add(
                        null
                );
            }

            return healthJournalList;

        } catch (Exception e)
        {
            e.printStackTrace();
            return healthJournalList;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void delete(int id)
    {

    }
}
