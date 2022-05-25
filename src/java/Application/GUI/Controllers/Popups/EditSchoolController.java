package Application.GUI.Controllers.Popups;

import Application.BE.School;
import Application.GUI.Models.SchoolModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class EditSchoolController implements Initializable {

    @FXML Button btnCancel;
    @FXML Button btnSave;
    @FXML TextField txtSchoolName;
    @FXML TextField txtSchoolZipCode;

    SchoolModel school;

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
        school.update(new School(school.getId(), txtSchoolName.getText(), new Location(Integer.parseInt(txtSchoolZipCode.getText()), "")));
        
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
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
