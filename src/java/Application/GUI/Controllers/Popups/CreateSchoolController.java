package Application.GUI.Controllers.Popups;

import Application.BE.School;
import Application.BLL.AdminDataManager;
import Application.Utility.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateSchoolController implements Initializable {

    @FXML
    private TextField txtSchoolName;
    @FXML
    private TextField txtSchoolZipCode;
    @FXML
    private Button btnCancel;

    AdminDataManager dataManager = new AdminDataManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtSchoolZipCode.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, GUIUtils.getIntegerFilter()));
    }

    public void createSchool(ActionEvent actionEvent) {
        try
        {
            dataManager.createSchool(new School(-1, txtSchoolName.getText(), Integer.parseInt(txtSchoolZipCode.getText()), ""));

            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        } catch (SQLException sqlException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            sqlException.printStackTrace();
        }
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
