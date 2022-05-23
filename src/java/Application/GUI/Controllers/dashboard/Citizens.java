package Application.GUI.Controllers.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Citizens implements Initializable
{
    public AnchorPane anchorPaneCitizensDashboard;

    public ListView AvailableCitizens;
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

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
