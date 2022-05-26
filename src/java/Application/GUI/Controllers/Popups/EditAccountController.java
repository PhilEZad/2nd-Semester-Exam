package Application.GUI.Controllers.Popups;

import Application.BE.Account;
import Application.BLL.AdminDataManager;
import Application.GUI.Models.AccountModel;
import Application.Utility.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditAccountController implements Initializable {

    @FXML public Label lblHeaderTitle;

    @FXML public TextField txtFieldUsername;
    @FXML public TextField txtFieldFirstName;
    @FXML public TextField txtFieldLastName;
    @FXML public TextField txtFieldEmail;
    @FXML public TextField passwordField;

    @FXML public Button btnSaveStudent;
    @FXML public Button btnCancel;

    AdminDataManager dataManager = new AdminDataManager();

    AccountModel account;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initBundle(resources);

        if (account.getIsTeacher())
        {
            lblHeaderTitle.setText("Rediger Lærer");
        };

        txtFieldUsername.setText(account.getAccountName());
        txtFieldFirstName.setText(account.getFirstName());
        txtFieldLastName.setText(account.getLastName());
        txtFieldEmail.setText(account.getEmail());
    }

    public void onSaveStudent(ActionEvent actionEvent)
    {
        try {
            if (account.getIsTeacher()) {
                dataManager.updateTeacher(new Account(
                        account.getId(),
                        txtFieldUsername.getText(),
                        account.getPassword(),
                        txtFieldFirstName.getText(),
                        txtFieldLastName.getText(),
                        txtFieldEmail.getText(),
                        account.getSchool(),
                        account.getIsTeacher(),
                        account.getIsAdmin()
                ));
            } else {
                dataManager.updateStudent(new Account(
                        account.getId(),
                        txtFieldUsername.getText(),
                        account.getPassword(),
                        txtFieldFirstName.getText(),
                        txtFieldLastName.getText(),
                        txtFieldEmail.getText(),
                        account.getSchool(),
                        account.getIsTeacher(),
                        account.getIsAdmin()
                ));
            }
        } catch (SQLException sqlException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            sqlException.printStackTrace();
        } catch (AccessDeniedException accessDeniedException) {
            accessDeniedException.printStackTrace();
        }

        Stage stage = (Stage) btnSaveStudent.getScene().getWindow();
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private void initBundle(ResourceBundle resource)
    {
        if (!(resource.getObject("selectedModel") == null))
        {
            account = (AccountModel) resource.getObject("selectedModel");
        }
    }
}
