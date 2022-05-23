package backend.logic.factories;

import backend.data.crud.IDataAccessObject;
import backend.data.sql.LocationSQL;
import backend.entity.Location;

public class LocationFactory extends AbstractFactory<Location>
{
    @Override
    public IDataAccessObject<Location> getAccessObject()
    {
        return switch (source)
        {
            case MSSQL -> new LocationSQL();
            case NOT_IMPLEMENTED -> null;
        };
    }
}
