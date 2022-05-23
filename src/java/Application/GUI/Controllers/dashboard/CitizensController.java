package Application.GUI.Controllers.dashboard;

import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.ControllerModels.CitizensControllerModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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
    }

    public void onAddStudentToCitizen(ActionEvent event) {
    }

    public void onJournal(ActionEvent event) {
    }

    public void onDeleteCitizen(ActionEvent event) {
    }
}
