package Application.GUI.Models.ControllerModels;

import Application.BE.Account;
import Application.BE.Citizen;
import Application.BLL.TeacherDataManager;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CitizensControllerModel {


    private TeacherDataManager manager = new TeacherDataManager();
    private CitizenModel selectedCitizen;

    public ObservableList<CitizenModel> getAllCitizenModels(){
        List<Citizen> citizenList = manager.getAllCitizens();
        ObservableList<CitizenModel> citizenModels = FXCollections.observableArrayList();
        citizenList.forEach(citizen -> {citizenModels.add(CitizenModel.convert(citizen));});
        return citizenModels;
    }

    public void removeStudentToCitizen() {
    }

    public void addStudentToCitizen() {

    }


    public CitizenModel getSelectedCitizen() {
        return selectedCitizen;
    }

    public void setSelectedCitizen(CitizenModel selectedCitizen) {
        this.selectedCitizen = selectedCitizen;
    }

    public ObservableList<AccountModel> getStudentsForCitizen() {
        ObservableList<AccountModel> students = FXCollections.observableArrayList();
        List<Account> accounts = manager.getGroupMembers();
        accounts.forEach(account -> {students.add(new AccountModel(account));});
        return students;
    }
}
