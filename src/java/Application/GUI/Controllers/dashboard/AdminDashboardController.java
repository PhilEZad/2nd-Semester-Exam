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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.ListResourceBundle;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML public TextField txtFieldSearch;

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



    AdminDataManager dataManager = new AdminDataManager();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableViews();
        populateTableViews();
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

    private void populateTableViews()
    {
        tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, dataManager.getAllTeachers()));
        tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, dataManager.getAllStudents()));
        tblViewSchool.setItems(dataManager.getAllSchools());
    }


    public void onNewStudent(ActionEvent event)
    {
        Stage popupMenu = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/Popups/CreateStudentView.fxml"));
            popupMenu.setTitle("Ny Elev");
            popupMenu.setScene(new Scene(root));
            popupMenu.show();
            popupMenu.setOnHidden(event1 -> tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, dataManager.getAllStudents())));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onEditStudent(ActionEvent event)
    {
        try
        {
        ResourceBundle resource = getResource(tblViewStudent);
        Stage popupMenuStudent = new Stage();
        Parent rootStudent = FXMLLoader.load(getClass().getResource("/Views/Popups/EditAccountView.fxml"), resource);
        popupMenuStudent.setTitle("Rediger Elev");
        popupMenuStudent.setScene(new Scene(rootStudent));
        popupMenuStudent.show();
        popupMenuStudent.setOnHidden(event1 -> tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, dataManager.getAllStudents())));
        } catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ingen elev valgt.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void onDeleteStudent(ActionEvent event)
    {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Slet " + tblViewStudent.getSelectionModel().getSelectedItem().getFirstName() + " " + tblViewStudent.getSelectionModel().getSelectedItem().getLastName() + "?");
            alert.setContentText("Dette kan ikke fortrydes!");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                dataManager.deleteAccount(tblViewStudent.getSelectionModel().getSelectedItem().getId());
                tblViewStudent.setItems(searchTable(txtFieldSearch, tblViewTeacher, dataManager.getAllStudents()));
            }
        } catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ingen elev valgt.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());
            alert.showAndWait();
            e.printStackTrace();
        }
    }



    public void onNewTeacher(ActionEvent event)
    {
        Stage popupMenu = new Stage();
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/views/Popups/CreateTeacherView.fxml"));
            popupMenu.setTitle("Ny Lærer");
            popupMenu.setScene(new Scene(root));
            popupMenu.show();
            popupMenu.setOnHidden(event1 -> tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, dataManager.getAllTeachers())));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onEditTeacher(ActionEvent event) {
        ResourceBundle resource;
        try {
            resource = getResource(tblViewTeacher);
            Stage popupMenuTeacher = new Stage();
            Parent rootTeacher = FXMLLoader.load(getClass().getResource("/views/Popups/EditAccountView.fxml"), resource);
            popupMenuTeacher.setTitle("Rediger Lærer");
            popupMenuTeacher.setScene(new Scene(rootTeacher));
            popupMenuTeacher.show();
            popupMenuTeacher.setOnHidden(event1 -> tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, dataManager.getAllTeachers())));
        } catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ingen lærer valgt.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void onDeleteTeacher(ActionEvent event)
    {
        try
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setHeaderText("Slet " + tblViewTeacher.getSelectionModel().getSelectedItem().getFirstName() + " " + tblViewTeacher.getSelectionModel().getSelectedItem().getLastName() + "?");
            alert.setContentText("Dette kan ikke fortrydes.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                dataManager.deleteAccount(tblViewTeacher.getSelectionModel().getSelectedItem().getId());
                tblViewTeacher.setItems(searchTable(txtFieldSearch, tblViewTeacher, dataManager.getAllTeachers()));
            }

        } catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ingen elev valgt.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());
            alert.showAndWait();
            e.printStackTrace();
        }
    }



    public void onNewSchool(ActionEvent event)
    {
        Stage popupMenu = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/Popups/CreateSchoolView.fxml"));
            popupMenu.setTitle("Ny Skole");
            popupMenu.setScene(new Scene(root));
            popupMenu.show();
            popupMenu.setOnHidden(event1 -> tblViewSchool.setItems(dataManager.getAllSchools()));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onEditSchool(ActionEvent event) {
        try {
            ResourceBundle resource = getResource(tblViewSchool);
            Stage popupMenuSchool = new Stage();
            Parent rootSchool = FXMLLoader.load(getClass().getResource("/views/Popups/EditSchoolView.fxml"), resource);
            popupMenuSchool.setTitle("Rediger Skole");
            popupMenuSchool.setScene(new Scene(rootSchool));
            popupMenuSchool.show();
            popupMenuSchool.setOnHidden(event1 -> tblViewSchool.setItems(dataManager.getAllSchools()));
        } catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ingen skole valgt.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void onDeleteSchool(ActionEvent event)
    {
        try
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setHeaderText("Slet " + tblViewSchool.getSelectionModel().getSelectedItem().getName().get() + "?");
            alert.setContentText("Dette kan ikke fortrydes.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                dataManager.deleteSchool(tblViewSchool.getSelectionModel().getSelectedItem().getId());
                tblViewSchool.setItems(dataManager.getAllSchools());
            }
        } catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ingen elev valgt.");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());
            alert.showAndWait();
            e.printStackTrace();
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
}
