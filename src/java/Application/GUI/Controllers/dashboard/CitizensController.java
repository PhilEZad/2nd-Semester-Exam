package Application.GUI.Controllers.dashboard;

import Application.BE.Citizen;

import Application.BLL.TeacherDataManager;
import Application.DAL.CitizenDAO;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CitizensController implements Initializable
{
    TeacherDataManager dataManager = new TeacherDataManager();

    public AnchorPane anchorPaneCitizensDashboard;

    public ListView<CitizenModel> availableCitizens;
    public ListView listViewStudentsForCitizen;


    public Button btnRemoveStudentToCitizen;
    public Button btnAddStudentToCitizen;
    public TextField txtFieldCitizensSearch;
    public Label lblCitizenName;
    public Label lblAge;

    public Button btnJournal;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initTables();
        initListeners();
    }

    public void initTables()
    {
        searchTable(txtFieldCitizensSearch, availableCitizens , dataManager.getAllCitizens());
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

        //sortedUsers.comparatorProperty().bind(table.comparatorProperty());

        return sortedUsers;
    }
}
