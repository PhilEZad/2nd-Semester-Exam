package Application.GUI.Controllers.Popups;

import Application.BLL.AdminDataManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditStudentController implements Initializable {
    public TextField txtFieldFirstName;
    public TextField txtFieldLastName;
    public TextField txtFieldEmail;
    public TextField txtFieldUsername;
    public PasswordField passwordField;
    private AdminDataManager adminDataManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminDataManager = new AdminDataManager();
    }

    public void onSaveStudent(ActionEvent actionEvent) {
    }

    public void onCancel(ActionEvent actionEvent) {
    }

}
