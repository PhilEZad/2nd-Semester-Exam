package Application.GUI.Controllers.Popups;

import Application.BE.Account;
import Application.BE.Location;
import Application.BE.School;
import Application.BLL.AdminDataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
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
        School school = new School(1, "Dummy School", new Location(0));

        try {
            adminDataManager.createAccount(login, Strings.generateAccessToken(login, password), firstName, lastName, email, school, 2) ;
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("brugernavnet er allerede taget, lav venligst en anden.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());
            alert.showAndWait();
        }
        //TODO: add getSchool() and implement salt for hashing

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void onCancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
