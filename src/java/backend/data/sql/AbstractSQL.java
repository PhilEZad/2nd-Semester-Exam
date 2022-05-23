package backend.data.sql;

import backend.data.source.SQLConnectionPool;
import org.intellij.lang.annotations.Language;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractSQL<RETURN_TYPE, PARAM_TYPE>
{
    private RETURN_TYPE return_value;
    private long return_id = -1;
    private Exception LastException = null;

    private Boolean hasExecutedSuccessfully = null;

    /**
     * @param input may be null
     */
    private void run(PARAM_TYPE input)
    {
        Connection conn = SQLConnectionPool.getInstance().checkOut();

        try
        {
            PreparedStatement statement = conn.prepareStatement(getSQLStatement(), PreparedStatement.RETURN_GENERATED_KEYS);

            this.return_value = execute(statement, input);

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.return_id = generatedKeys.getInt(1);
            }

            statement.close();
            this.hasExecutedSuccessfully = true;
        }
        catch (Exception e)
        {
            this.LastException = e;
            this.hasExecutedSuccessfully = false;
        }
        finally
        {
            SQLConnectionPool.getInstance().checkIn(conn);
        }
    }

    public final RETURN_TYPE getResult(PARAM_TYPE in) {

        if (this.hasExecutedSuccessfully == null)
        {
            this.run(in);
        }

        if (!this.hasExecutedSuccessfully)
        {
            // post error
            System.err.println("exception occurred when running db query");
            return null;
        }

        return return_value;
    }

    public final RETURN_TYPE getResult() {
        return this.getResult(null);
    }

    public final long getResultID()
    {
        return return_id;
    }

    protected abstract RETURN_TYPE execute(PreparedStatement statement, PARAM_TYPE input) throws SQLException;

    @Language("sql")
    protected abstract String getSQLStatement();

    protected final Exception getLastError() {
        return LastException;
    };


}

