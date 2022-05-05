package Application.DAL.DBConnector;

import Application.Utility.ObjectPool;

import java.io.IOException;
import java.sql.SQLException;


public class DBConnectionPool extends ObjectPool<DBConnection> {

    private static DBConnectionPool instance;

    public DBConnectionPool() {
        super();
    }

    @Override
    protected DBConnection create() {
        try {
            return new DBConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validate(DBConnection o) {
        try {
            return (!o.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    @Override
    public void expire(DBConnection o) {
        try {
            o.getConnection().close();
        } catch (SQLException e) {
        }
    }

    public static DBConnectionPool getInstance() {
        if (instance == null) {
            instance = new DBConnectionPool();
        }
        return instance;
    }
}
