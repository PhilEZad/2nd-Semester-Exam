package Application.GUI.Models.ControllerModels;

import Application.GUI.Models.CitizenModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherViewModel {

    private CitizenModel selectedCitizenTemplateModel;


    //Students
    public void viewStudentCases() {
        //TODO
    }

    public void studentSettings() {
    }

    public void removeCitizenToStudent() {
    }

    public void addCitizenToStudent() {
    }

    public void viewStudentsWork() {
    }




    // Citizens
    public void generalInfo() {
    }

    public void journal() {
    }

    public void removeStudentToCitizen() {
    }

    public void addStudentToCitizen() {
    }

    public void citizensSearch() {
    }



    // Citizen Template

    public void citizenTemplateSearch() {
    }

    public void removeCitizenTemplateContactInfo() {
    }

    public void addCitizenTemplateContactInfo() {
    }

    public void citizenTemplateChangeJournal() {
    }

    public void onCitizenTemplateEditBaseData() {
    }

    public ObservableList getCitizenTemplates() {
        ObservableList<CitizenModel> citizenTemplates = FXCollections.observableArrayList();
        return citizenTemplates;
    }

    public void setSelectedCitizenTemplateModel(CitizenModel selectedCitizenTemplateModel) {
        this.selectedCitizenTemplateModel = selectedCitizenTemplateModel;
    }

    public CitizenModel getSelectedCitizenTemplateModel() {
        return selectedCitizenTemplateModel;
    }
}
