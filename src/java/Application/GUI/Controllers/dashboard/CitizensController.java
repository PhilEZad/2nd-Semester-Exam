package Application.GUI.Controllers.dashboard;

import Application.BE.Account;
import Application.BE.Citizen;

import Application.BLL.TeacherDataManager;
import Application.DAL.CitizenDAO;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.ControllerModels.CitizensControllerModel;
import Application.GUI.Models.SessionModel;
import Application.GUI.Models.CitizenModel;
import Application.Utility.GUIUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.util.List;
import java.util.ResourceBundle;

public class CitizensController implements Initializable
{
    TeacherDataManager dataManager = new TeacherDataManager();

    public AnchorPane anchorPaneCitizensDashboard;

    public ListView<CitizenModel> availableCitizens;
    public ListView<AccountModel> listViewStudentsForCitizen;


    public Button btnRemoveStudentToCitizen;
    public Button btnAddStudentToCitizen;
    public TextField txtFieldCitizensSearch;
    public Label lblCitizenName;
    public Label lblAge;


    private CitizensControllerModel model = new CitizensControllerModel();

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

    public void onAddStudentToCitizen(ActionEvent event) 
    {
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
    
    public Button btnJournal;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initTables();
        initListeners();
    }

    public void initTables()
    {
        availableCitizens.setItems(listToObservable(dataManager.getAllCitizens()));
    }

    public void initListeners()
    {
        availableCitizens.getSelectionModel().selectedItemProperty().addListener(citizenSelectionChanged());
    }

    public void onRemoveStudentToCitizen(ActionEvent event)
    {

    }

    public void onAddStudentToCitizen(ActionEvent event)
    {
     //   dataManager.
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

    public void onDeleteCitizen2(ActionEvent event) {

        if (availableCitizens.getSelectionModel().getSelectedItem() != null) {
            model.deleteCitizen(availableCitizens.getSelectionModel().getSelectedItem());
            availableCitizens.getItems().remove(availableCitizens.getSelectionModel().getSelectedItem());
        }
    }

    public void onDeleteCitizen(ActionEvent event)
    {
        dataManager.deleteCitizen(availableCitizens.getSelectionModel().getSelectedItem().getBeCitizen());
    }

    private ChangeListener<CitizenModel> citizenSelectionChanged()
    {
        return new ChangeListener<CitizenModel>() {
            @Override
            public void changed(ObservableValue<? extends CitizenModel> observable, CitizenModel oldValue, CitizenModel newValue)
            {
                updatedSelectedItemBinds();
            }
        };
    }

    public void updatedSelectedItemBinds()
    {
        var selected = this.availableCitizens.getSelectionModel().getSelectedItem();

        if (selected == null)
        {
            return;
        }

        lblCitizenName.setText(selected.getFirstName() + " " + selected.getLastName());
        lblAge.setText(Integer.toString(selected.getAge()));
    }

    private SortedList<CitizenModel> searchTable(TextField searchField, ListView table, ObservableList searchList)
    {
        FilteredList<CitizenModel> filteredData = new FilteredList<>(searchList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (user.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<CitizenModel> sortedUsers = new SortedList<>(filteredData);

        //sortedUsers.comparatorProperty().bind(table.itemsProperty()comparatorProperty());

        return sortedUsers;
    }

    private ObservableList<CitizenModel> listToObservable(List<Citizen> list)
    {
        ObservableList<CitizenModel> citizenModelObservableList = FXCollections.observableArrayList();
        for (Citizen citizen: list)
        {
            CitizenModel citizenModel = new CitizenModel(citizen);
            citizenModelObservableList.add(citizenModel);
        }

        return citizenModelObservableList;
    }
}
