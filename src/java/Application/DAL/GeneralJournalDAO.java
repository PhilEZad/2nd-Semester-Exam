package Application.DAL;

import Application.BE.Citizen;
import Application.BE.HealthJournal.GeneralJournal;
import Application.BE.HealthJournal.Journal;
import Application.BE.HealthJournal.JournalEntry;
import Application.DAL.DBConnector.DBConnectionPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralJournalDAO {

    public void createGeneralJournal(GeneralJournal journal)
    {
        //TODO
    }

    public GeneralJournal readGeneralJournal(int id)
    {
        GeneralJournal generalJournal = new GeneralJournal();
        String sqlRead = """
        SELECT * FROM generalJournal
        JOIN generalStatus ON generalJournal.genJournalId = generalStatus.genJournalIdFK
        JOIN generalCatgories ON generalStatus.genSubCat = generalCatgories.genCatId
        WHERE genJournalId = ?
                """;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement psrg = conn.prepareStatement(sqlRead);

            psrg.setInt(1, id);

            ResultSet rs = psrg.executeQuery();

            while (rs.next())
            {
                String title = rs.getString("genCat");
                String description = rs.getString("genDesc1");
                JournalEntry entry = new JournalEntry(title, description);

                generalJournal.addJournal(title,entry);
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return generalJournal;
    }

    public void updateGeneralJournal(GeneralJournal Journal)
    {
        //TODO
    }

    public void deleteGeneralJournal(GeneralJournal Journal)
    {
        //TODO
    }
}
