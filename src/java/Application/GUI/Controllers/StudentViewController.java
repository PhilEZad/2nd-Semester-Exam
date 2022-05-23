package Application.GUI.Controllers;

import Application.BLL.StudentDataManager;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.ControllerModels.StudentViewControllerModel;
import Application.GUI.Models.HealthLevels;
import Application.Utility.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {

    @FXML public ListView<CitizenModel> listViewCitizens;
    @FXML public TextField txtFieldCitizenSearch;
    @FXML public Label lblCitizenName;
    @FXML public Label lblAge;
    @FXML public TableView<CategoryEntryModel> tblViewStudentDashboardHealth;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardHealthCategory;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardHealthLevel;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardHealthNote;
    @FXML public TableView<CategoryEntryModel> tblViewStudentDashboardFunc;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardFuncCategory;
    @FXML public TableColumn<CategoryEntryModel, ImageView> tblColumnStudentDashboardFuncLevel;
    @FXML public TableColumn<CategoryEntryModel, String> tblColumnStudentDashboardFuncNote;

    public Button btnOpenDetails;

    private StudentDataManager dataManager = new StudentDataManager();

    private StudentViewControllerModel model = new StudentViewControllerModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableViews();
        initListViewCitizens();
        initTestData();
        initBundle(resources);
        GUIUtils.searchListener(txtFieldCitizenSearch, listViewCitizens);
    }

    private void initBundle(ResourceBundle bundle) {
        if (bundle != null && bundle.getObject("selectedCitizen") != null){
            listViewCitizens.getSelectionModel().select((CitizenModel) bundle.getObject("selectedCitizen"));
        }
    }

    private void initTestData() {
        ObservableList<CitizenModel> citizens = FXCollections.observableArrayList();
        citizens.add(new CitizenModel());
        citizens.add(new CitizenModel());
        listViewCitizens.setItems(citizens);
    }

    private void initTableViews() {
        tblColumnStudentDashboardHealthCategory.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        tblColumnStudentDashboardHealthLevel.setCellValueFactory(cellData -> cellData.getValue().levelHealthProperty());
        tblColumnStudentDashboardHealthNote.setCellValueFactory(cellData -> cellData.getValue().noteProperty());
        tblColumnStudentDashboardFuncCategory.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        tblColumnStudentDashboardFuncLevel.setCellValueFactory(cellData -> cellData.getValue().levelFuncProperty());
        tblColumnStudentDashboardFuncNote.setCellValueFactory(cellData -> cellData.getValue().noteProperty());

        tblViewStudentDashboardHealth.setFixedCellSize(50);

        tblViewStudentDashboardHealth.getItems().add(new CategoryEntryModel("Health", HealthLevels.RELEVANT.ordinal(), ""));

        tblViewStudentDashboardFunc.getItems().add(new CategoryEntryModel("FUNCY", HealthLevels.RELEVANT.ordinal(), ""));
    }

    private void updateCitizenInfo(CitizenModel citizen) {
        lblCitizenName.setText(citizen.getFirstName() + " " + citizen.getLastName());
        lblAge.setText(citizen.getAge() + "");

        tblViewStudentDashboardHealth.setItems(model.getRelevantHealthCategoriesAsList());
        tblViewStudentDashboardFunc.setItems(model.getRelevantFuncCategoriesAsList());
    }

    private void initListViewCitizens(){
        listViewCitizens.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                model.setSelectedCitizen(newValue);
                updateCitizenInfo(newValue);
            }
        });
        listViewCitizens.setItems(model.getAllCitizens());
    }



    public void onOpenDetails(ActionEvent event) {
        Stage stage = (Stage) btnOpenDetails.getScene().getWindow();
        Parent root = null;

        try {
            ResourceBundle resources = new ListResourceBundle()
            {
                @Override
                protected Object[][] getContents()
                {
                    return new Object[][]{  {"selectedCitizen", model.getSelectedCitizen()}, {"accountType", "student"}};
                }
            };

            root = FXMLLoader.load(getClass().getResource("/Views/Popups/CitizenDetailsView.fxml"), resources);
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Ingen valgt borger");
            alert.setContentText("Vælg venligst en borger");
            alert.showAndWait();
        }
    }


}
