package Application.GUI.Controllers;

import Application.BE.Account;
import Application.BLL.AdminDataManager;
import Application.GUI.Models.SchoolModel;
import Application.GUI.Models.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    @FXML Button btnCreateTeacher;
    @FXML Button btnCreateStudent;
    @FXML Button btnCreateSchool;
    
    @FXML private TableView<StudentModel> tblViewStudent;
    @FXML private TableColumn<StudentModel, String> tblClmStudentFirstName;
    @FXML private TableColumn<StudentModel, String> tblClmStudentLastName;
    @FXML private TableColumn<StudentModel, String> tblClmStudentEmail;
    @FXML private TableColumn<StudentModel, String> tblClmStudentClass;

    @FXML private TableView<Account> tblViewTeacher;
    @FXML private TableColumn<Account, String> tblClmTeacherFirstName;
    @FXML private TableColumn<Account, String> tblClmTeacherLastName;
    @FXML private TableColumn<Account, String> tblClmTeacherEmail;

    @FXML private TableView<SchoolModel> tblViewSchool;
    @FXML private TableColumn<SchoolModel, String> tblClmSchoolName;
    @FXML private TableColumn<SchoolModel, Number> tblClmSchoolZipCode;
    @FXML private TableColumn<SchoolModel, String> tblClmSchoolCity;



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
    }

    public void createStudent(ActionEvent actionEvent) throws IOException
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
            popupMenu.setTitle("Ny Elev");
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
}
