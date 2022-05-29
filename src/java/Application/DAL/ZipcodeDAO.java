package Application.DAL;

import Application.BE.Location;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZipcodeDAO {

    public List<Location> readAllCity()
    {
        List<Location> cityList = new ArrayList<Location>();
        String sqlreadAll = """
                SELECT * FROM Zipcode
                """;
        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psrac = conn.prepareStatement(sqlreadAll);

            ResultSet rs = psrac.executeQuery();

            while (rs.next()) {
                String cityName = rs.getString("city");
                int zipCode = rs.getInt("Zip");
                Location city = new Location(zipCode, cityName);
                cityList.add(city);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cityList;
    }

    public Location readCity(int zipCode) throws SQLException
    {
        String sql = "Select city FROM Zipcode WHERE Zip = ?";

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psrc = conn.prepareStatement(sql);

            ResultSet rs = psrc.executeQuery();;

            return new Location(zipCode, rs.getString("city"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
