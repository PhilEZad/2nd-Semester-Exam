package Application.GUI.Controllers;

import Application.GUI.Models.SessionModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
    private SessionModel account;

    @FXML
    public AnchorPane LoginScene;

    @FXML
    public TextField txtUsername;

    @FXML
    public TextField txtPwd;

    @FXML
    public Button btnSubmit;


    public LoginController()
    {
        this.txtUsername = new TextField();
        this.txtPwd = new TextField();
        this.btnSubmit = new Button();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
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

        account = new SessionModel();

        if (account.authenticate(this.txtUsername.getText(), this.txtPwd.getText()))
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/views/TeacherView.fxml")), createResourceBundle());

            // switch to dashboard
            LoginScene.getScene().setRoot(root);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Fejl");
            alert.setHeaderText("Fejl i login");
            alert.setContentText("Brugernavn eller kodeord er forkert");
            alert.showAndWait();
        }
    }
}
