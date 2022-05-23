package backend.data.crud;

import java.util.List;

public interface IDataRead<JAVA_ENTITY>
{
    List<JAVA_ENTITY> getAll() throws Exception;

    JAVA_ENTITY getByID(int id) throws Exception;
    JAVA_ENTITY getByString(String str) throws Exception;
}
