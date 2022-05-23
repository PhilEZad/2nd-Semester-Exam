package Application.DAL.TemplateMethod;

import Application.DAL.DBConnector.DBConnectionPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO_TP<RETURN_TYPE, PARAM_TYPE>
{
    private RETURN_TYPE return_value;
    private long return_id = -1;

    private PreparedStatement statement;
    private Exception LastException;

    /**
     * @param input may be null
     * */
    public final boolean run(PARAM_TYPE input) {

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            statement = conn.prepareStatement(getSQLStatement(), PreparedStatement.RETURN_GENERATED_KEYS);
            // statement = setup();

            this.return_value = execute(this.statement, input);

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.return_id = generatedKeys.getInt(1);
            }

            statement.close();

            return true;
        } catch (Exception e) {
            this.LastException = e;
            return false;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }


    public final RETURN_TYPE getResult() {

        return return_value;
    }

    public final long getResultID()
    {
        return return_id;
    }

    protected abstract RETURN_TYPE execute(PreparedStatement statement, PARAM_TYPE input) throws SQLException;
    
    protected abstract String getSQLStatement();

    //protected abstract PreparedStatement setup() throws SQLException;

    protected final Exception getLastError() {
        return LastException;
    };


}

