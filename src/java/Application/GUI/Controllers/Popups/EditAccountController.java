package Application.GUI.Controllers.Popups;

import Application.BE.Account;
import Application.GUI.Models.AccountModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
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

    AccountModel account;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initBundle(resources);

        if (account.getAuthorization() == 1)
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
        account.updateAccount(new Account(
                account.getId(),
                txtFieldUsername.getText(),
                account.getPassword(),
                txtFieldFirstName.getText(),
                txtFieldLastName.getText(),
                txtFieldEmail.getText(),
                account.getSchool(),
                account.
        ));

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
