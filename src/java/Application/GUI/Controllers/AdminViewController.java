package Application.GUI.Controllers;

import Application.BE.Account;
import Application.GUI.Models.SchoolModel;
import Application.GUI.Models.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    @FXML
    private TableView<StudentModel> tblViewStudent;
    @FXML
    private TableColumn<StudentModel, String> tblClmStudentFirstName;
    @FXML
    private TableColumn<StudentModel, String> tblClmStudentLastName;
    @FXML
    private TableColumn<StudentModel, String> tblClmStudentEmail;
    @FXML
    private TableColumn<StudentModel, String> tblClmStudentClass;

    @FXML
    private TableView<Account> tblViewTeacher;
    @FXML
    private TableColumn<Account, String> tblClmTeacherFirstName;
    @FXML
    private TableColumn<Account, String> tblClmTeacherLastName;
    @FXML
    private TableColumn<Account, String> tblClmTeacherEmail;

    @FXML
    private TableView<SchoolModel> tblViewSchool;
    @FXML
    private TableColumn<SchoolModel, String> tblClmSchoolName;
    @FXML
    private TableColumn<SchoolModel, Number> tblClmSchoolZipCode;
    @FXML
    private TableColumn<SchoolModel, String> tblClmSchoolCity;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableViews();
    }

    private void initTableViews()
    {
        tblClmSchoolName.setCellValueFactory(param -> param.getValue().getName());
        tblClmSchoolZipCode.setCellValueFactory(param -> param.getValue().getZipCode());
        tblClmSchoolCity.setCellValueFactory(param -> param.getValue().getCity());
    }

    public void openCreateSchool(ActionEvent actionEvent) {
    }
}
