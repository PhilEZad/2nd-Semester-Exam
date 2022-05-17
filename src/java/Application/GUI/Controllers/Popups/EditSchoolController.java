package Application.GUI.Controllers.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditSchoolController implements Initializable {

    @FXML Button btnCancel;
    @FXML Button btnSave;
    @FXML TextField txtSchoolName;
    @FXML TextField txtSchoolZipCode;

    public EditSchoolController()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void saveEdits(ActionEvent actionEvent)
    {

    }

    public void cancelEdits(ActionEvent actionEvent)
    {

    }
}
