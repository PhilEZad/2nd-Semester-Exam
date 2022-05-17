package Application.DAL.TemplateMethod;

import Application.BE.City;
import Application.BE.School;
import Application.DAL.TemplateMethod.Annotations.*;
import netscape.javascript.JSObject;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DefaultDAO
{
    private static <U> Field[] getFields(U data)
    {
        return data.getClass().getDeclaredFields();
    }

    private static <U extends Field> Field[] getFields(U data)
    {
        return data.getType().getDeclaredFields();
    }

    private static List<SQLMetaData> parse(Field[] fields, Object data, SQLMetaData parent)
    {
        List<SQLMetaData> metaDataList = new ArrayList<>();

        // get class variables
        for (Field field : fields)
        {
            try
            {
                // check to see if the field is accessible and has the relevant annotation
                if (field.trySetAccessible() && field.isAnnotationPresent(SQLColumn.class))
                {
                    SQLColumn column = field.getAnnotation(SQLColumn.class);
                    SQLMetaData current = new SQLMetaData();

                    current.sql_column = column.name();

                    // check if field is an object that can be mapped to an SQL table
                    // if it is recursively get those fields
                    if (field.getType().isAnnotationPresent(SQLTable.class))
                    {
                        // current value is just a reference to the object stored in the current instance field.
                        current.value = field.get(data);

                        current.children = parse(getFields(field), field.get(data), current);
                    }
                    else
                    {
                        // end node
                        current.parent = parent;

                        // current value contains a datatype that can (if annotations are used correctly) be mapped directly to sql data types
                        current.value = field.get(data);
                    }

                    metaDataList.add(current);
                }
                else System.err.printf("field '%s' could not be accessed, likely in another package\n", field.getName());
            }
            catch (SecurityException | IllegalAccessException se)
            {
                se.printStackTrace();

                //  ideas for implementing it as a general production API
                //
                // 1) try to find set methods
                // 2) prompt programmer to change access specifier
                // 3) create dynamic sub-class (can't if class is marked as final)
                //
                // 4) use another system (as alternative)
                //      4.1) variadic generic method?
            }
        }

        return metaDataList;
    }

    private static class SQLMetaData
    {
        public String sql_table;
        public String sql_column;
        public Object value;
        public SQLMetaData parent;
        public List<SQLMetaData> children;

        @Override
        public String toString()
        {
            return"SQLMetaData{" +
                        "sql_table='" + sql_table + '\'' +
                        ", sql_column='" + sql_column + '\'' +
                        ", value=" + value +
                        // use the value of the parent since it has a default toString; prints the internal reference used by JVM
                        ", parent=" + (parent == null ? "null" :  parent.value) +
                        ", children=" + children +
                        '}';
        }
    }

    private static <T> SQLMetaData parse(T data)
    {
        List<SQLMetaData> metaData = parse(getFields(data), data, null);

        var result = new SQLMetaData();

        result.children = metaData;
        result.parent = null;
        result.value = null;
        result.sql_column = null;
        result.sql_table = "root";

        return result;
    }

    public static <T> T create(T data)
    {
        SQLMetaData meta = parse(data);

        System.out.println(meta);


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
