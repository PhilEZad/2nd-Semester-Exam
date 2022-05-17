package Application.DAL;

import Application.BE.CitizenTemplate;
import Application.BE.City;
import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO extends TemplatePatternDAO<School>{


    @Override
    public School create(School input) {
        String sqlCreate = """
                INSERT INTO schools (schoolName, schoolZipCode)
                VALUES (?, ?)
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlCreate, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,input.getSchoolName());
            pstmt.setInt(2, input.getLocation().getZipCode());

            pstmt.execute();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();

            return new School(
                    id,
                    input.getSchoolName(),
                    new City(input.getLocation().getZipCode())
            );

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void update(School input) {
        String sqlUpdate = """
                UPDATE school
                SET schoolName =  ?, FK_Zipcode = ?
                WHERE SID = ?
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psus = conn.prepareStatement(sqlUpdate);

            psus.setString(1, input.getSchoolName());
            psus.setInt(2, input.getLocation().getZipCode());
            psus.setInt(3, input.getSchoolID());
            psus.executeUpdate();
            psus.close();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public School read(int id) {
        return null;
    }

    @Override
    public List<School> readAll() {
        String sqlRead = """
                     SELECT * FROM school 
                     JOIN Zipcode ON school.FK_Zipcode = ZipCode.Zip
                     """;
        List<School> schoolList = new ArrayList<>();
        String name;
        String city;
        int zipCode;
        int id;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psas = conn.prepareStatement(sqlRead);

            ResultSet rs = psas.executeQuery();

            while (rs.next()) {
                name = rs.getString("schoolName");
                city = rs.getString("cityName");
                zipCode = rs.getInt("zipCode");
                id = rs.getInt("id");

                School school = new School(id, name, new City(zipCode, city));

                schoolList.add(school);
            }
            psas.close();
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
        String sqlDelete = """
                DELETE FROM School 
                WHERE SID = ?
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psds = conn.prepareStatement(sqlDelete);

            psds.setInt(1, id);

            psds.executeUpdate();
            psds.close();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }
}
