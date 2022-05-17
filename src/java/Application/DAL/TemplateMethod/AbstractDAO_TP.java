package Application.DAL.TemplateMethod;

import Application.DAL.DBConnector.DBConnectionPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.Contract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @param <PARAM_TYPE>
 *                    the <i>type class</i> that <b>may</b> be used to:
 *                    <p>1) transfer data to the database.</p>
 *                    <p>2) partially/fully construct the returned value type.</p>
 *
 * @param <RETURN_TYPE>
 *
 * @apiNote using template method (design pattern) for communicating with a MSSQL database with prepared statements.
 * @author mads
 * */
public abstract class AbstractDAO_TP<RETURN_TYPE, PARAM_TYPE>
{
    private RETURN_TYPE return_value;
    private Integer return_id = null;

    private Exception LastException;

    /**
     * @param input may be null
     * */
    public final boolean run(PARAM_TYPE input) {
        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement statement = conn.prepareStatement(getSQLStatement(), PreparedStatement.RETURN_GENERATED_KEYS);

            this.return_value = execute(statement, input);

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

    /**
     * this is the way of getting the returned value(s) from the SQL server.
     *
     * @return the result of the overridden execute method. Or a null reference.
     * */
    public final RETURN_TYPE getResult() {
        return return_value;
    }

    /**
     * useful when inserting new rows, for further manipulation without a query between DB insertions.
     *
     * @return the id of the affected row in some table
     * */
    public final long getResultID()
    {
        return return_id;
    }


    /**
     * <P> <b>MUST:</b> contain all logic for mapping DB table columns to variables in a Business entity </P>
     * <P> <b>MAY:</b> handle exceptions by itself. If not handled manually, any thrown exception will be accessible from the <code>getLastError()</code> method </P>
     *
     * @return the resulting object from a CRUD operation to the database
     * */
     protected abstract RETURN_TYPE execute(PreparedStatement statement, PARAM_TYPE input) throws SQLException;

    /**
     *
     * */
    @Language("SQL")
    protected abstract String getSQLStatement();

    /**
     * @apiNote if an exception is cast in the execute method either manually or by dependencies.
     * the local variable will be set automatically
     *
     * @return the latest thrown exception. or NULL
     * */
    protected final Exception getLastError() {
        return LastException;
    };


}

