package Application.DAL.DBConnector;

import Application.BE.HealthJournal.ERelevanceState;
import Application.BE.HealthJournal.FunctionalJournal;
import Application.BE.HealthJournal.JournalEntry;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FunctionalJournalDAO {

    public void createFunctionalJournal(FunctionalJournal journal)
    {
        //TODO

    }

    public FunctionalJournal readFunctionalJournal(int id)
    {
        FunctionalJournal funJournal  = new FunctionalJournal();
        String sqlRead = """
        SELECT * FROM functionalJournal
        JOIN functionalStatus ON functionalJournal.funJournalId = functionalStatus.funJournalIdFK
        JOIN functionalCatgories ON functionalStatus.funSubCat = functionalCatgories.funCatId
        WHERE funJournalId = ?
        """;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement psrf = conn.prepareStatement(sqlRead);

            psrf.setInt(1,id);

            ResultSet rs = psrf.executeQuery();

            while (rs.next())
            {
                String title = rs.getString("funCatName");
                String assessment = rs.getString("funDesc1");
                String description = rs.getString("funDesc2");
                String cause = rs.getString("funDesc3");
                int relevance = rs.getInt("funStatus");

                JournalEntry entry = new JournalEntry(title, assessment);
                entry.setDescription(description);
                entry.setCause(cause);
                entry.setRelevance(ERelevanceState.values()[relevance]);
                funJournal.addJournal(title,entry);
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return funJournal;
    }

    public void updateFunctionalJournal(FunctionalJournal journal)
    {
        //TODO
    }

    public void deleteFunctionalJournal(FunctionalJournal journal)
    {
        //TODO
    }
}
