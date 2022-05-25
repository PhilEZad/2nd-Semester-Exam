package Application.BLL;


import Application.BE.Citizen;

import java.sql.SQLException;
import java.util.List;

public class StudentDataManager {

    public StudentDataManager()
    {

    }

    public List<Citizen> getAllCitizens() throws SQLException
    {
        return null;
    }

    public void updateCitizen(Citizen citizen) throws IllegalArgumentException, SQLException
    {
        if (citizen != null)
        {
               citizenDAO.updateCitizen();
        }
        else
        {
            throw new IllegalArgumentException("");
        }
    }
}
