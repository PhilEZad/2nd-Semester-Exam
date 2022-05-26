package Application.DAL.TemplateMethod;

import org.jetbrains.annotations.Contract;

import java.sql.SQLException;
import java.util.List;

public interface IDatabaseActions<T>
{
    /**
     * todo: doc
     * */
    @Contract(mutates = "param")
    T create(T input);


    /**
     * todo: doc
     * */
    void update(T input);


    /**
     * todo: doc
     * */
    T read(int id) throws SQLException;


    /**
     * todo: doc
     * */
    List<T> readAll();


    /**
     * todo: doc
     * */
    void delete(int id);


}
