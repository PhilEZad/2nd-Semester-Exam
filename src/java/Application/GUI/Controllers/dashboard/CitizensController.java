package Application.GUI.Controllers.dashboard;

import Application.BE.Account;
import Application.BE.Citizen;

import Application.DAL.CitizenDAO;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.ControllerModels.CitizensControllerModel;
import Application.GUI.Models.SessionModel;
import Application.Utility.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class CitizensController implements Initializable
{
    public AnchorPane anchorPaneCitizensDashboard;

    public ListView<CitizenModel> availableCitizens;
    public ListView<AccountModel> listViewStudentsForCitizen;

    public Button btnRemoveStudentToCitizen;
    public Button btnAddStudentToCitizen;
    public TextField txtFieldCitizensSearch;
    public Label lblCitizenName;
    public Label lblAge;
    public Button btnJournal;


    private CitizensControllerModel model = new CitizensControllerModel();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       //initCitizensList();

    }

    private void initCitizensList(){
        availableCitizens.setItems(model.getAllCitizenModels());

        availableCitizens.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                model.setSelectedCitizen(newValue);
                setDataToCitizen();
            }
        });
    }

    private void setDataToCitizen() {
        lblAge.setText(model.getSelectedCitizen().getAge() + "");
        lblCitizenName.setText(model.getSelectedCitizen().getFirstName() + " " + model.getSelectedCitizen().getLastName());
        listViewStudentsForCitizen.setItems(model.getStudentsForCitizen());
    }


    public void onRemoveStudentToCitizen(ActionEvent event) {
        model.removeStudentToCitizen();
        listViewStudentsForCitizen.getItems().remove(listViewStudentsForCitizen.getSelectionModel().getSelectedItem());
    }

    public void onAddStudentToCitizen(ActionEvent event) {
        Stage stage = new Stage();
        if (availableCitizens.getSelectionModel().getSelectedItem() != null && listViewStudentsForCitizen.getSelectionModel().getSelectedItem() != null) {
            Account selectedStudent = SessionModel.getCurrent();
            CitizenModel selectedCitizenModel = availableCitizens.getSelectionModel().getSelectedItem();
            Parent root = null;

            try {
                ResourceBundle resources = new ListResourceBundle()
                {
                    @Override
                    protected Object[][] getContents()
                    {
                        return new Object[][]{  {"selectedCitizen", selectedCitizenModel}, {"accountType", "observer"}};
                    }
                };

                root = FXMLLoader.load(getClass().getResource("/Views/Popups/AssignmentView.fxml"), resources);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle(selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + " - " + selectedCitizenModel.getFirstName() + " " + selectedCitizenModel.getLastName());
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Ingen valgt");
            alert.setContentText("Vælg venligst en borger og en elev");
            alert.showAndWait();
        }
        stage.setOnHiding(event1 -> model.addStudentToCitizen(SessionModel.getCurrent()));
    }

    public void onJournal(ActionEvent event) {
        if (availableCitizens.getSelectionModel().getSelectedItem() != null) {
            Account selectedStudent = SessionModel.getCurrent();
            CitizenModel selectedCitizenModel = availableCitizens.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            Parent root = null;

            try {
                ResourceBundle resources = new ListResourceBundle()
                {
                    @Override
                    protected Object[][] getContents()
                    {
                        return new Object[][]{  {"selectedCitizen", selectedCitizenModel}, {"accountType", "observer"}};
                    }
                };

                root = FXMLLoader.load(getClass().getResource("/Views/Popups/CitizenDetailsView.fxml"), resources);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle(selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + " - " + selectedCitizenModel.getFirstName() + " " + selectedCitizenModel.getLastName());
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Ingen valgt");
            alert.setContentText("Vælg venligst en borger");
            alert.showAndWait();
        }
    }

    public void onDeleteCitizen(ActionEvent event) {


        if (availableCitizens.getSelectionModel().getSelectedItem() != null) {
            model.deleteCitizen(availableCitizens.getSelectionModel().getSelectedItem());
            availableCitizens.getItems().remove(availableCitizens.getSelectionModel().getSelectedItem());
        }
    }
}
