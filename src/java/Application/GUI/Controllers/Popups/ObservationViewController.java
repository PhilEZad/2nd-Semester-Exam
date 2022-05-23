package Application.GUI.Controllers.Popups;

import Application.BE.CategoryType;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.ControllerModels.StudentViewControllerModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.HealthLevels;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ObservationViewController implements Initializable {

    @FXML public TabPane tabPaneContainer;
    @FXML public Tab tabHealth;
    @FXML public Tab tabFunc;

    @FXML public TreeTableView<CategoryEntryModel> treeTableViewHealth;
    @FXML public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCatName;
    @FXML public ComboBox<HealthLevels> comboBoxHealthLevel;
    @FXML public TextArea txtAreaHealthCause;
    @FXML public TextArea txtAreaHealthAssessment;
    @FXML public ComboBox<HealthLevels> comboBoxHealthExpectedCondition;
    @FXML public TextArea txtAreaHealthNote;

    @FXML public TreeTableView<CategoryEntryModel> treeTableViewFunc;
    @FXML public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCatName;
    @FXML public ComboBox<FunctionalLevels> comboBoxFuncLevel;
    @FXML public TextArea txtAreaFuncCause;
    @FXML public TextArea txtAreaFuncAssessment;
    @FXML public TextArea txtAreaFuncImplications;
    @FXML public VBox txtAreaFuncGoals;
    @FXML public ComboBox<FunctionalLevels> comboBoxFuncExpectedCondition;
    @FXML public TextArea txtAreaFuncNote;

    private StudentViewControllerModel model = new StudentViewControllerModel();
    private TreeItem<CategoryEntryModel> selectedCategoryEntryModel;
    private TreeItem<CategoryEntryModel> observation;
    private Boolean isEditing = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initIsEditing(resources);
        initCitizenBundle(resources);
        initSelectionListener();
        if (isEditing) {
            goToCategory(resources);
            initAutoFillListener();
        }
        initTreeTableViews();
    }

    private void initCitizenBundle(ResourceBundle bundle) {
        if (bundle.getObject("selectedCitizen") != null){
            model.setSelectedCitizen((CitizenModel) bundle.getObject("selectedCitizen"));
        }
    }

    private void goToCategory(ResourceBundle bundle){
        if (bundle.getObject("selectedCategoryEntryModel") != null){
            selectedCategoryEntryModel = (TreeItem<CategoryEntryModel>) bundle.getObject("selectedCategoryEntryModel");
            if (selectedCategoryEntryModel.getValue().getType() == CategoryType.FUNCTIONAL_ABILITY){
                tabPaneContainer.getSelectionModel().select(tabFunc);
            }
            else if (selectedCategoryEntryModel.getValue().getType() == CategoryType.HEALTH_CONDITION){
                tabPaneContainer.getSelectionModel().select(tabHealth);
                treeTableViewHealth.getSelectionModel().select(selectedCategoryEntryModel);
            }
        }
    }

    private void initIsEditing(ResourceBundle bundle){
        if (bundle.getObject("isEditing") != null){
            isEditing = (Boolean) bundle.getObject("isEditing");
        }
    }

    private void initAutoFillListener(){
        treeTableViewHealth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                if (newValue.getValue().getType() == CategoryType.HEALTH_CONDITION){
                    comboBoxHealthLevel.getSelectionModel().select(newValue.getValue().getLevelHealth());
                    txtAreaHealthCause.setText(newValue.getValue().getCause());
                    txtAreaHealthAssessment.setText(newValue.getValue().getAssessment());
                    comboBoxHealthExpectedCondition.getSelectionModel().select(newValue.getValue().getExConHealth());
                    txtAreaHealthNote.setText(newValue.getValue().getNote());
                }
                else if (newValue.getValue().getType() == CategoryType.FUNCTIONAL_ABILITY){
                    comboBoxFuncLevel.getSelectionModel().select(newValue.getValue().getLevelFunc());
                    txtAreaFuncCause.setText(newValue.getValue().getCause());
                    txtAreaFuncAssessment.setText(newValue.getValue().getAssessment());
                    txtAreaFuncImplications.setText(newValue.getValue().getImplications());
                    comboBoxFuncExpectedCondition.getSelectionModel().select(newValue.getValue().getExConFunc());
                    txtAreaFuncNote.setText(newValue.getValue().getNote());
                }
            }
        });
    }

    private void initSelectionListener(){
        treeTableViewHealth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                observation = newValue;
            }
        });
    }

    private void initTreeTableViews(){
        treeTableViewHealth.setRoot(model.getAllHealthConditionsAsTreeItem());
        treeTableViewHealth.setShowRoot(false);

        treeTableViewFunc.setRoot(model.getAllFuncCategoriesAsTreeItem());
        treeTableViewFunc.setShowRoot(false);

        treeTblColumnFuncCatName.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblColumnHealthCatName.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
    }


    public void onSaveHealthObservation(ActionEvent event) {
        if (observation != null){


            //Obligatory fields for health conditions

            if (observation.getValue().getCategoryName() == null || observation.getValue().getContentEntry().getCategory().getChildren().size() > 0){

                showAlert();
                return;
            }
            if (txtAreaHealthAssessment.getText().isEmpty() || txtAreaHealthAssessment.getText().isBlank() ||
                    txtAreaHealthAssessment.getText().equals(" ") || txtAreaHealthCause.getText() == null) {
                showAlert();
                return;
            }


            if (observation.getValue().getType() == CategoryType.HEALTH_CONDITION){
                observation.getValue().setLevelHealth(comboBoxHealthLevel.getSelectionModel().getSelectedItem());
                observation.getValue().setCause(txtAreaHealthCause.getText());
                observation.getValue().setAssessment(txtAreaHealthAssessment.getText());
                observation.getValue().setExConHealth(comboBoxHealthExpectedCondition.getSelectionModel().getSelectedItem());
                observation.getValue().setNote(txtAreaHealthNote.getText());

                model.updateObservation(observation.getValue());
            }
        }
        else {
            showAlert();
        }
    }

    public void onCancel(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void onSaveFuncObservation(ActionEvent event) {
        if (observation != null){

            //Obligatory fields for functional abilities

            if (observation.getValue().getCategoryName() == null  || observation.getValue().getContentEntry().getCategory().getChildren().size() > 0){

                showAlert();
                return;
            }
            else if (comboBoxFuncLevel.getSelectionModel().getSelectedItem() == null){
                showAlert();
                return;
            }

            if (observation.getValue().getType() == CategoryType.FUNCTIONAL_ABILITY){
                observation.getValue().setLevelFunc(comboBoxFuncLevel.getSelectionModel().getSelectedItem());
                observation.getValue().setCause(txtAreaFuncCause.getText());
                observation.getValue().setAssessment(txtAreaFuncAssessment.getText());
                observation.getValue().setImplications(txtAreaFuncImplications.getText());
                observation.getValue().setExConFunc(comboBoxFuncExpectedCondition.getSelectionModel().getSelectedItem());
                observation.getValue().setNote(txtAreaFuncNote.getText());

                model.updateObservation(observation.getValue());
            }
        }
        else {
            showAlert();
        }
    }

    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Vælg venligst den tilstands kategori du ønsker at opdatere en observation for, og udfyld venligst alle obligatoriske felter.");
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());
        alert.showAndWait();
    }
}
