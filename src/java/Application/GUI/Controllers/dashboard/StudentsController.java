package Application.GUI.Controllers.dashboard;

import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
import javafx.event.ActionEvent;
import javafx.event.EventDispatchChain;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {
    public AnchorPane anchorPaneStudents;
    public ListView<AccountModel> listViewStudents;
    public TextField txtFieldStudentsSearch;
    public Label lblStudentsStudentName;
    public Label lblStudentEmail;
    public Button btnStudentAdmin;
    public ListView<CitizenModel> listViewCitizensForStudents;
    public Button btnViewStudentsWork;
    public Button btnAddCitizenToStudent;
    public Button btnRemoveCitizenToStudent;

    private ContextMenu adminMenu = new ContextMenu();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initActionsMenu();
    }


    /**
     * Shows the context menu with the available actions for the administrating students
     * @param event
     */
    public void onStudentAdmin(ActionEvent event) {
        double offsetX = -15;
        double offsetY = btnStudentAdmin.getHeight() / 2.2;
        adminMenu.show(btnStudentAdmin, Side.BOTTOM, offsetX, -offsetY);
    }

    public void onViewStudentsWork(ActionEvent event) {
        if (listViewCitizensForStudents.getSelectionModel().getSelectedItem() != null) {
            AccountModel selectedStudent = listViewStudents.getSelectionModel().getSelectedItem();
            CitizenModel selectedCitizenModel = listViewCitizensForStudents.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            Parent root = null;

            try {
                ResourceBundle resources = new ListResourceBundle()
                {
                    @Override
                    protected Object[][] getContents()
                    {
                        return new Object[][]{  {"selectedCitizen", selectedCitizenModel}, {"accountType", "teacher"}};
                    }
                };

                root = FXMLLoader.load(getClass().getResource("/Views/Popups/CitizenDetailsView.fxml"), resources);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle(selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + " - " + selectedCitizenModel.getFirstName() + " " + selectedCitizenModel.getLastName());
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Ingen valgt elev");
            alert.setContentText("Vælg venligst en elev");
            alert.showAndWait();
        }
    }

    public void onAddCitizenToStudent(ActionEvent event) {
    }

    public void onRemoveCitizenToStudent(ActionEvent event) {
    }


    /**
     * Initializes the context menu with the available actions for the selected citizen template
     */
    private void initActionsMenu() {
        MenuItem newStudent = new MenuItem("Opret Ny Skabelon");
        newStudent.setOnAction(event -> onNewStudent());
        MenuItem editStudent = new MenuItem("Rediger Elev");
        editStudent.setOnAction(event -> onEditStudent());
        MenuItem deleteStudent = new MenuItem("Slet Elev");
        deleteStudent.setOnAction(event -> onDeleteStudent());

        adminMenu = new ContextMenu(newStudent, editStudent, deleteStudent);
        adminMenu.setAutoHide(true);
    }

    private void onNewStudent() {
    }

    private void onEditStudent() {
    }

    private void onDeleteStudent() {
    }

}


