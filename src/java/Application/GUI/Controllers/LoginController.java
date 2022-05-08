package Application.GUI.Controllers;

import Application.GUI.Models.AccountModel;
import Application.GUI.Models.UserType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    private AccountModel account;

    @FXML
    public AnchorPane LoginScene;

    @FXML
    public TextField txtUsername;

    @FXML
    public TextField txtPwd;

    @FXML
    public Button btnSubmit;

    @FXML
    public ComboBox<UserType> cbUserTypeSelect;

    public LoginController()
    {
        this.txtUsername = new TextField();
        this.txtPwd = new TextField();
        this.btnSubmit = new Button();
        this.cbUserTypeSelect = new ComboBox<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cbUserTypeSelect.setItems(FXCollections.observableArrayList(UserType.values()));
    }

    private ResourceBundle createResourceBundle()
    {
        return new ListResourceBundle() {
            @Override
            protected Object[][] getContents() {
                return new Object[][] {
                    {"account", account}
                };
            }
        };
    }

    public void onSubmit(ActionEvent event) throws IOException {

        account = new AccountModel();

        if (account.authenticate(this.txtUsername.getText(), this.txtPwd.getText()))
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/views/TeacherView.fxml")), createResourceBundle());

            // switch to dashboard
            LoginScene.getScene().setRoot(root);
        }
        else
        {
            System.err.println("wrong credentials");
            // post error message
                // (wrong username / password)
        }
    }
}
