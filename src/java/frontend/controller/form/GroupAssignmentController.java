package frontend.controller.form;

import frontend.model.UserInfoModel;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupAssignmentController implements Initializable
{
    @FXML public TableView<UserInfoModel> tblAssignedStudents;
    @FXML public TableColumn<UserInfoModel, StringProperty> clmAssignedStudentName;

    @FXML public TableView<UserInfoModel> tblAvailableStudents;
    @FXML public TableColumn<UserInfoModel, StringProperty> clmAvailableStudentName;

    @FXML public TextField txtSearch;

    @FXML public ComboBox comboBoxClasses;
    @FXML public ComboBox comboBoxCases;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void add(ActionEvent actionEvent)
    {
        // add selected student to group
    }

    @FXML
    public void remove(ActionEvent actionEvent)
    {
        // remove selected student from group
    }

    @FXML
    public void cancel(ActionEvent actionEvent)
    {
        // ignore changes
    }

    @FXML
    public void save(ActionEvent actionEvent)
    {
        // commit changes
    }


}
