package Application.BLL;


import Application.BE.*;
import Application.DAL.CategoryDAO;
import Application.DAL.CitizenDAO;
import Application.DAL.ContentDAO;
import Application.DAL.GroupDAO;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.SessionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class StudentDataManager {

    public StudentDataManager() {

    }

    public List<Group> getAssignedGroups(int studentID)
    {
        GroupDAO dao = new GroupDAO();

        return dao.readAll().stream().filter(group -> group.getGroupMembers().contains(studentID)).toList();
    }

     // journal entry!!! CRUD

    public List<Citizen> getAssignedCitizens(int studentID)
    {
        var groups = getAssignedGroups(studentID);

        CitizenDAO dao = new CitizenDAO();

        List<Citizen> citizens = new ArrayList<>();

        for (var group : groups)
        {
            citizens.add(dao.read(group.getCitizen().getId()));
        }

        return citizens;
    }

    public void updateObservation(Citizen selectedCitizen, ContentEntry value) {

        ContentDAO DAO = new ContentDAO();

        if (value.getCategory().getType() == CategoryType.FUNCTIONAL_ABILITY)
        {
            if (selectedCitizen.getFunctionalAbilities().containsKey(value.getCategory())  || selectedCitizen.getHealthConditions().containsKey(value.getCategory()))
            {
                DAO.update(value);
            }
            else
            {
                selectedCitizen.addFunctionalAbility(value);
                DAO.create(value);
            }
        }
        else
        {
            selectedCitizen.addHealthConditions(value);
            DAO.create(value);
        }
    }

}
