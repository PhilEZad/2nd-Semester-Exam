package Application.GUI.Controllers;

import Application.BE.Account;
import Application.BLL.AdminDataManager;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.SchoolModel;
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

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AdminViewController implements Initializable {

    AdminDataManager daoAdmin;

    @FXML TextField txtFieldSearch;

    @FXML Button btnCreateTeacher;
    @FXML Button btnCreateStudent;
    @FXML Button btnCreateSchool;

    @FXML Tab tabViewStudent;
    @FXML Tab tabViewTeacher;
    @FXML Tab tabViewSchool;
    
    @FXML private TableView<AccountModel> tblViewStudent;
    @FXML private TableColumn<AccountModel, String> tblClmStudentFirstName;
    @FXML private TableColumn<AccountModel, String> tblClmStudentLastName;
    @FXML private TableColumn<AccountModel, String> tblClmStudentEmail;

    @FXML private TableView<AccountModel> tblViewTeacher;
    @FXML private TableColumn<AccountModel, String> tblClmTeacherFirstName;
    @FXML private TableColumn<AccountModel, String> tblClmTeacherLastName;
    @FXML private TableColumn<AccountModel, String> tblClmTeacherEmail;

    @FXML private TableView<SchoolModel> tblViewSchool;
    @FXML private TableColumn<SchoolModel, String> tblClmSchoolName;
    @FXML private TableColumn<SchoolModel, Number> tblClmSchoolZipCode;
    @FXML private TableColumn<SchoolModel, String> tblClmSchoolCity;

    public AdminViewController()
    {
        daoAdmin = new AdminDataManager();
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
        tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, daoAdmin.getAllTeachers()));
        tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, daoAdmin.getAllStudents()));
        tblViewSchool.setItems(daoAdmin.getAllSchools());
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
        popupMenu.setOnHidden(event -> tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, daoAdmin.getAllStudents())));
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
        popupMenu.setOnHidden(event -> tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, daoAdmin.getAllTeachers())));
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
        popupMenu.setOnHidden(event -> tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, daoAdmin.getAllStudents())));
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
                popupMenuTeacher.setOnHidden(event -> tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, daoAdmin.getAllTeachers())));
            } else if (tabViewStudent.isSelected()) {
                resource = getResource(tblViewStudent);
                Stage popupMenuStudent = new Stage();
                Parent rootStudent = FXMLLoader.load(getClass().getResource("/Views/Popups/EditAccountView.fxml"), resource);
                popupMenuStudent.setTitle("Rediger Elev");
                popupMenuStudent.setScene(new Scene(rootStudent));
                popupMenuStudent.show();
                popupMenuStudent.setOnHidden(event -> tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, daoAdmin.getAllStudents())));
            } else if (tabViewSchool.isSelected()) {
                resource = getResource(tblViewSchool);
                Stage popupMenuSchool = new Stage();
                Parent rootSchool = FXMLLoader.load(getClass().getResource("/views/Popups/EditSchoolView.fxml"), resource);
                popupMenuSchool.setTitle("Rediger Skole");
                popupMenuSchool.setScene(new Scene(rootSchool));
                popupMenuSchool.show();
                popupMenuSchool.setOnHidden(event -> tblViewSchool.setItems(daoAdmin.getAllSchools()));
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
                daoAdmin.deleteAccount(tblViewTeacher.getSelectionModel().getSelectedItem().getId());
                tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, daoAdmin.getAllTeachers()));
            }

        } else if (tabViewStudent.isSelected())
        {
            alert.setHeaderText("Slet " + tblViewStudent.getSelectionModel().getSelectedItem().getFirstName() + " " + tblViewStudent.getSelectionModel().getSelectedItem().getLastName() + "?");
            alert.setContentText("Dette kan ikke fortrydes.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                daoAdmin.deleteAccount(tblViewStudent.getSelectionModel().getSelectedItem().getId());
                tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, daoAdmin.getAllStudents()));
            }

        } else if (tabViewSchool.isSelected())
        {
            alert.setHeaderText("Slet " + tblViewSchool.getSelectionModel().getSelectedItem().getName().get() + "?");
            alert.setContentText("Dette kan ikke fortrydes.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                daoAdmin.deleteSchool(tblViewSchool.getSelectionModel().getSelectedItem().getId());
                tblViewSchool.setItems(daoAdmin.getAllSchools());
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
