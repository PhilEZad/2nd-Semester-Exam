package Application.BLL;


import Application.BE.Citizen;
import Application.BE.FunctionalEntry;
import Application.DAL.AssignedCitizensDAO;
import Application.DAL.CitizenDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 *
 * @author Philip Zadeh
 * @author Mads Mandahl-Barth
 * @author Rasmus Sandbæk
 * */
public class StudentDataManager
{
    CitizenDAO citizenDAO = new CitizenDAO();

    public StudentDataManager()
    {

    }

    public List<Citizen> getAllCitizens() throws SQLException
    {
        List<Citizen> citizenListDAO = citizenDAO.readAll();
        List<Citizen> citizenList = new ArrayList<>();

        for (Citizen citizen: citizenListDAO)
        {
            if (!citizen.getTemplate())
            {
                citizenList.add(citizen);
            }
        }
        return citizenList;
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

    public List<Citizen> getAssignedCitizens(int id)
    {
        try {
            return new AssignedCitizensDAO().read(id).getValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateObservation(Citizen beCitizen, FunctionalEntry contentEntry) {
    }
}
