package Application.DAL;

import org.jetbrains.annotations.Contract;

import java.sql.SQLException;
import java.util.List;

public interface IDatabaseActions<T>
{
    @Contract(mutates = "param")
    T create(T input);

    void update(T input);

    T read(int id) throws SQLException;

    List<T> readAll();

    void delete(int id);


}
