package Application.GUI.Controllers.dashboard;

import Application.BE.Account;
import Application.BLL.TeacherDataManager;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
import Application.Utility.GUIUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import java.sql.SQLException;
import java.util.*;

public class StudentsController implements Initializable {

    TeacherDataManager dataManger = new TeacherDataManager();

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
        listViewStudents.setItems(initTableWithSearch());
        listViewStudents.getSelectionModel().selectedItemProperty().addListener(studentSelectionChanged());
    }

    private SortedList initTableWithSearch() {
        FilteredList<AccountModel> filteredData = new FilteredList<>(studentList(), b -> true);

        txtFieldStudentsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (user.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<AccountModel> sortedUsers = new SortedList<>(filteredData);

        return sortedUsers;
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

    private void onNewStudent()
    {

    }

    private void onEditStudent()
    {
        try
        {
            ResourceBundle resource = getResource(listViewStudents);
            Stage popupMenuStudent = new Stage();
            Parent rootStudent = FXMLLoader.load(getClass().getResource("/Views/Popups/EditAccountView.fxml"), resource);
            popupMenuStudent.setTitle("Rediger Elev");
            popupMenuStudent.setScene(new Scene(rootStudent));
            popupMenuStudent.show();
            popupMenuStudent.setOnHidden(event1 -> listViewStudents.setItems(initTableWithSearch()));
        } catch (IllegalArgumentException illegalArgumentException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Ingen elev valgt.");
            illegalArgumentException.printStackTrace();
        } catch (IOException ioException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Stylesheet ikke fundet.");
            ioException.printStackTrace();
        }
    }

    private void onDeleteStudent()
    {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Slet " + listViewStudents.getSelectionModel().getSelectedItem().getFirstName() + " " + listViewStudents.getSelectionModel().getSelectedItem().getLastName() + "?");
            alert.setContentText("Dette kan ikke fortrydes!");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                dataManger.deleteStudent((listViewStudents.getSelectionModel().getSelectedItem().getAccount()));
                listViewStudents.setItems(initTableWithSearch());
            }
        } catch (SQLException sqlException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            sqlException.printStackTrace();
        } catch (IllegalArgumentException illegalArgumentException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Ingen elev valgt.");
            illegalArgumentException.printStackTrace();
        }
    }

    private ResourceBundle getResource(ListView tableView) {
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


    // FIXME: 23-05-2022 Databinds
    private ChangeListener<AccountModel> studentSelectionChanged()
    {
        return new ChangeListener<AccountModel>() {
            @Override
            public void changed(ObservableValue<? extends AccountModel> observable, AccountModel oldValue, AccountModel newValue)
            {
                updateSelectedItemBinds();
               //try {
               //    //System.out.println((new AssignedCitizenDAO().read(newValue)));
               //} catch (SQLException e) {
               //    throw new RuntimeException(e);
               //}
            }
        };
    }

    public void updateSelectedItemBinds()
    {
        var selected = this.listViewStudents.getSelectionModel().getSelectedItem();

        if (selected == null)
        {
            return;
        }

        //try {
        //    this.listViewCitizensForStudents.setItems(FXCollections.observableArrayList(new AssignedCitizenDAO().read(selected)));
        //} catch (SQLException e) {
        //    throw new RuntimeException(e);
        //}

        lblStudentsStudentName.setText(selected.getFirstName() + " " + selected.getLastName());
        lblStudentEmail.setText(selected.getEmail());
    }

    private ObservableList<AccountModel> studentList()
    {
        ObservableList<AccountModel> returnList = FXCollections.observableArrayList();
        List<Account> accountList;

        try
        {
            accountList = dataManger.getAllStudents();

            for (Account account : accountList) {
                AccountModel accountModel = new AccountModel(account);
                returnList.add(accountModel);
            }
            return returnList;
        } catch (SQLException sqlException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            sqlException.printStackTrace();
            return returnList;
        }
    }
}


