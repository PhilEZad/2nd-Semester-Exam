package backend.logic;

import java.io.InvalidClassException;
import java.util.HashMap;

import backend.entity.*;
import backend.logic.factories.*;

public final class DataAccessFactory
{
    public static AbstractFactory<?> get(Class<?> cls) throws InvalidClassException
    {
        if (cls == User.class)
        {
            return new AccountFactory();
        }
        else if (cls == Location.class)
        {
            return new LocationFactory();
        }
        else if (cls == School.class)
        {
            return new SchoolFactory();
        }
        else if (cls == Citizen.class)
        {
            return new CitizenFactory();
        }

        throw new InvalidClassException("no factory exists for the '%s' class".formatted(cls.getName()));
    }
}
