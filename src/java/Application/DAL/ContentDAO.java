package Application.DAL;

import Application.BE.FunctionalEntry;
import Application.DAL.DBConnector.DBConnectionPool;
import Application.DAL.TemplateMethod.IDatabaseActions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContentDAO implements IDatabaseActions<FunctionalEntry>
{
    @Override
    public FunctionalEntry create(FunctionalEntry input) {
        String sql = """
                    INSERT INTO JournalEntry (FK_Category, assessment, cause, implications, currentStatus, expectedStatus, citizenGoals, notes, severity) 
                    VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?)
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            pstmt.setInt(1, input.getCategory().getID());
            pstmt.setString(2, input.getAssessment());
            pstmt.setString(3, input.getCause());
            pstmt.setString(4, input.getImplications());
            pstmt.setInt(5, input.getCurrentStatus());
            pstmt.setInt(6, input.getExpectedStatus());
            pstmt.setString(7, input.getCitizenGoals());
            pstmt.setString(8, input.getNote());
            pstmt.setInt(9, input.getSeverity());

            pstmt.execute();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            input.setID(id);
            pstmt.close();
            return input;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void update(FunctionalEntry input) {

    }

    @Override
    public FunctionalEntry read(int id) {
        return null;
    }

    @Override
    public List<FunctionalEntry> readAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
