package Application.GUI.Controllers.Popups;

import Application.BE.School;
import Application.GUI.Models.AccountModel;
import Application.Utility.Strings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class CreateTeacherController
{
    @FXML public TextField txtFieldLogin;
    @FXML public TextField txtFieldPassword;
    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldSurname;
    @FXML public TextField txtFieldEmail;

    AccountModel accountDAO = new AccountModel();

    public void onSaveCreateTeacher(ActionEvent actionEvent) {

        // FIXME: 03/05/2022 -- Dummy School
        School school = new School(1, "Dummy School", 6715, "NotARealCity");
        String alertInfo = "login navn er allerede taget,";

        if (!accountDAO.createAccount(
                txtFieldLogin.getText(),
                Strings.generateAccessToken(txtFieldLogin.getText(),txtFieldPassword.getText()),
                txtFieldFirstName.getText(),
                txtFieldSurname.getText(),
                txtFieldEmail.getText(),
                school,
                1))
        {
            new Alert(Alert.AlertType.INFORMATION, alertInfo, ButtonType.OK);
        }
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void onCancelCreateTeacher(ActionEvent actionEvent)
    {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
