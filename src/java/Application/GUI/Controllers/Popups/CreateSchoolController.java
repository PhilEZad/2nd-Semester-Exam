package Application.GUI.Controllers.Popups;

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

public class CreateSchoolController implements Initializable {

    @FXML
    private TextField txtSchoolName;
    @FXML
    private TextField txtSchoolZipCode;
    @FXML
    private Button btnCancel;

    SchoolModel schoolDAO = new SchoolModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtSchoolZipCode.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter()));
    }

    public void createSchool(ActionEvent actionEvent) {
        schoolDAO.create(txtSchoolName.getText(), Integer.parseInt(txtSchoolZipCode.getText()));

        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * An interger filter, for use in a textFormatter. Only allows whole numbers.
     * @return
     */
    private UnaryOperator<TextFormatter.Change> integerFilter(){
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            //if (newText.matches("-?([1-9][0-9]*)?")) {
            if (newText.matches("-?([1-9][0-9]*)?")) {

                return change;
            }
            return null;
        };

        return integerFilter;
    }
}
