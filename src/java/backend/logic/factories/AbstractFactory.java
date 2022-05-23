package backend.logic.factories;

import backend.data.crud.IDataAccessObject;
import backend.data.SourceSelection;

public abstract class AbstractFactory<TYPE>
{
    protected static SourceSelection source = SourceSelection.MSSQL;

    public static void setSource(SourceSelection source)
    {
        AbstractFactory.source = source;
    }

    public abstract IDataAccessObject<TYPE> getAccessObject();
}
