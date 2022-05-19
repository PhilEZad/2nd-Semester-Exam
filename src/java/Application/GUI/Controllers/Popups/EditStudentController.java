package Application.GUI.Controllers.Popups;

import Application.BE.Account;
import Application.GUI.Models.AccountModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditStudentController implements Initializable {

    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldLastName;
    @FXML public TextField txtFieldEmail;
    @FXML public TextField passwordField;

    @FXML public Button btnSaveStudent;
    @FXML public Button btnCancel;

    AccountModel student;

    public EditStudentController()
    {

    }

    public EditStudentController(AccountModel student)
    {
        this.student = student;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        txtFieldUsername.setText(student.getAccountName());
        txtFieldFirstName.setText(student.getFirstName());
        txtFieldLastName.setText(student.getLastName());
        txtFieldEmail.setText(student.getEmail());
    }

    public void onSaveStudent(ActionEvent actionEvent)
    {
        student.updateAccount(new Account(
                student.getId(),
                txtFieldUsername.getText(),
                student.getPassword(),
                txtFieldFirstName.getText(),
                txtFieldLastName.getText(),
                txtFieldEmail.getText(),
                student.getSchool(),
                student.getAuthorization()
        ));

        Stage stage = (Stage) btnSaveStudent.getScene().getWindow();
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}