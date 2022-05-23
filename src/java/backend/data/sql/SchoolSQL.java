package backend.data.sql;

import backend.data.crud.IDataAccessObject;
import backend.entity.Location;
import backend.entity.School;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolSQL implements IDataAccessObject<School>
{
    @Override
    public List<School> getAll() throws Exception {
        return new AbstractSQL<List<School>, Void>(){
            @Override
            protected List<School> execute(PreparedStatement statement, Void input) throws SQLException {

                List<School> schoolList = new ArrayList<>();
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    schoolList.add(new School(rs.getInt("SID"), rs.getString("schoolName"), new Location(rs.getInt("Zip"), rs.getString("city"))));
                }

                return schoolList;
            }

            @Override
            protected String getSQLStatement() {
                return "SELECT * FROM school JOIN Zipcode ON school.FK_Zipcode = ZipCode.Zip";
            }
        }.getResult();
    }


    @Override
    public School getByID(int id) throws Exception {
        return new AbstractSQL<School, Integer>(){
            @Override
            protected School execute(PreparedStatement statement, Integer input) throws SQLException {
                statement.setInt(1, input);
                ResultSet rs = statement.executeQuery();
                return new School( rs.getInt("id"), rs.getString("schoolName"), new Location(rs.getInt("Zip"), rs.getString("city")));
            }

            @Override
            protected String getSQLStatement() {
                return "SELECT * FROM school JOIN Zipcode ON school.FK_Zipcode = ZipCode.Zip WHERE SID = ?";
            }
        }.getResult(id);
    }

    @Override
    public School create(School obj) throws Exception {
        return new AbstractSQL<School, School>() {
            @Override
            protected School execute(PreparedStatement statement, School input) throws SQLException {
                statement.setString(1,input.getName());
                statement.setInt(2, input.getLocation().getZipcode());
                statement.execute();

                int id = -1;

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }

                statement.close();

                return new School(id, input.getName(), new Location(input.getLocation().getZipcode(), input.getLocation().getName()));
            }

            @Override
            protected String getSQLStatement() {
                return "INSERT INTO School (schoolName, FK_Zipcode) VALUES (?, ?)";
            }
        }.getResult(obj);
    }

    @Override
    public void remove(School obj) throws Exception {
        this.removeByID(obj.getId());
    }

    @Override
    public void removeByID(int id) throws Exception {
        new AbstractSQL<Void, Integer>() {

            @Override
            protected Void execute(PreparedStatement statement, Integer input) throws SQLException {
                statement.setInt(1, input);

                statement.executeUpdate();
                statement.close();

                return null;
            }

            @Override
            protected String getSQLStatement() {
                return "DELETE FROM School WHERE SID = ?";
            }
        }.getResult(id);
    }

    @Override
    public void update(School obj) throws Exception {
        new AbstractSQL<Void, School>() {
            @Override
            protected Void execute(PreparedStatement statement, School input) throws SQLException {

                statement.setString(1, input.getName());
                statement.setInt(2, input.getLocation().getZipcode());
                statement.setInt(3, input.getId());
                statement.executeUpdate();

                return null;
            }

            @Override
            protected String getSQLStatement() {
                return "UPDATE school SET schoolName =  ?, FK_Zipcode = ? WHERE SID = ?";
            }
        }.getResult(obj);
    }

    @Override
    public void updateByID(int id, School obj) throws Exception {
        var school = new School(obj);
        school.setId(id);
        this.update(school);
    }

    @Override
    public School getByString(String name) throws Exception {
        return new AbstractSQL<School, String>(){
            @Override
            protected School execute(PreparedStatement statement, String input) throws SQLException {
                statement.setString(1, input);
                ResultSet rs = statement.executeQuery();
                return new School(rs.getInt("id"), rs.getString("schoolName"), new Location(rs.getInt("Zip"), rs.getString("city")));
            }

            @Override
            protected String getSQLStatement() {
                return "SELECT * FROM school JOIN Zipcode ON school.FK_Zipcode = ZipCode.Zip WHERE schoolName = ?";
            }
        }.getResult(name);
    }
}
