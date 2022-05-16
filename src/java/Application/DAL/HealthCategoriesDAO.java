package Application.DAL;

import Application.BE.Case;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HealthCategoriesDAO extends TemplatePatternDAO{
    @Override
    public Object create(Object input) {
        return null;
    }

    @Override
    public void update(Object input) {

    }

    @Override
    public Object read(int id) {
        return null;
    }

    @Override
    public List readAll() {
        String sql = """
                    SELECT * cases dbo.functionalCatgories
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("funCatId");
                int superId = resultSet.getInt("funSuperCat");
                String name = resultSet.getString("funCatName");


            }


            pstmt.close();



        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }


    @Override
    public void delete(int id) {

    }
}
