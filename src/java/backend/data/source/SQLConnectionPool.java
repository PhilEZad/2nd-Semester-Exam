package backend.data.source;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import util.ObjectPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class SQLConnectionPool extends ObjectPool<Connection> {


    public SQLConnectionPool() {
        super();
    }

    @Override
    protected Connection create() {
        try {
            return new SQLDatabase().getConnection();
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
     * The class loader guarantees to complete all static initialization (static variable in a static context) before it grants access to the class.
     * This implementation lazy initializes the INSTANCE by calling LoadSingleton.INSTANCE
     * when first accessed inside getInstance() method.
     */
    private static class LoadSingleton {
        private static final SQLConnectionPool INSTANCE = new SQLConnectionPool();

        private LoadSingleton() {}

        public static SQLConnectionPool getInstance() {
            return INSTANCE;
        }
    }

    public static SQLConnectionPool getInstance() {
        return LoadSingleton.getInstance();
    }
}
