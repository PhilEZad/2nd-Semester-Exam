package Application.GUI.Controllers.dashboard;

import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import Application.GUI.Models.ControllerModels.TeacherViewModel;
import Application.GUI.Models.FunctionalLevels;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CitizenTemplate implements Initializable {

    public AnchorPane anchorPaneCitizenTemplateDashboard;
    public ListView listViewCitizenTemplates;
    public TextField txtFieldCitizenTemplateSearch;
    public Button btnCitizenTemplateSearch;
    public Label lblCitizenTemplateName;
    public ListView listViewCitizenTemplateContactInfo;
    public Button btnAddCitizenTemplateContactInfo;
    public Button btnRemoveCitizenTemplateContactInfo;
    public Button btnCitizenTemplateEditBaseData;
    public Label lblAgeCitizenTemplate;
    public Label lblBirthdateCitizenTemplate;
    public Label lblAddressCitizenTemplate;
    public Label lblHelpStatusCitizenTemplate;
    public Label lblCivilianStatusCitizenTemplate;
    public Button btnCitizenTemplateChangeJournal;
    public ToggleButton tglBtnCitizenTemplateEditOn;
    public ToggleButton tglBtnCitizenTemplateEditOff;


    // Citizen Template - Functional Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewFunc;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCategory;
    public TreeTableColumn<CategoryEntryModel, ComboBox<FunctionalLevels>> treeTblColumnFuncLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncImplications;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCitizenGoals;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncNote;

    // Citizen Template - Health Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewHealth;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCategory;
    public TreeTableColumn<CategoryEntryModel, Number> treeTblColumnHealthLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthNote;

    // Citizen Template - General Information
    public TextArea txtAreaGenInfoMastering;
    public TextArea txtAreaGenInfoMotivation;
    public TextArea txtAreaGenInfoRessources;
    public TextArea txtAreaGenInfoRoles;
    public TextArea txtAreaGenInfoHabits;
    public TextArea txtAreaGenInfoEduAndJob;
    public TextArea txtAreaGenInfoLifeStory;
    public TextArea txtAreaGenInfoHealthInfo;
    public TextArea txtAreaGenInfoAssistiveDevices;
    public TextArea txtAreaGenInfoHomeLayout;
    public TextArea txtAreaGenInfoNetwork;

    private BooleanProperty citizenTemplateEditMode = new SimpleBooleanProperty(false);
    private ToggleGroup toggleGroup;
    private TeacherViewModel teacherViewModel = new TeacherViewModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    initTreeTableClmns();
    initToggleGroup();
    setFuncTreeTable();
    editModeListener();
    }


    public void onCitizenTemplateSearch(ActionEvent event) {
        teacherViewModel.citizenTemplateSearch();
    }

    public void onRemoveCitizenTemplateContactInfo(ActionEvent event) {
        teacherViewModel.removeCitizenTemplateContactInfo();
    }

    public void onAddCitizenTemplateContactInfo(ActionEvent event) {
        teacherViewModel.addCitizenTemplateContactInfo();
    }

    private void setFuncTreeTable(){
        // Set up the table
        CitizenTemplateModel citizenTemplateModel = new CitizenTemplateModel();

        ObservableList<CategoryEntryModel> func = citizenTemplateModel.getFunctionalAbilities();
        ObservableList<CategoryEntryModel> health = citizenTemplateModel.getHealthConditions();

        ObservableList<TreeItem<CategoryEntryModel>> funcTree = FXCollections.observableArrayList();
        ObservableList<TreeItem<CategoryEntryModel>> healthTree = FXCollections.observableArrayList();

        TreeItem<CategoryEntryModel> funcRoot = new TreeItem<>(new CategoryEntryModel("Functional Abilities"));
        TreeItem<CategoryEntryModel> healthRoot = new TreeItem<>(new CategoryEntryModel("Health Conditions"));
        treeTblViewFunc.setRoot(funcRoot);
        treeTblViewHealth.setRoot(healthRoot);

        for (CategoryEntryModel categoryEntryModel : func) {
            funcTree.add(new TreeItem<>(categoryEntryModel));
        }

        for (CategoryEntryModel categoryEntryModel : health) {
            healthTree.add(new TreeItem<>(categoryEntryModel));
        }
        funcRoot.getChildren().addAll(funcTree);
        healthRoot.getChildren().addAll(healthTree);

        //https://jenkov.com/tutorials/javafx/treetableview.html
    }

    private void initToggleGroup()
    {

        toggleGroup = new ToggleGroup();
        tglBtnCitizenTemplateEditOn.setToggleGroup(toggleGroup);
        tglBtnCitizenTemplateEditOff.setToggleGroup(toggleGroup);
    }

    private void initTreeTableClmns(){
        treeTblColumnFuncCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblColumnFuncLevel.setCellValueFactory(param -> param.getValue().getValue().getLevelImageComboBoxProperty());
        treeTblColumnFuncAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblColumnFuncCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblColumnFuncImplications.setCellValueFactory(param -> param.getValue().getValue().implicationsProperty());
        treeTblColumnFuncCitizenGoals.setCellValueFactory(param -> param.getValue().getValue().citizenGoalsProperty());
        treeTblColumnFuncExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().expectedConditionProperty());
        treeTblColumnFuncNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());

        treeTblColumnHealthCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblColumnHealthLevel.setCellValueFactory(param -> param.getValue().getValue().levelProperty());
        treeTblColumnHealthAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblColumnHealthCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblColumnHealthExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().expectedConditionProperty());
        treeTblColumnHealthNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());
    }

    public void onCitizenTemplateChangeJournal(ActionEvent event) {
        teacherViewModel.citizenTemplateChangeJournal();
    }

    public void onCitizenTemplateEditBaseData(ActionEvent event) {
        teacherViewModel.onCitizenTemplateEditBaseData();
    }

    private void initCitizenTemplatesList(){
        listViewCitizenTemplates.setItems(teacherViewModel.getCitizenTemplates());
        listViewCitizenTemplates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            teacherViewModel.setSelectedCitizenTemplateModel((CitizenTemplateModel) newValue);
            setDataToCitizenTemplateView();
        });
    }

    private void setDataToCitizenTemplateView(){
        lblCitizenTemplateName.setText(teacherViewModel.getSelectedCitizenTemplateModel().getName());
        lblAgeCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getAge() + "");
        lblAddressCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getAddress());
        lblBirthdateCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        lblHelpStatusCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getHelpStatus());
        lblCivilianStatusCitizenTemplate.setText(teacherViewModel.getSelectedCitizenTemplateModel().getCivilianStatus());

        listViewCitizenTemplateContactInfo.setItems(teacherViewModel.getSelectedCitizenTemplateModel().getContactInfo());
    }

    private void editModeListener(){
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            ToggleButton newToggleValue = null;
            ToggleButton oldToggleValue = null;
            if(newValue != null) {
                newToggleValue = (ToggleButton) newValue;
                newToggleValue.setDisable(true);
            }

            if (oldValue != null) {
                oldToggleValue = (ToggleButton) oldValue;
                oldToggleValue.setDisable(false);
            }

            if(newToggleValue == tglBtnCitizenTemplateEditOn && newToggleValue != null)
            {

            }
            else if(newToggleValue == tglBtnCitizenTemplateEditOff && newToggleValue != null) {

            }


        });
    }

    private void editModePropertyListener() {
        citizenTemplateEditMode.addListener((observable, oldValue, newValue) -> editComboBoxes(newValue));

        //TODO: Switch table items
    }

    private void editComboBoxes(boolean editable) {
        treeTblViewFunc.setEditable(editable);
        treeTblViewHealth.setEditable(editable);
        for (TreeItem<CategoryEntryModel> cat : treeTblViewFunc.getRoot().getChildren()) {
            cat.getValue().getLevelImageComboBox().setDisable(editable);
        }
        for (TreeItem<CategoryEntryModel> cat : treeTblViewHealth.getRoot().getChildren()) {
            cat.getValue().getLevelImageComboBox().setDisable(editable);
        }

    }
}
