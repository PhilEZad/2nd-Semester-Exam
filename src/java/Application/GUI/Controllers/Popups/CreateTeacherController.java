package Application.GUI.Controllers.Popups;

import Application.BE.Location;
import Application.BE.School;
import Application.GUI.Models.AccountModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class CreateTeacherController
{
    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldPassword;
    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldLastName;
    @FXML public TextField txtFieldEmail;

    AccountModel accountDAO = new AccountModel();

    public void onSaveTeacher(ActionEvent actionEvent)
    {

        // FIXME: 03/05/2022 -- Dummy School
        School school = new School(1, "Dummy School", new Location(6800, "Varde"));

        accountDAO.createAccount(
                txtFieldUsername.getText(),
                txtFieldPassword.getText(),
                txtFieldFirstName.getText(),
                txtFieldLastName.getText(),
                txtFieldEmail.getText(),
                school,
                1
        );
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void onCancel(ActionEvent actionEvent)
    {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
