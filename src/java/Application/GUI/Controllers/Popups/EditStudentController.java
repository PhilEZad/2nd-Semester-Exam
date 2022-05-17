package Application.GUI.Controllers.Popups;

import Application.BLL.AdminDataManager;
import Application.GUI.Models.AccountModel;
import Application.Utility.Strings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditStudentController implements Initializable {
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtFieldFirstName;
    @FXML
    private TextField txtFieldLastName;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private CheckBox checkPassword;
    private AdminDataManager adminDataManager;
    private AccountModel student;
    private Strings hashing;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminDataManager = new AdminDataManager();
        this.student = (AccountModel) resources.getObject("student");
        hashing = new Strings();

        txtFieldFirstName.setText(student.getFirstName());
        txtFieldLastName.setText(student.getLastName());
        txtFieldEmail.setText(student.getEmail());
        txtFieldPassword.setText(student.getAccount().getPassword());
    }

    public void onSaveStudent(ActionEvent actionEvent) {
        student.getAccount().setFirstName(txtFieldFirstName.getText());
        student.getAccount().setLastName(txtFieldLastName.getText());
        student.getAccount().setEmail(txtFieldEmail.getText());
    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void onChangeLogin(ActionEvent actionEvent) {

        if (checkPassword.isSelected())
        {
            adminDataManager.changePassword(hashing.generateAccessToken(student.getAccount().getLogin(), txtFieldPassword.getText()), student.getAccount());
        }
    }
}
