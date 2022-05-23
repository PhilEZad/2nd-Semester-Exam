package backend.data.sql;

import backend.data.crud.IDataAccessObject;
import backend.entity.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationSQL implements IDataAccessObject<Location>
{
    @Override
    public List<Location> getAll()
    {
        return new AbstractSQL<List<Location>, Void>() {
            @Override
            protected List<Location> execute(PreparedStatement statement, Void input) throws SQLException {

                List<Location> result = new ArrayList<>();
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    result.add(new Location(rs.getInt("Zip"), rs.getString("city")));
                }

                return result;
            }

            @Override
            protected String getSQLStatement() {
                return "SELECT * FROM Zipcode";
            }
        }.getResult();
    }

    @Override
    public Location getByID(int id) {
        return new AbstractSQL<Location, Integer>()
        {
            @Override
            protected Location execute(PreparedStatement statement, Integer input) throws SQLException
            {
                statement.setInt(1, input);
                ResultSet rs = statement.executeQuery();;

                return new Location(input, rs.getString("city"));
            }

            @Override
            protected String getSQLStatement() {
                return "Select city FROM Zipcode WHERE Zip = ?";
            }
        }.getResult(id);
    }

    @Override
    public Location getByString(String city) {
        return new AbstractSQL<Location, String>() {
            @Override
            protected Location execute(PreparedStatement statement, String input) throws SQLException {
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return "Select city FROM Zipcode WHERE city = ?";
            }
        }.getResult(city);
    }

    @Override
    public Location create(Location obj) {
        // TODO: 22-05-2022
        return obj;
    }

    @Override
    public void remove(Location obj) {
        // TODO: 22-05-2022

    }

    @Override
    public void removeByID(int id) {
        // TODO: 22-05-2022

    }

    @Override
    public void update(Location obj) {
        // TODO: 22-05-2022

    }

    @Override
    public void updateByID(int id, Location obj) {
        // TODO: 22-05-2022

    }
}
