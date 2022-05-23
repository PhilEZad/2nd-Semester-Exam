package Application.DAL;

import java.sql.SQLException;
import java.util.List;

public abstract class TemplatePatternDAO<T>
{
    public abstract T create(T input);

    public abstract void update(T input);

    public abstract T read(int id) throws SQLException;

    public abstract List<T> readAll();

    public abstract void delete(int id);


}
