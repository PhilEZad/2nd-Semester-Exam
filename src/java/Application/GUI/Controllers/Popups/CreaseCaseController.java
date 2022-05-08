package Application.GUI.Controllers.Popups;

import Application.BE.Inquiry;
import Application.BLL.TeacherDataManager;
import Application.DAL.InquiryDAO;
import Application.DAL.TemplatePatternDAO;
import Application.GUI.Models.CaseModel;
import Application.GUI.Models.InquiryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreaseCaseController implements Initializable {

    @FXML public Button btnCancel;
    @FXML public Button btnSave;
    @FXML public ComboBox<Inquiry> comboBoxInquiry;
    public TextArea medicalDiagnose;
    public TextArea inquiryReason;

    CaseModel caseModel = new CaseModel();

    ObservableList<Inquiry> inquiryList;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        inquiryList = FXCollections.observableList(caseModel.getAllInquries());
        initComboBox();
    }

    public void save(ActionEvent actionEvent)
    {
        caseModel.createCase(-1, inquiryReason.getText(), medicalDiagnose.getText(), comboBoxInquiry.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }


    public void cancel(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private void initComboBox()
    {
        comboBoxInquiry.setItems(inquiryList);
    }

}
