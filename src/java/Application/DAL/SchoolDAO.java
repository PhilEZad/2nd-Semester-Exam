package Application.DAL;

import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;
import Application.DAL.TemplateMethod.IDatabaseActions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO implements IDatabaseActions<School>
{
    @Override
    public School create(School input) {
        String sql = "INSERT INTO School (schoolName, FK_Zipcode) VALUES (?, ?)";

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try
        {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,input.getSchoolName());
            pstmt.setInt(2, input.getZipCode());
            pstmt.execute();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();

            return new School(id, input.getSchoolName(), input.getZipCode(), input.getCityName());

        }
        catch (SQLException throwable)
        {
            throwable.printStackTrace();
            return null;
        }
        finally
        {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void update(School input) {
        String sql = "UPDATE school SET schoolName =  ?, FK_Zipcode = ? WHERE SID = ?";

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psus = conn.prepareStatement(sql);

            psus.setString(1, input.getSchoolName());
            psus.setInt(2, input.getZipCode());
            psus.setInt(3, input.getID());
            psus.executeUpdate();
            psus.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public School read(int id) {
        return null;
    }

    @Override
    public List<School> readAll() {
        String sqlRead = "SELECT * FROM school JOIN Zipcode ON school.FK_Zipcode = ZipCode.Zip";

        List<School> schoolList = new ArrayList<>();

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psas = conn.prepareStatement(sqlRead);

            ResultSet rs = psas.executeQuery();

            while (rs.next()) {
                schoolList.add(
                        new School(
                                rs.getInt("SID"),
                                rs.getString("schoolName"),
                                rs.getInt("Zip"),
                                rs.getString("city")
                        )
                );
            }

            return schoolList;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void delete(int id)
    {
        String sql = "DELETE FROM School WHERE SID = ?";

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement psds = conn.prepareStatement(sql);

            psds.setInt(1, id);

            psds.executeUpdate();
            psds.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }
}
