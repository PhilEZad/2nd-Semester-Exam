package Application.DAL;

import Application.BE.Location;
import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationDAO extends TemplatePatternDAO<Location>
{
    @Override
    public Location create(Location input) {
        System.err.println("illegal");
        return null;
    }

    @Override
    public void update(Location input) {
        System.err.println("illegal");
    }

    @Override
    public Location read(int id) {

        String sql = "SELECT (Zip, city) FROM Zipcode where Zip = ?";

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement psas = conn.prepareStatement(sql);

            ResultSet rs = psas.executeQuery();
            rs.next();

            return new Location(rs.getInt("Zip"), rs.getString("city"));
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
    public List<Location> readAll()
    {
        String sql = "SELECT * FROM Zipcode";

        List<Location> locations = new ArrayList<>();

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement psas = conn.prepareStatement(sql);

            ResultSet rs = psas.executeQuery();

            while (rs.next()) {
                locations.add(new Location(rs.getInt("Zip"), rs.getString("city")));
            }

            return locations;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void delete(int id) {
        System.err.println("illegal");
    }
}
