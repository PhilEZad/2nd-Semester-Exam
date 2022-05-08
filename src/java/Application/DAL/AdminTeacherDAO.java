package Application.DAL;

import Application.BE.Teacher;
import Application.DAL.DBConnector.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminTeacherDAO
{
    private DBConnection DBconnect = new DBConnection();;

    public AdminTeacherDAO() throws IOException {
    }


    public void createTeacher(String login, String password, String firstName, String surname, String email, String salt, int schoolid)
    {
        String sql = """
                    INSERT INTO teachers (teacherLogin, teacherPassword, teacherFirstName, teacherSurname, teacherEmail, salt, school)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection connection = DBconnect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, surname);
            statement.setString(5, email);
            statement.setString(6, salt);
            statement.setInt(7, schoolid);

            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(int id)
        {
            String sql = """
                    DELETE FROM teachers
                    WHERE id = ?
                    """;

            try (Connection connection = DBconnect.getConnection())
            {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, id);

                statement.execute();

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    public Teacher getTeacher(int id)
    {
        String sql = """
                SELECT * FROM teachers
                WHERE id = ?
                """;

        try (Connection connection = DBconnect.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            result.next();
            int teacherid = result.getInt(1);
            String login = result.getString(2);
            String password = result.getString(3);
            String firstName = result.getString(4);
            String surname = result.getString(5);
            String email = result.getString(6);
            String salt = result.getString(7);
            int schoolid = result.getInt(8);

            return new Teacher(teacherid,login, password, firstName, surname, email, salt, schoolid);

        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List getAllTeachers() {

        List<Teacher> returnList = new ArrayList<>();

        String sql = """
                    SELECT * FROM teachers
                    """;
        try (Connection connection = DBconnect.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();

        while (result.next())
        {
            int teacherid = result.getInt(1);
            String login = result.getString(2);
            String password = result.getString(3);
            String firstName = result.getString(4);
            String surname = result.getString(5);
            String email = result.getString(6);
            String salt = result.getString(7);
            int schoolid = result.getInt(8);

            returnList.add(new Teacher(teacherid,login, password, firstName, surname, email, salt, schoolid));
        }
        return returnList;

        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
