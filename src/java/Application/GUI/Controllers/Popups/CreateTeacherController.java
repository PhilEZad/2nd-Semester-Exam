package Application.GUI.Controllers.Popups;

import Application.BE.Account;
import Application.BE.School;
import Application.BLL.AdminDataManager;
import Application.Utility.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CreateTeacherController
{
    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldPassword;
    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldLastName;
    @FXML public TextField txtFieldEmail;

    // FIXME: 20/05/2022 Detatch DAO layer
    AdminDataManager accountDAO = new AdminDataManager();

    public void onSaveTeacher(ActionEvent actionEvent)
    {

        // FIXME: 03/05/2022 -- Dummy School
        School school = new School(1, "Dummy School", 6800, "Varde");
        try {
            accountDAO.createTeacher(new Account(
                            -1,
                            txtFieldUsername.getText(),
                            txtFieldPassword.getText(),
                            txtFieldFirstName.getText(),
                            txtFieldLastName.getText(),
                            txtFieldEmail.getText(),
                            school,
                            true,
                            false
                    )
            );
        } catch (SQLException sqlException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            sqlException.printStackTrace();
        }
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void onCancel(ActionEvent actionEvent)
    {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
