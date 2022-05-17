package Application.DAL;

import java.util.List;

public abstract class TemplatePatternDAO<T>
{
    public abstract Boolean create(T input);

    public abstract void update(T input);

    public abstract T read(int id);

    public abstract List<T> readAll();

    public abstract void delete(int id);


}
