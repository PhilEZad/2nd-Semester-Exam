package Application.GUI.Controllers;

import Application.BLL.SessionManager;
import Application.GUI.Models.SessionModel;
import Application.Utility.AccountType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    public RadioButton rbStudent;
    public RadioButton rbTeacher;
    public RadioButton rbAdmin;

    ToggleGroup toggleGroup = new ToggleGroup();

    public TextField username;
    public TextField password;

    private SessionModel account;

    @FXML
    public BorderPane root;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        toggleGroup = new ToggleGroup();

        toggleGroup.getToggles().add(rbStudent);
        toggleGroup.getToggles().add(rbAdmin);
        toggleGroup.getToggles().add(rbTeacher);

        System.out.println("admin hash \"" + SessionManager.createToken("admin", "admin")+"\"");

        toggleGroup.selectToggle(rbStudent);
    }

    public void onSubmit(ActionEvent actionEvent) throws IOException
    {
        AccountType accountType = rbAdmin.isSelected() ? AccountType.ADMIN : rbTeacher.isSelected() ? AccountType.TEACHER : rbStudent.isSelected() ? AccountType.STUDENT : AccountType.NONE;

        if (SessionManager.tryBeginSession(accountType, this.username.getText(), this.password.getText()))
        {
            String url = switch (accountType)
                    {
                        case ADMIN -> "/Views/AdminView.fxml";
                        case TEACHER -> "/Views/TeacherView.fxml";
                        case STUDENT -> "/Views/StudentView.fxml";
                        case NONE -> null;
                    };

            if (url != null) root.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource(url))));
        }
    }
}
