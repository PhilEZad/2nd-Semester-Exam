package Application.DAL;

import Application.BE.*;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO extends TemplatePatternDAO<Citizen>
{
    @Override
    public Citizen create(Citizen input) {
        String sql = """
                    INSERT INTO Citizen (FK_Info, FK_SchoolOwner, firstName, lastName, age) 
                    VALUES (?, ?, ?, ?, ?)
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, input.getJournal().getId());
            pstmt.setInt(2, input.getSchool().getSchoolID());
            pstmt.setString(3, input.getFirstname());
            pstmt.setString(4, input.getLastname());
            pstmt.setInt(5, input.getAge());

            pstmt.executeUpdate();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();
            return new Citizen(
                    id,
                    input.getJournal(),
                    input.getSchool(),
                    input.getFirstname(),
                    input.getLastname(),
                    input.getAge()
            );

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void update(Citizen input)
    {
        String sql = """
                UPDATE Citizen
                SET FK_Info = ?, FK_SchoolOwner = ?, firstName = ?, lastName = ?, age = ?
                WHERE CID = ?
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, input.getJournal().getId());
            ptsm.setInt(2, input.getSchool().getSchoolID());
            ptsm.setString(3, input.getFirstname());
            ptsm.setString(4, input.getLastname());
            ptsm.setInt(5, input.getAge());

            ptsm.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public Citizen read(int id)
    {
        String sql = """
                    SELECT * FROM Citizen
                    JOIN School ON School.SID = Citizen.FK_SchoolOwner
                    JOIN GeneralInfo ON GeneralInfo.InfoID = Citizen.FK_Info
                    JOIN Zipcode ON Zipcode.zip = School.FK_Zipcode
                    WHERE CID = ?
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, id);

            ResultSet result = ptsm.executeQuery();

            result.next();

            Location location = new Location(
                    result.getInt("FK_Zipcode"),
                    result.getString("city")
            );

            School school = new School(
                    result.getInt("SID"),
                    result.getString("schoolName"),
                    location
            );

            GeneralJournal journal = new GeneralJournal(
                    result.getInt("InfoID"),
                    result.getString("coping"),
                    result.getString("motivation"),
                    result.getString("resources"),
                    result.getString("roles"),
                    result.getString("habits"),
                    result.getString("eduAndJob"),
                    result.getString("lifestory"),
                    result.getString("healthInfo"),
                    result.getString("assistiveDevices"),
                    result.getString("homelayout"),
                    result.getString("network")
            );

            return new Citizen(
                    result.getInt("CID"),
                    journal,
                    school,
                    result.getString("firstName"),
                    result.getString("lastName"),
                    result.getInt("age")
            );
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Citizen> readAll()
    {
        List<Citizen> returnList = new ArrayList<>();

        String sql = """
                    SELECT * FROM Citizen
                    JOIN School ON School.SID = Citizen.FK_SchoolOwner
                    JOIN GeneralInfo ON GeneralInfo.InfoID = Citizen.FK_Info
                    JOIN Zipcode ON Zipcode.zip = School.FK_Zipcode
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {

                Location location = new Location(
                        result.getInt("FK_Zipcode"),
                        result.getString("city")
                );

                School school = new School(
                        result.getInt("SID"),
                        result.getString("schoolName"),
                        location
                );

                GeneralJournal journal = new GeneralJournal(
                        result.getInt("InfoID"),
                        result.getString("coping"),
                        result.getString("motivation"),
                        result.getString("resources"),
                        result.getString("roles"),
                        result.getString("habits"),
                        result.getString("eduAndJob"),
                        result.getString("lifestory"),
                        result.getString("healthInfo"),
                        result.getString("assistiveDevices"),
                        result.getString("homelayout"),
                        result.getString("network")
                );

                Citizen citizen = new Citizen(
                        result.getInt("CID"),
                        journal,
                        school,
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getInt("age")
                );

                returnList.add(citizen);

            }
            pstmt.close();

            return returnList;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return returnList;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void delete(int id) {
        String sql = """
                DELETE FROM Citizen
                WHERE CID = ?
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CitizenDAO temptDAO = new CitizenDAO();

        System.out.print(temptDAO.read(7).getFirstname());
    }
}
