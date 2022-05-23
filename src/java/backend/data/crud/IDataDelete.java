package backend.data.crud;

public interface IDataDelete <JAVA_ENTITY>
{
    void remove(JAVA_ENTITY obj);

    void removeByID(int id);
}
