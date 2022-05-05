package Application.DAL.DBConnector;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBConnection {
    private final SQLServerDataSource dataSource;

    public DBConnection() throws IOException
    {
        Properties property = new Properties();
        dataSource = new SQLServerDataSource();

        try {
            property.load(new FileReader("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataSource.setServerName(property.getProperty("server"));
        dataSource.setDatabaseName(property.getProperty("database"));
        dataSource.setUser(property.getProperty("username"));
        dataSource.setPassword(property.getProperty("password"));
        dataSource.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
}
