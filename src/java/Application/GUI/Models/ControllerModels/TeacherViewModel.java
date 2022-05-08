package Application.GUI.Models.ControllerModels;

import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;

public class TeacherViewModel {

    private CitizenTemplateModel selectedCitizenTemplateModel;


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
        ObservableList<CitizenTemplateModel> citizenTemplates = FXCollections.observableArrayList();
        return citizenTemplates;
    }

    public void setSelectedCitizenTemplateModel(CitizenTemplateModel selectedCitizenTemplateModel) {
        this.selectedCitizenTemplateModel = selectedCitizenTemplateModel;
    }

    public CitizenTemplateModel getSelectedCitizenTemplateModel() {
        return selectedCitizenTemplateModel;
    }
}
