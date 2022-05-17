package Application.GUI.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditStudentController {

    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldLastName;
    @FXML public TextField txtFieldEmail;
    @FXML public TextField passwordField;

    @FXML public Button btnSaveStudent;
    @FXML public Button btnCancel;

    public void onSaveStudent(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnSaveStudent.getScene().getWindow();
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
