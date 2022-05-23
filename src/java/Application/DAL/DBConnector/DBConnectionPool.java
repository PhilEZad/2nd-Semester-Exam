package Application.DAL.DBConnector;

import Application.Utility.ObjectPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class DBConnectionPool extends ObjectPool<Connection> {


    public DBConnectionPool() {
        super();
    }

    @Override
    protected Connection create() {
        try {
            return new DBConnection().getConnection();
        } catch (IOException | SQLServerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validate(Connection o) {
        try {
            return (!o.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    @Override
    public void expire(Connection o) {
        try {
            o.close();
        } catch (SQLException e) {
        }
    }

    /**
     * private static class.
     * Thread-safe lazy initialization is achieved without explicit synchronization.
     * the variable INSTANCE is wrapped in an inner class, utilizing the class loader to do synchronization.
     * The class loader guarantees to complete all static initialization before it grants access to the class.
     * This implementation lazy initializes the INSTANCE by calling LoadSingleton.INSTANCE
     * when first accessed inside getInstance() method.
     */
    private static class LoadSingleton {
        private static final DBConnectionPool INSTANCE = new DBConnectionPool();

        private LoadSingleton() {}

        public static DBConnectionPool getInstance() {
            return INSTANCE;
        }
    }

    public static DBConnectionPool getInstance() {
        return LoadSingleton.getInstance();
    }
}
