package backend.data.crud;

public interface IDataUpdate <JAVA_ENTITY>
{
    void update(JAVA_ENTITY obj) throws Exception;

    void updateByID(int id, JAVA_ENTITY obj) throws Exception;
}
