package Application.GUI.Controllers.dashboard;

import Application.BE.Citizen;

import Application.DAL.CitizenDAO;
import Application.Utility.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CitizensController implements Initializable
{
    public AnchorPane anchorPaneCitizensDashboard;

    public ListView<Citizen> AvailableCitizens;
    public ListView listViewStudentsForCitizen;

    public Button btnRemoveStudentToCitizen;
    public Button btnAddStudentToCitizen;
    public TextField txtFieldCitizensSearch;
    public Button btnCitizensSearch;
    public Label lblCitizenName;
    public Label lblAge;

    public Button btnGeneralInfo;
    public Button btnJournal;

    private ObservableList<Citizen> citizens;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // TODO: 09-05-2022 abstract to a model

        CitizenDAO dao = new CitizenDAO();
        //citizens = FXCollections.observableList(dao.readAll(0));

        AvailableCitizens = new ListView<>();
        AvailableCitizens.getSelectionModel().selectFirst();
        GUIUtils.searchListener(txtFieldCitizensSearch, AvailableCitizens);

       // lblCitizenName.textProperty().bindBidirectional(AvailableCitizens.getSelectionModel().getSelectedItem().getFirstname());
    }

    public void onRemoveStudentToCitizen(ActionEvent event) {
    }

    public void onAddStudentToCitizen(ActionEvent event) {
    }

    public void onCitizensSearch(ActionEvent event) {
    }

    public void onGeneralInfo(ActionEvent event) {
    }

    public void onJournal(ActionEvent event) {
    }


}
