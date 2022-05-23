package backend.data.sql;

import backend.data.source.SQLConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Callable;

public class GetResultCaller<RETURN_TYPE, PARAM_TYPE> implements Callable<RETURN_TYPE> {

    private AbstractSQL<RETURN_TYPE, PARAM_TYPE> query;
    private PARAM_TYPE in;

    private RETURN_TYPE return_value;

    private Boolean hasExecutedSuccessfully = null;


    public GetResultCaller(PARAM_TYPE in, AbstractSQL<RETURN_TYPE, PARAM_TYPE> return_typeparam_typeAbstractSQL) {
        this.in = in;
        this.query = return_typeparam_typeAbstractSQL;
    }


    @Override
    public RETURN_TYPE call() throws Exception {
        if (this.hasExecutedSuccessfully == null)
        {
            run(in);
        }

        if (!this.hasExecutedSuccessfully)
        {
            // post error
            System.err.println("exception occurred when running db query");
            return null;
        }

        return return_value;
    }


    private void run(PARAM_TYPE input)
    {
        Connection conn = SQLConnectionPool.getInstance().checkOut();

        try
        {
            PreparedStatement statement = conn.prepareStatement(query.getSQLStatement(), PreparedStatement.RETURN_GENERATED_KEYS);

            this.return_value = query.execute(statement, input);

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long return_id = generatedKeys.getInt(1);
                this.query.setReturn_id(return_id);
            }

            statement.close();
            this.hasExecutedSuccessfully = true;
        }
        catch (Exception e)
        {
            this.query.setLastException(e);
            this.hasExecutedSuccessfully = false;
        }
        finally
        {
            SQLConnectionPool.getInstance().checkIn(conn);
        }
    }
}
