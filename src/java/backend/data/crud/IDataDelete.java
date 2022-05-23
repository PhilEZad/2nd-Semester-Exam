package backend.data.crud;

public interface IDataDelete <JAVA_ENTITY>
{
    void remove(JAVA_ENTITY obj) throws Exception;

    void removeByID(int id) throws Exception;
}
