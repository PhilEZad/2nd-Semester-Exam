package Application.GUI.Controllers.dashboard;

import Application.BE.Account;
import Application.BLL.AdminDataManager;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.SchoolModel;
import Application.GUI.Models.StudentModel;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private TableView<AccountModel> tblViewStudent;
    @FXML
    private TableColumn<AccountModel, String> tblClmStudentFirstName;
    @FXML
    private TableColumn<AccountModel, String> tblClmStudentLastName;
    @FXML
    private TableColumn<AccountModel, String> tblClmStudentEmail;

    @FXML
    private TableView<AccountModel> tblViewTeacher;
    @FXML
    private TableColumn<AccountModel, String> tblClmTeacherFirstName;
    @FXML
    private TableColumn<AccountModel, String> tblClmTeacherLastName;
    @FXML
    private TableColumn<AccountModel, String> tblClmTeacherEmail;

    @FXML
    private TableView<SchoolModel> tblViewSchool;
    @FXML
    private TableColumn<SchoolModel, String> tblClmSchoolName;
    @FXML
    private TableColumn<SchoolModel, Number> tblClmSchoolZipCode;
    @FXML
    private TableColumn<SchoolModel, String> tblClmSchoolCity;



    AdminDataManager dataManager = new AdminDataManager();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableViews();
    }

    private void initTableViews()
    {

        tblClmSchoolName.setCellValueFactory(param -> param.getValue().getName());
        tblClmSchoolZipCode.setCellValueFactory(param -> param.getValue().getZipCode());
        tblClmSchoolCity.setCellValueFactory(param -> param.getValue().getCity());

        tblClmTeacherFirstName.setCellValueFactory(param -> param.getValue().firstNameProperty());
        tblClmTeacherLastName.setCellValueFactory(param -> param.getValue().lastNameProperty());
        tblClmTeacherEmail.setCellValueFactory(param -> param.getValue().emailProperty());

        tblClmStudentFirstName.setCellValueFactory(param -> param.getValue().firstNameProperty());
        tblClmStudentLastName.setCellValueFactory(param -> param.getValue().lastNameProperty());
        tblClmStudentEmail.setCellValueFactory(param -> param.getValue().emailProperty());
    }


    public void onNewStudent(ActionEvent event) {
    }

    public void onEditStudent(ActionEvent event) {
    }

    public void onDeleteStudent(ActionEvent event) {
    }



    public void onNewTeacher(ActionEvent event) {
    }

    public void onEditTeacher(ActionEvent event) {
    }

    public void onDeleteTeacher(ActionEvent event) {
    }



    public void onNewSchool(ActionEvent event) {
    }

    public void onEditSchool(ActionEvent event) {
    }

    public void onDeleteSchool(ActionEvent event) {
    }



    private SortedList<AccountModel> searchTable(TextField searchField, TableView table, ObservableList searchList)
    {
        FilteredList<AccountModel> filteredData = new FilteredList<>(searchList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {

                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                } else if(user.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                }else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<AccountModel> sortedUsers = new SortedList<>(filteredData);

        sortedUsers.comparatorProperty().bind(table.comparatorProperty());

        return sortedUsers;
    }

    private ResourceBundle getResource(TableView tableView) {
        ResourceBundle resource = new ListResourceBundle() {
            @Override
            protected Object[][] getContents() {
                return new Object[][]
                        {
                                {"selectedModel", tableView.getSelectionModel().getSelectedItem()},
                        };
            }
        };
        return resource;
    }

    private ResourceBundle getResource(AccountModel account) {
        ResourceBundle resource = new ListResourceBundle() {
            @Override
            protected Object[][] getContents() {
                return new Object[][]
                        {
                                {"selectedModel", account},
                        };
            }
        };
        return resource;
    }
}
