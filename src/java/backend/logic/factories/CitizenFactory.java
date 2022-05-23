package backend.logic.factories;

import backend.data.crud.IDataAccessObject;
import backend.data.sql.CitizenSQL;
import backend.entity.Citizen;

public class CitizenFactory extends AbstractFactory<Citizen>{
    @Override
    public IDataAccessObject<Citizen> getAccessObject() {
        return switch (source)
                {
                    case MSSQL -> new CitizenSQL();
                    case NOT_IMPLEMENTED -> null;
                };
    }
}
