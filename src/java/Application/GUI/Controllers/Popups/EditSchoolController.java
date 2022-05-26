package Application.GUI.Controllers.Popups;

import Application.BE.School;
import Application.BLL.AdminDataManager;
import Application.GUI.Models.SchoolModel;
import Application.Utility.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class EditSchoolController implements Initializable {

    @FXML Button btnCancel;
    @FXML Button btnSave;
    @FXML TextField txtSchoolName;
    @FXML TextField txtSchoolZipCode;

    SchoolModel school;

    AdminDataManager dataManager;

    public EditSchoolController()
    {
        dataManager = new AdminDataManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initBundle(resources);

        txtSchoolZipCode.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter()));

        txtSchoolName.setText(school.getName().get());
        txtSchoolZipCode.setText(Integer.toString(school.getZipCode().get()));


    }

    public void saveEdits(ActionEvent actionEvent)
    {
        try
        {
            dataManager.updateSchool(new School(school.getId(), txtSchoolName.getText(), Integer.parseInt(txtSchoolZipCode.getText()), ""));

            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        } catch (SQLException sqlException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            sqlException.printStackTrace();
        }
    }

    public void cancelEdits(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private UnaryOperator<TextFormatter.Change> integerFilter(){
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();

            if (newText.matches("[0-9]+")) {

                return change;
            }
            return null;
        };

        return integerFilter;
    }

    private void initBundle(ResourceBundle resource)
    {
        if (!(resource.getObject("selectedModel") == null))
        {
            school = (SchoolModel) resource.getObject("selectedModel");
        }
    }
}
