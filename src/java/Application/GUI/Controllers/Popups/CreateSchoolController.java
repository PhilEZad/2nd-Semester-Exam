package Application.GUI.Controllers.Popups;

import Application.GUI.Models.SchoolModel;
import Application.Utility.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class CreateSchoolController implements Initializable {

    @FXML
    private TextField txtSchoolName;
    @FXML
    private TextField txtSchoolZipCode;
    @FXML
    private Button btnCancel;

    SchoolModel schoolDAO = new SchoolModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtSchoolZipCode.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, GUIUtils.getIntegerFilter()));
    }

    public void createSchool(ActionEvent actionEvent) {
        schoolDAO.create(txtSchoolName.getText(), Integer.parseInt(txtSchoolZipCode.getText()));

        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
