package Application.DAL.TemplateMethod;

import Application.DAL.TemplateMethod.Annotations.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DefaultDAO<T>
{
    public static <T> T create(T data)
    {
        var methods = data.getClass().getMethods();

        if (methods.length == 0)
        {
            // no methods or they are private / protected
            System.err.println("no method access");
            return null;
        }

        SQLHelper.createInsertStatement(SQLHelper.tableName(data.getClass()), SQLHelper.columns(methods, SQLSetter.class));



        var exec = new AbstractDAO_TP<T, T>() {

            @Override
            protected T execute(PreparedStatement statement, T input) throws SQLException {;
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return "INSERT INTO " + SQLHelper.tableName(data.getClass()) + " " + SQLHelper.columnCSV(methods, SQLSetter.class) + " VALUES (?, ?)";
            }
        };

        return null;
        /*
        exec.run(data);

        return exec.getResult();


         */
    }
}
