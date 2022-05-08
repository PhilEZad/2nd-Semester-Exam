package Application.DAL;

import Application.BE.Inquiry;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InquiryDAO extends TemplatePatternDAO<Inquiry>
{

    @Override
    public Inquiry create(Inquiry input) {
        return null;
    }

    @Override
    public void update(Inquiry input) {

    }

    @Override
    public Inquiry read(int id) {
        return null;
    }

    @Override
    public List<Inquiry> readAll() {
        String sql = """
                    SELECT * FROM inquiry
                    """;
        List<Inquiry> inquiryList = new ArrayList<>();

        try
        {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Inquiry inquiry = new Inquiry(
                        rs.getInt("inquiryId"),
                        rs.getString("inquiry")
                        );

                inquiryList.add(inquiry);
            }

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inquiryList;
    }

    @Override
    public void delete(int id) {

    }
}
