package Application.BLL;


import Application.BE.Citizen;
import Application.BE.FunctionalEntry;
import Application.DAL.AssignedCitizensDAO;
import Application.DAL.CitizenDAO;
import Application.Utility.GUIUtils;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


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
        Callable<List<Citizen>> callable = () ->
        {
            var citizens = new AssignedCitizensDAO().read(id).getValue();
            {

                citizens.forEach(citizen -> {
                    for (var entry : new  HealthEntriesManager().getEntriesFor(citizen.getID())) {
                        citizen.addHealthConditions(entry);
                    }

                    for (var entry : new FunctionalEntriesManager().getEntriesFor(citizen.getID())) {
                        citizen.addFunctionalAbility(entry);
                    }
                });
                return citizens;
            }};

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            return executorService.submit(callable).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            GUIUtils.alertCall(Alert.AlertType.ERROR, "Fejl ved hentning af skabeloner");
            return new ArrayList<>();
        }
    }

    public void updateObservation(Citizen beCitizen, FunctionalEntry contentEntry) {
    }
}
