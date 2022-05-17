package Application.DAL.TemplateMethod;

import Application.BE.City;
import Application.BE.School;
import Application.DAL.TemplateMethod.Annotations.*;
import jdk.jfr.StackTrace;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DefaultDAO<T>
{
    public static <T> T create(T data)
    {
        List<Field> fields = new ArrayList<>();
        for (Field field : data.getClass().getDeclaredFields()) {
            try {
                if (field.trySetAccessible())
                {
                    System.out.println(field.getName());

                }
                else
                    System.err.printf("field '%s' could not be accessed, likely in another package", field.getName());
            }
            catch (SecurityException ignored)
            {
                /*
                *   ideas for implementing it as a general production API
                * */

                // 1) try to find set methods
                // 2) prompt programmer to change access specifier
                // 3) create dynamic sub-class (can't if class is marked as final)

                // 4) use another system (as alternative)
                //      4.1) variadic generic method?
            }
        }

        //SQLHelper.createInsertStatement(SQLHelper.tableName(data.getClass()), SQLHelper.columns(methods, SQLSetter.class));


        var exec = new AbstractDAO_TP<T, T>() {

            @Override
            protected T execute(PreparedStatement statement, T input) throws SQLException {;
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return "";//"INSERT INTO " + SQLHelper.tableName(data.getClass()) + " " + SQLHelper.columnCSV(methods, SQLSetter.class) + " VALUES (?, ?)";
            }
        };

        return null;
        /*
        exec.run(data);

        return exec.getResult();


         */
    }

    public static void main(String[] args) {
        create(new School(0, "VG", new City(6800)));
    }
}
