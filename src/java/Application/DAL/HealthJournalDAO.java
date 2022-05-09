package Application.DAL;

import Application.BE.Citizen;
import Application.BE.HealthJournal.*;
import Application.DAL.DBConnector.DBConnectionPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthJournalDAO {

    public void createHealthJournal(HealthJournal journal)
    {

    }

    public HealthJournal readHealthJournal(int id)
    {
        HealthJournal heaJournal  = new HealthJournal();
        String sqlRead = """
        SELECT * FROM healthJournal
        JOIN healthStatus ON healthJournal.heaJournalId = healthStatus.heaJournalIdFK
        JOIN healthCatgories ON healthStatus.heaSubCat = healthCatgories.heaCatId
        WHERE heaJournalId = ?
        """;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement psrf = conn.prepareStatement(sqlRead);

            psrf.setInt(1,id);

            ResultSet rs = psrf.executeQuery();

            while (rs.next())
            {
                String title = rs.getString("heaCatName");
                int superCatId = rs.getInt("heaSuperCat");
                String assessment = rs.getString("heaDesc1");
                String description = rs.getString("heaDesc2");
                String cause = rs.getString("heaDesc3");
                int severity = rs.getInt("heaStatus");

                JournalEntry entry = new JournalEntry(title, assessment);
                entry.setDescription(description);
                entry.setCause(cause);
                entry.setSeverity(ESeverityState.values()[severity]);
                heaJournal.addJournal(title,entry);
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return heaJournal;
    }

    public void updateHealthJournal(HealthJournal journal)
    {
        //TODO
    }

    public void deleteHealthJournal(HealthJournal Journal)
    {
        //TODO
    }
}
