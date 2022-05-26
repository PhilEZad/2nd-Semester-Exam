package Application.GUI.Controllers.Popups;

import Application.BE.Account;
import Application.BE.School;
import Application.BLL.AdminDataManager;
import Application.Utility.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CreateStudentController implements Initializable {

    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldLastName;
    @FXML public TextField txtFieldEmail;
    @FXML public TextField txtFieldUsername;
    @FXML public PasswordField passwordField;

    private AdminDataManager adminDataManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminDataManager = new AdminDataManager();
    }


    public void onSaveStudent(ActionEvent event) {
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastName.getText();
        String email = txtFieldEmail.getText();
        String login = txtFieldUsername.getText();
        String password = passwordField.getText();

        // FIXME: 03/05/2022 -- Dummy School
        try {
            School school = new School(1, "Dummy School", 6700, "Esbjerg");

            adminDataManager.createStudent(new Account(-1, login, password, firstName, lastName, email, school, false, false));
            //TODO: add getSchool() and implement salt for hashing

            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (SQLException sqlException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            sqlException.printStackTrace();
        } catch (AccessDeniedException accessDeniedException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Du har ikke adgang til denne funktion.");
        }
    }

    public void onCancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
