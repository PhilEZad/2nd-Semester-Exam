package Application.GUI.Controllers.Popups;

import Application.BE.School;
import Application.GUI.Models.SchoolModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class EditSchoolController implements Initializable {

    @FXML Button btnCancel;
    @FXML Button btnSave;
    @FXML TextField txtSchoolName;
    @FXML TextField txtSchoolZipCode;

    SchoolModel school;

    public EditSchoolController(SchoolModel school)
    {
        this.school = school;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        txtSchoolName.setText(school.getName().get());
        txtSchoolZipCode.setText(txtSchoolZipCode.getText());
    }

    public void saveEdits(ActionEvent actionEvent)
    {
        school.update(new School(school.getSchoolID(), txtSchoolName.getText(), Integer.parseInt(txtSchoolZipCode.getText()), ""));
        
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    public void cancelEdits(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private UnaryOperator<TextFormatter.Change> integerFilter(){
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {

                return change;
            }
            return null;
        };

        return integerFilter;
    }
}
