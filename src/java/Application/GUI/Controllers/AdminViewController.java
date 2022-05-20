package Application.GUI.Controllers;

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
import java.util.Objects;
import java.util.ResourceBundle;

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
    public void initialize(URL location, ResourceBundle resources) {
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
        try {
            Stage popupMenu = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Popups/CreateStudentView.fxml")));
            popupMenu.setTitle("Ny Elev");
            popupMenu.setScene(new Scene(root));
            popupMenu.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createTeacher(ActionEvent actionEvent)
    {
        try
        {
            Stage popupMenu = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Popups/CreateTeacherView.fxml")));
            popupMenu.setTitle("Ny Lærer");
            popupMenu.setScene(new Scene(root));
            popupMenu.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createSchool(ActionEvent actionEvent)
    {
        try
        {
            Stage popupMenu = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Popups/CreateSchoolView.fxml")));
            popupMenu.setTitle("Ny Skole");
            popupMenu.setScene(new Scene(root));
            popupMenu.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void editSelected(ActionEvent actionEvent) throws IOException
    {

        if (tabViewTeacher.isSelected())
        {
            Stage popupMenuTeacher = new Stage();
            Parent rootTeacher = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Popups/EditTeacherView.fxml")));
            popupMenuTeacher.setTitle("Rediger Lærer");
            popupMenuTeacher.setScene(new Scene(rootTeacher));
            popupMenuTeacher.show();
        }
        else if (tabViewStudent.isSelected())
        {
            Stage popupMenuStudent = new Stage();
            Parent rootStudent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Popups/EditStudentView.fxml")));
            popupMenuStudent.setTitle("Rediger Elev");
            popupMenuStudent.setScene(new Scene(rootStudent));
            popupMenuStudent.show();
        }
        else if (tabViewSchool.isSelected())
        {
            Stage popupMenuSchool = new Stage();
            Parent rootSchool = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Popups/EditSchoolView.fxml")));
            popupMenuSchool.setTitle("Rediger Skole");
            popupMenuSchool.setScene(new Scene(rootSchool));
            popupMenuSchool.show();
        } else
        {

        }
    }

    public void removeSelected(ActionEvent actionEvent)
    {
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
}
