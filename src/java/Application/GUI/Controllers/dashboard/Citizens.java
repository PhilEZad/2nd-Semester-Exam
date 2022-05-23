package Application.GUI.Controllers.dashboard;

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
import java.util.ArrayList;
import java.util.List;
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

        /*CitizenDAO dao = new CitizenDAO();
        citizens = FXCollections.observableList(dao.readAll(0));

        AvailableCitizens = new ListView<>();
        AvailableCitizens.getSelectionModel().selectFirst();
         */
        mockUp();

       // lblCitizenName.textProperty().bindBidirectional(AvailableCitizens.getSelectionModel().getSelectedItem().getFirstname());
    }

    public void onRemoveStudentToCitizen(ActionEvent event) {
    }

    public void onAddStudentToCitizen(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("/views/Popups/AddToTaskView.fxml"));

        stage.setTitle("Tilføj Studerende");

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onCitizensSearch(ActionEvent event) {
    }

    public void onGeneralInfo(ActionEvent event) {
    }

    public void onJournal(ActionEvent event) {
    }

    public void mockUp()
    {
        Citizen arne = new Citizen(1, "Arne", "Johansen", 68, 0, 5, 6710, 1);
        Citizen bodil = new Citizen(2,"Bodil", "Stender", 57, 0,53, 6715,1);
        Citizen karsten = new Citizen(3, "Karsten", "Andersen", 47, 0, 32, 6700, 1);
        ObservableList citizenList = FXCollections.observableArrayList();
        citizenList.add(arne);
        citizenList.add(bodil);
        citizenList.add(karsten);
        AvailableCitizens.setItems(citizenList);




        lblCitizenName.setText("Arne Johansen");
        lblAge.setText("68");
        lblBirthdateYear.setText("1954");
        lblAddress.setText("granly alle 5c");
        lblHelpStatus.setText("aktiv");
        lblCivilianStatus.setText("gift");

        listViewContactInfo.getItems().add("Tlf: 52 74 68 88");
        listViewContactInfo.getItems().add("");
        listViewContactInfo.getItems().add("Kontant Personer:");
        listViewContactInfo.getItems().add("Christian Sandbæk");
        listViewContactInfo.getItems().add("Tlf: 50 74 67 77");
    }

}
