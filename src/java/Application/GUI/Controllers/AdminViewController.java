package Application.GUI.Controllers;

import Application.BE.Account;
import Application.BE.School;
import Application.BLL.AdminDataManager;
import Application.GUI.Controllers.dashboard.AdminDashboardController;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.SchoolModel;
import Application.Utility.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Philip Zadeh
 * @author Kasper Rasmussen
 * */
@Deprecated
public class AdminViewController implements Initializable {

    AdminDataManager dataManager;

    @FXML TextField txtFieldSearch;

    @FXML public Button btnCreateTeacher;
    @FXML public Button btnCreateStudent;
    @FXML public Button btnCreateSchool;

    @FXML public Tab tabViewStudent;
    @FXML public Tab tabViewTeacher;
    @FXML public Tab tabViewSchool;
    
    @FXML public TableView<AccountModel> tblViewStudent;
    @FXML public TableColumn<AccountModel, String> tblClmStudentFirstName;
    @FXML public TableColumn<AccountModel, String> tblClmStudentLastName;
    @FXML public TableColumn<AccountModel, String> tblClmStudentEmail;

    @FXML public TableView<AccountModel> tblViewTeacher;
    @FXML public TableColumn<AccountModel, String> tblClmTeacherFirstName;
    @FXML public TableColumn<AccountModel, String> tblClmTeacherLastName;
    @FXML public TableColumn<AccountModel, String> tblClmTeacherEmail;

    @FXML public TableView<SchoolModel> tblViewSchool;
    @FXML public TableColumn<SchoolModel, String> tblClmSchoolName;
    @FXML public TableColumn<SchoolModel, Number> tblClmSchoolZipCode;
    @FXML public TableColumn<SchoolModel, String> tblClmSchoolCity;

    enum ListType
    {
        TEACHER,
        STUDENT,
        ADMIN
    }

    public AdminViewController()
    {
        dataManager = new AdminDataManager();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initTableViews();
        populateTableViews();
    }

    private void initTableViews()
    {
        tblClmStudentFirstName.setCellValueFactory(param -> param.getValue().firstNameProperty());
        tblClmStudentLastName.setCellValueFactory(param -> param.getValue().lastNameProperty());
        tblClmStudentEmail.setCellValueFactory(param -> param.getValue().emailProperty());

        tblClmTeacherFirstName.setCellValueFactory(param -> param.getValue().firstNameProperty());
        tblClmTeacherLastName.setCellValueFactory(param -> param.getValue().lastNameProperty());
        tblClmTeacherEmail.setCellValueFactory(param -> param.getValue().emailProperty());

        tblClmSchoolName.setCellValueFactory(param -> param.getValue().getName());
        tblClmSchoolZipCode.setCellValueFactory(param -> param.getValue().getZipCode());
        tblClmSchoolCity.setCellValueFactory(param -> param.getValue().getCity());
    }

    private void populateTableViews()
    {
        tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, FXCollections.observableArrayList(getObservableList(ListType.TEACHER))));
        tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, FXCollections.observableArrayList(getObservableList(ListType.STUDENT))));
        tblViewSchool.setItems(getObservableSchools());
    }

    public void createStudent(ActionEvent actionEvent)
    {
        Stage popupMenu = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/Popups/CreateStudentView.fxml"));
            popupMenu.setTitle("Ny Elev");
            popupMenu.setScene(new Scene(root));
            popupMenu.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        popupMenu.setOnHidden(event -> tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, getObservableList(ListType.STUDENT))));
    }

    public void createTeacher(ActionEvent actionEvent)
    {
        Stage popupMenu = new Stage();
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/views/Popups/CreateTeacherView.fxml"));
            popupMenu.setTitle("Ny Lærer");
            popupMenu.setScene(new Scene(root));
            popupMenu.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        popupMenu.setOnHidden(event -> tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, getObservableList(ListType.TEACHER))));
    }

    public void createSchool(ActionEvent actionEvent)
    {
        Stage popupMenu = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/Popups/CreateStudentView.fxml"));
            popupMenu.setTitle("Ny Elev");
            popupMenu.setScene(new Scene(root));
            popupMenu.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        popupMenu.setOnHidden(event -> tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewStudent, getObservableSchools())));
    }

    public void editSelected(ActionEvent actionEvent)
    {
        try {

            ResourceBundle resource;

            if (tabViewTeacher.isSelected()) {
                resource = getResource(tblViewTeacher);
                Stage popupMenuTeacher = new Stage();
                Parent rootTeacher = FXMLLoader.load(getClass().getResource("/views/Popups/EditAccountView.fxml"), resource);
                popupMenuTeacher.setTitle("Rediger Lærer");
                popupMenuTeacher.setScene(new Scene(rootTeacher));
                popupMenuTeacher.show();
                popupMenuTeacher.setOnHidden(event -> tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, getObservableList(ListType.TEACHER))));
            } else if (tabViewStudent.isSelected()) {
                resource = getResource(tblViewStudent);
                Stage popupMenuStudent = new Stage();
                Parent rootStudent = FXMLLoader.load(getClass().getResource("/Views/Popups/EditAccountView.fxml"), resource);
                popupMenuStudent.setTitle("Rediger Elev");
                popupMenuStudent.setScene(new Scene(rootStudent));
                popupMenuStudent.show();
                popupMenuStudent.setOnHidden(event -> tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, getObservableList(ListType.STUDENT))));
            } else if (tabViewSchool.isSelected()) {
                resource = getResource(tblViewSchool);
                Stage popupMenuSchool = new Stage();
                Parent rootSchool = FXMLLoader.load(getClass().getResource("/views/Popups/EditSchoolView.fxml"), resource);
                popupMenuSchool.setTitle("Rediger Skole");
                popupMenuSchool.setScene(new Scene(rootSchool));
                popupMenuSchool.show();
                popupMenuSchool.setOnHidden(event -> tblViewSchool.setItems(getObservableSchools()));
            } else {
                //Alert alert
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            //Alert
        }
    }

    public void removeSelected(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        if (tabViewTeacher.isSelected())
        {
            alert.setHeaderText("Slet " + tblViewTeacher.getSelectionModel().getSelectedItem().getLastName() + " " + tblViewTeacher.getSelectionModel().getSelectedItem().getLastName() + "?");
            alert.setContentText("Dette kan ikke fortrydes.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                try {
                    dataManager.deleteTeacher(tblViewTeacher.getSelectionModel().getSelectedItem().getAccount());
                } catch (SQLException e) {
                    GUIUtils.alertCall(Alert.AlertType.WARNING, "Datbase fejl.");
                    e.printStackTrace();
                }
                tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, getObservableList(ListType.TEACHER)));
            }

        } else if (tabViewStudent.isSelected())
        {
            alert.setHeaderText("Slet " + tblViewStudent.getSelectionModel().getSelectedItem().getFirstName() + " " + tblViewStudent.getSelectionModel().getSelectedItem().getLastName() + "?");
            alert.setContentText("Dette kan ikke fortrydes.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                try {
                    dataManager.deleteStudent(tblViewStudent.getSelectionModel().getSelectedItem().getAccount());
                } catch (SQLException e)
                {
                    GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
                    e.printStackTrace();
                }
                tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, getObservableList(ListType.STUDENT)));
            }

        } else if (tabViewSchool.isSelected())
        {
            alert.setHeaderText("Slet " + tblViewSchool.getSelectionModel().getSelectedItem().getName().get() + "?");
            alert.setContentText("Dette kan ikke fortrydes.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                try
                {
                    dataManager.deleteSchool(tblViewSchool.getSelectionModel().getSelectedItem().getSchool());
                    tblViewSchool.setItems(getObservableSchools());
                } catch (SQLException sqlException)
                {
                    GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
                    sqlException.printStackTrace();
                }
            }
        }
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

    private ObservableList<AccountModel> getObservableList(ListType listType)
    {
        ObservableList<AccountModel> studentList = FXCollections.observableArrayList();

        List<Account> accountList;

        try
        {
            switch (listType)
            {
                case STUDENT:
                    accountList = dataManager.getAllStudents();
                    break;
                case TEACHER:
                    accountList = dataManager.getAllTeachers();
                    break;
                case ADMIN:
                    accountList = dataManager.getAllAdmins();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + listType);
            }

            for (Account account : accountList)
            {
                AccountModel student = new AccountModel(account);
                studentList.add(student);
            }

            return studentList;

        } catch (SQLException e)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            e.printStackTrace();
            return studentList;
        }
    }

    private ObservableList<SchoolModel> getObservableSchools()
    {
        ObservableList<SchoolModel> schoolModelList = FXCollections.observableArrayList();

        List<School> schoolList;

        try
        {
            schoolList = dataManager.getAllSchools();

            for (School school : schoolList)
            {
                SchoolModel schoolModel = new SchoolModel(school);
                schoolModelList.add(schoolModel);
            }

            return schoolModelList;

        } catch (SQLException e)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            e.printStackTrace();
            return schoolModelList;
        }
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
}
