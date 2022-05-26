package Application.GUI.Controllers.dashboard;

import Application.BE.Citizen;

import Application.BLL.TeacherDataManager;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
import Application.Utility.GUIUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
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
    }

    public void onDeleteCitizen(ActionEvent event)
    {
        try
        {
            dataManager.deleteCitizen(availableCitizens.getSelectionModel().getSelectedItem().getBeCitizen());
        } catch (SQLException sqlException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            sqlException.printStackTrace();
        } catch (IllegalArgumentException illegalArgumentException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Ingen borger valgt.");
            illegalArgumentException.printStackTrace();
        }
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

        try {
            this.listViewStudentsForCitizen.setItems(FXCollections.observableArrayList(new AssignedCitizenDAO().read(availableCitizens.getSelectionModel().getSelectedItem())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblCitizenName.setText(selected.getFirstName() + " " + selected.getLastName());
        lblAge.setText(Integer.toString(selected.getAge()));
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
