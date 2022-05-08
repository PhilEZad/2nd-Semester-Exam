package Application.DAL;

import Application.BE.Case;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CasesDAO extends TemplatePatternDAO<Case>{

    @Override
    public Case create(Case input)
    {
            String sql = """
                    INSERT INTO cases (caseInquiry, caseDesc1, caseDesc2) 
                    VALUES (?, ?, ?)
                    """;

            try {
                Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, input.getInquiry().getId());
                pstmt.setString(2, input.getInquiryReason());
                pstmt.setString(3, input.getMedicalDiagnose());

                pstmt.executeUpdate();

                int id = -1;

                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }

                pstmt.close();

                return new Case(
                        id,
                        input.getInquiryReason(),
                        input.getMedicalDiagnose(),
                        input.getInquiry()
                );

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public void update(Case input)
    {

    }

    @Override
    public Case read(int id)
    {
        return null;
    }

    @Override
    public List<Case> readAll()
    {
        return null;
    }

    @Override
    public void delete(int caseId)
    {
        String sql = """
                    DELETE FROM cases
                    WHERE id = ?
                    """;
        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, caseId);

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
