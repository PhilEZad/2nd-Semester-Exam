package backend.data.sql.experimental;

import org.jetbrains.annotations.ApiStatus;

import java.util.List;

@ApiStatus.Experimental
public class SQLMetaData
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
