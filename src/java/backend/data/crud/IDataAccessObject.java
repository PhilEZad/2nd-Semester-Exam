package backend.data.crud;

public interface IDataAccessObject<JAVA_ENTITY> extends
        IDataCreate<JAVA_ENTITY>,
        IDataRead<JAVA_ENTITY>,
        IDataUpdate<JAVA_ENTITY>,
        IDataDelete<JAVA_ENTITY>
{ }
