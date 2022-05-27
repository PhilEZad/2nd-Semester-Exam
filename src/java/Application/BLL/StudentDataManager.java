package Application.BLL;


import Application.BE.Citizen;
import Application.BE.FunctionalEntry;
import Application.DAL.CitizenDAO;

import java.sql.SQLException;
import java.util.List;

public class StudentDataManager
{
    CitizenDAO citizenDAO = new CitizenDAO();

    public StudentDataManager()
    {

    }

    public List<Citizen> getAllCitizens() throws SQLException
    {
        return citizenDAO.readAll();
    }

    public void updateCitizen(Citizen citizen) throws IllegalArgumentException, SQLException
    {
        if (citizen != null)
        {
               citizenDAO.update(citizen);
        }
        else
        {
            throw new IllegalArgumentException("");
        }
    }

    public Citizen[] getAssignedCitizens(int id)
    {
        return null;
    }

    public void updateObservation(Citizen beCitizen, FunctionalEntry contentEntry) {
    }
}
