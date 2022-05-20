package Application.DAL;

import Application.BE.Citizen;
import Application.BE.ContentEntry;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContentDAO extends TemplatePatternDAO<List<ContentEntry>>
{

    private int insertSingle(ContentEntry entry)
    {
        String sql = """
                    INSERT INTO JournalEntry (FK_Category, assessment, cause, implications, currentStatus, expectedStatus, citizenGoals, notes, severity) 
                    VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?)
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            pstmt.setInt(1, entry.getCategory().getId());
            pstmt.setString(2, entry.getAssessment());
            pstmt.setString(3, entry.getCause());
            pstmt.setString(4, entry.getImplications());
            pstmt.setInt(5, entry.getCurrentStatus());
            pstmt.setInt(6, entry.getExpectedStatus());
            pstmt.setString(7, entry.getCitizenGoals());
            pstmt.setString(8, entry.getNote());
            pstmt.setInt(9, entry.getSeverity());

            pstmt.execute();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();
            return id;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public List<ContentEntry> create(List<ContentEntry> input)
    {
        for (ContentEntry entry : input) {
            entry.setId(insertSingle(entry));
        }

        return input;
    }

    @Override
    public void update(List<ContentEntry> input) {

    }

    /**
     * @param id citizenID
     * */
    @Override
    public List<ContentEntry> read(int id) {
        return null;
    }

    @Override
    public List<List<ContentEntry>> readAll() {
        return null;
    }

    /**
     * @param id citizenID
     * */
    @Override
    public void delete(int id) {
        // all entries for citizen
    }

    public static void main(String[] args)
    {

    }
}
