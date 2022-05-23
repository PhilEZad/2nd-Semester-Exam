package backend.data.sql;

import org.intellij.lang.annotations.Language;


import java.sql.PreparedStatement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class AbstractSQL<RETURN_TYPE, PARAM_TYPE>
{
    private RETURN_TYPE return_value;
    private long return_id = -1;
    private Exception LastException = null;

    private Boolean hasExecutedSuccessfully = null;


    /**
     * Runs a callable task on a separate thread.
     *
     * @param in
     * @return
     * @throws Exception
     */
    public final RETURN_TYPE getResult(PARAM_TYPE in) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        GetResultCaller caller = new GetResultCaller<RETURN_TYPE, PARAM_TYPE>(in, this);
        Future<RETURN_TYPE> future = executor.submit(caller);
        return future.get();
    }

    public final RETURN_TYPE getResult() throws Exception {
        return this.getResult(null);
    }

    public final long getResultID()
    {
        return return_id;
    }

    public final void setReturn_id(long id)
    {
        return_id = id;
    }

    protected abstract RETURN_TYPE execute(PreparedStatement statement, PARAM_TYPE input) throws Exception;

    @Language("sql")
    protected abstract String getSQLStatement();

    protected final Exception getLastError() {
        return LastException;
    };

    protected void setLastException(Exception e) {
        this.LastException = e;
    }


}

