package Application.DAL.TemplateMethod;

import Application.BE.IUniqueIdentifier;
import Application.DAL.DBConnector.DBConnectionPool;
import javafx.util.Pair;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractDAO<RETURN_TYPE>
{
    private final AtomicReference<RETURN_TYPE> return_value = new AtomicReference<>();
    private final List<Integer> return_ids = new ArrayList<>();

    private Exception LastException ;
    private final AtomicBoolean hasExecutedSuccessfully = new AtomicBoolean(false);
    private final AtomicBoolean hasFinished = new AtomicBoolean(false);
    private final AtomicBoolean hasStarted = new AtomicBoolean(false);

    private void run()
    {
        Connection conn = DBConnectionPool.getInstance().checkOut();

        try
        {
            PreparedStatement statement = conn.prepareStatement(this.getSQLStatement(), PreparedStatement.RETURN_GENERATED_KEYS);

            RETURN_TYPE newVal = this.execute(statement);
            this.return_value.set(newVal);

            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                this.return_ids.add(generatedKeys.getInt(1));
            }

            statement.close();
            this.hasExecutedSuccessfully.set(true);
        }
        catch (Exception e)
        {
            LastException = e;
            this.hasExecutedSuccessfully.set(false);
        }
        finally
        {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    public final void start()
    {
        this.hasStarted.set(true);

        this.run();

        this.hasFinished.set(true);
    }

    public static void setPlaceholders(PreparedStatement statement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++)
        {
            try {
                statement.setObject(i + 1, values[i]);
            } catch (SQLException e) {
                statement.setObject(i + 1, null);
            }
        }
    }

    public final Pair<Integer, RETURN_TYPE> getResult()
    {
        if (!this.hasExecutedSuccessfully.get())
        {
            // post error
            System.err.println("exception occurred when running db query");
            return null;
        }

        return new Pair<>(return_ids.stream().findFirst().orElse(-1), return_value.getAcquire());
    }

    public final List<Integer> getAllReturnedIDs()
    {
        return this.return_ids;
    }

    protected abstract RETURN_TYPE execute(PreparedStatement statement) throws SQLException;

    @Language("sql")
    protected abstract String getSQLStatement();

    public final boolean hasFinished() { return this.hasFinished.get(); }
    public final boolean hasStarted() { return this.hasStarted.get(); }
    public final boolean isSuccessful() { return this.hasExecutedSuccessfully.get(); }
    protected final Exception getLastError() { return LastException; };
}

