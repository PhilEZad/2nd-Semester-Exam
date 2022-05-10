package Application.GUI.Controllers.dashboard;

import Application.BE.Citizen;
import Application.DAL.TemplateMethod.CitizenDAO;
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
import java.util.ResourceBundle;

public class Citizens implements Initializable
{
    public AnchorPane anchorPaneCitizensDashboard;

    public ListView<Citizen> AvailableCitizens;
    public ListView listViewContactInfo;
    public ListView listViewStudentsForCitizen;

    public Button btnRemoveStudentToCitizen;
    public Button btnAddStudentToCitizen;
    public TextField txtFieldCitizensSearch;
    public Button btnCitizensSearch;
    public Label lblCitizenName;
    public Label lblAge;
    public Label lblBirthdateYear;
    public Label lblAddress;
    public Label lblHelpStatus;
    public Label lblCivilianStatus;
    public Button btnGeneralInfo;
    public Button btnJournal;

    private ObservableList<Citizen> citizens;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // TODO: 09-05-2022 abstract to a model

        CitizenDAO dao = new CitizenDAO();
        citizens = FXCollections.observableList(dao.readAll(0));

        AvailableCitizens = new ListView<>();
        AvailableCitizens.getSelectionModel().selectFirst();

       // lblCitizenName.textProperty().bindBidirectional(AvailableCitizens.getSelectionModel().getSelectedItem().getFirstname());
    }

    public void onRemoveStudentToCitizen(ActionEvent event) {
    }

    public void onAddStudentToCitizen(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("/views/Popups/AddToTaskView.fxml"));

        stage.setTitle("???");

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onCitizensSearch(ActionEvent event) {
    }

    public void onGeneralInfo(ActionEvent event) {
    }

    public void onJournal(ActionEvent event) {
    }


}
