package backend.data.crud;

import java.util.List;

public interface IDataRead<JAVA_ENTITY>
{
    List<JAVA_ENTITY> getAll();

    JAVA_ENTITY getByID(int id);
    JAVA_ENTITY getByString(String str);
}
