package backend.logic.factories;

import backend.data.crud.IDataAccessObject;
import backend.data.sql.SchoolSQL;
import backend.entity.School;

public class SchoolFactory extends AbstractFactory<School>
{
    @Override
    public IDataAccessObject<School> getAccessObject() {
        return switch (source)
                {
                    case MSSQL -> new SchoolSQL();
                    case NOT_IMPLEMENTED -> null;
                };
    }
}
