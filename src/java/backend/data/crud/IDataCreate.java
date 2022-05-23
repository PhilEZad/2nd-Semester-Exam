package backend.data.crud;

public interface IDataCreate<JAVA_ENTITY>
{
    JAVA_ENTITY create(JAVA_ENTITY obj) throws Exception;
}
