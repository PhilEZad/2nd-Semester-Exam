package Application.GUI.Controllers.Popups;

import Application.BE.GeneralJournal;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.ControllerModels.StudentViewControllerModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.HealthLevels;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.Objects;
import java.util.ResourceBundle;

public class CitizenDetailsViewController implements Initializable {

    public Label lblCitizenFirstName;
    public Label lblCitizenSurname;
    public Label lblCitizenAge;

    // Functional Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewFunc;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCategory;
    public TreeTableColumn<CategoryEntryModel, ComboBox<FunctionalLevels>> treeTblColumnFuncLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncImplications;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCitizenGoals;
    public TreeTableColumn<CategoryEntryModel, ComboBox<FunctionalLevels>> treeTblColumnFuncExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncNote;

    // Health Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewHealth;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCategory;
    public TreeTableColumn<CategoryEntryModel, ComboBox<HealthLevels>> treeTblColumnHealthLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCause;
    public TreeTableColumn<CategoryEntryModel, ComboBox<HealthLevels>> treeTblColumnHealthExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthNote;

    // General Information
    public TextArea txtAreaGenInfoCoping;
    public TextArea txtAreaGenInfoMotivation;
    public TextArea txtAreaGenInfoResources;
    public TextArea txtAreaGenInfoRoles;
    public TextArea txtAreaGenInfoHabits;
    public TextArea txtAreaGenInfoEduAndJob;
    public TextArea txtAreaGenInfoLifeStory;
    public TextArea txtAreaGenInfoHealthInfo;
    public TextArea txtAreaGenInfoAssistiveDevices;
    public TextArea txtAreaGenInfoHomeLayout;
    public TextArea txtAreaGenInfoNetwork;

    public Button btnAddObservation;
    public Button btnEditObservation;
    public Button btnBackToDashboard;

    private StudentViewControllerModel model = new StudentViewControllerModel();
    private boolean isTeacher = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBundle(resources);
        setDataToCitizenTemplateView();
    }


    private void initBundle(ResourceBundle bundle) {
        if (bundle.getObject("selectedCitizen") != null){
            model.setSelectedCitizen((CitizenModel) bundle.getObject("selectedCitizen"));
        }
        if (bundle.getObject("accountType") != null){
            String accountType = (String) bundle.getObject("accountType");
            if (accountType.equals("teacher")){
                isTeacher = true;
            }else {
                isTeacher = false;
            }
        }
    }

    /**
     * sets every compononent of the citizen details view to the values of the selected citizen.
     */
    private void setDataToCitizenTemplateView() {
        if (model.getSelectedCitizen() != null) {
            //set the base data of name, surname and age to that of the selected citizen template
            lblCitizenFirstName.setText(model.getSelectedCitizen().getFirstName());
            lblCitizenSurname.setText(model.getSelectedCitizen().getLastName());
            lblCitizenAge.setText(String.valueOf(model.getSelectedCitizen().getAge()));

            //set the functional abilities TreeTableView to the values of the selected citizen template
            treeTblViewFunc.setRoot(model.getRelevantFuncCategoriesAsTreeItem());
            treeTblViewFunc.setShowRoot(false);

            //set the health categories to the health categories of the selected citizen template
            treeTblViewHealth.setRoot(model.getRelevantHealthCategoriesAsTreeItem());
            treeTblViewHealth.setShowRoot(false);

            GeneralJournal journal = model.getSelectedCitizen().getBeCitizen().getGeneralInfo();

            //set the general information section to that of the selected citizen template
            txtAreaGenInfoCoping.setText(journal.getCoping());
            txtAreaGenInfoMotivation.setText(journal.getMotivation());
            txtAreaGenInfoResources.setText(journal.getResources());
            txtAreaGenInfoRoles.setText(journal.getRoles());
            txtAreaGenInfoHabits.setText(journal.getHabits());
            txtAreaGenInfoEduAndJob.setText(journal.getEduAndJob());
            txtAreaGenInfoLifeStory.setText(journal.getLifeStory());
            txtAreaGenInfoHealthInfo.setText(journal.getHealthInfo());
            txtAreaGenInfoAssistiveDevices.setText(journal.getAssistiveDevices());
            txtAreaGenInfoHomeLayout.setText(journal.getHomeLayout());
            txtAreaGenInfoNetwork.setText(journal.getNetwork());
        }

    }


    public void onAddObservation(ActionEvent event) {
        openObservationView(false, null);
    }

    /**
     * opens the observation view with the given parameters.
     * editing is true, the view is opened in edit mode, automatically navigating
     * to the observation selected in this view and autofilling the user inputs to the existing data.
     * @param event
     */
    public void onEditObservation(ActionEvent event) {
        TreeItem<CategoryEntryModel> selectedFuncItem = treeTblViewFunc.getSelectionModel().getSelectedItem();
        TreeItem<CategoryEntryModel> selectedHealthItem = treeTblViewHealth.getSelectionModel().getSelectedItem();
        if (selectedFuncItem == null && selectedHealthItem == null || selectedFuncItem != null && selectedHealthItem != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Vælg venligst én tilstandskategori, hvis observationen skal redigeres");
            alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());
            alert.show();
        }
        TreeItem<CategoryEntryModel> selectedItem = null;

        if (selectedFuncItem != null) {
            selectedItem = selectedFuncItem;
        }
        else if (selectedHealthItem != null) {
            selectedItem = selectedHealthItem;
        }

        openObservationView(true, selectedItem);
    }

    private void openObservationView(boolean editing, TreeItem<CategoryEntryModel> selectedItem) {
        Parent root = null;
        Stage stage = new Stage();

        try {
            ResourceBundle resources = new ListResourceBundle()
            {
                @Override
                protected Object[][] getContents()
                {
                    return new Object[][]{
                            {"selectedCitizen", model.getSelectedCitizen()},
                            {"selectedCategoryEntryModel", selectedItem},
                            {"isEditing", editing}
                    };
                }
            };


            root = FXMLLoader.load(getClass().getResource("/Views/Popups/ObservationView.fxml"), resources);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setOnHiding(event -> {
            //refresh the data in the table and add any newly relevant categories
            model.recalculateRelevantCategories();

            //set the functional abilities TreeTableView to the values of the selected citizen template
            treeTblViewFunc.setRoot(model.getRelevantFuncCategoriesAsTreeItem());
            treeTblViewFunc.setShowRoot(false);

            //set the health categories to the health categories of the selected citizen template
            treeTblViewHealth.setRoot(model.getRelevantHealthCategoriesAsTreeItem());
            treeTblViewHealth.setShowRoot(false);
        });
    }

    public void onBackToDashboard(ActionEvent event) {
        Stage stage = (Stage) btnBackToDashboard.getScene().getWindow();
        Parent root = null;

        try {
            ResourceBundle resources = new ListResourceBundle()
            {
                @Override
                protected Object[][] getContents()
                {
                    return new Object[][]{  {"selectedCitizen", model.getSelectedCitizen()}};
                }
            };

            if (isTeacher) {
                stage.close();
            }
            else {
                root = FXMLLoader.load(getClass().getResource("/Views/StudentView.fxml"), resources);
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
