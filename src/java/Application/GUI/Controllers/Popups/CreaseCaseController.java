package Application.GUI.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreaseCaseController implements Initializable {

    @FXML public Button btnCancel;
    @FXML public Button btnSave;
    public TextArea medicalDiagnose;
    public TextArea inquiryReason;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //inquiryList = FXCollections.observableList(caseModel.getAllInquries());
        //initComboBox();
    }

    public void save(ActionEvent actionEvent)
    {
       //caseModel.createCase(-1, inquiryReason.getText(), medicalDiagnose.getText(), comboBoxInquiry.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }


    public void cancel(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }



}
