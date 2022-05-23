package backend.logic.factories;

import backend.data.crud.IDataAccessObject;
import backend.data.sql.AccountSQL;
import backend.entity.User;

public class AccountFactory extends AbstractFactory<User>{
    @Override
    public IDataAccessObject<User> getAccessObject() {
        return switch (source)
                {
                    case MSSQL -> new AccountSQL();
                    case NOT_IMPLEMENTED -> null;
                };
    }
}
