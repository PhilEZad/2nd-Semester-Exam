package Application.GUI.Controllers.dashboard;

import Application.BE.GeneralJournal;
import Application.GUI.Models.*;
import Application.GUI.Models.ControllerModels.CitizenTemplateControllerModel;
import Application.Utility.GUIUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.*;

public class CitizenTemplateController implements Initializable {

    public AnchorPane anchorPaneCitizenTemplateDashboard;
    public ListView<CitizenModel> listViewCitizenTemplates;
    public TextField txtFieldCitizenTemplateSearch;
    public TextField txtFieldName;
    public TextField txtFieldSurname;
    public TextField txtFieldAge;
    public Button btnGenerateBaseData;
    public Button btnCitizenTemplateEditOn;
    public Button btnCitizenTemplateEditCancel;
    public Button btnCitizenTemplateEditSave;
    public Button btnActions;


    // Citizen Template - Functional Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewFunc;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCategory;
    public TreeTableColumn<CategoryEntryModel, ComboBox<FunctionalLevels>> treeTblColumnFuncLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCause;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncImplications;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncCitizenGoals;
    public TreeTableColumn<CategoryEntryModel, ComboBox<FunctionalLevels>> treeTblColumnFuncExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnFuncNote;

    // Citizen Template - Health Conditions
    public TreeTableView<CategoryEntryModel> treeTblViewHealth;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCategory;
    public TreeTableColumn<CategoryEntryModel, ComboBox<HealthLevels>> treeTblColumnHealthLevel;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthAssessment;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthCause;
    public TreeTableColumn<CategoryEntryModel, ComboBox<HealthLevels>> treeTblColumnHealthExpectedCondition;
    public TreeTableColumn<CategoryEntryModel, String> treeTblColumnHealthNote;

    // Citizen Template - General Information
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

    // private CitizenTemplateControllerModel model = new CitizenTemplateControllerModel();
    private ContextMenu actionsMenu = new ContextMenu();
    private List<TreeTableColumn<CategoryEntryModel, String>> editableTreeTableColumns = new ArrayList<>();
    private List<TextArea> editableTextAreas = new ArrayList<>();

    private CitizenTemplateControllerModel model = new CitizenTemplateControllerModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnList();
        initTextAreaList();
        setTreeTables();
        initTreeTableClmns();
        initTreeTblColumnEdit();


        initCitizenTemplatesList();
        initActionsMenu();
        initTextFields();
        //GUIUtils.searchListener(txtFieldCitizenTemplateSearch, listViewCitizenTemplates);
    }

    /**
     * Sets the textFields containing the citizen template base information to disabled and applies
     * a textformatter to the age textfield, allowing only integers to be entered.
     */
    private void initTextFields() {
        txtFieldName.setDisable(true);
        txtFieldSurname.setDisable(true);
        txtFieldAge.setDisable(true);
        txtFieldAge.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(),0, GUIUtils.getIntegerFilter()));
    }


    /**
     * Shows the context menu with the available actions for the selected citizen template
     * right above the clicked button.
     * @param event
     */
    public void onActions(ActionEvent event) {
        double offsetX = -15;
        double offsetY = btnActions.getHeight() / 2.2;
        actionsMenu.show(btnActions, Side.TOP, offsetX, -offsetY);
    }

    /**
     * Initializes the context menu with the available actions for the selected citizen template
     */
    private void initActionsMenu() {
        MenuItem newCitizenTemplate = new MenuItem("Ny Borger Skabelon");
        newCitizenTemplate.setOnAction(event -> onNewCitizenTemplate());
        MenuItem copyCitizenTemplate = new MenuItem("Kopier Borger Skabelon");
        copyCitizenTemplate.setOnAction(event -> onCopyCitizenTemplate());
        MenuItem deleteCitizenTemplate = new MenuItem("Slet Borger Skabelon");
        deleteCitizenTemplate.setOnAction(event -> onDeleteCitizenTemplate());
        MenuItem newCitizenEntity = new MenuItem("Ny Borger Fra Skabelon");
        deleteCitizenTemplate.setOnAction(event -> onNewCitizenEntity());

        actionsMenu = new ContextMenu(newCitizenTemplate, copyCitizenTemplate, deleteCitizenTemplate, newCitizenEntity);
        actionsMenu.setAutoHide(true);
    }

    /**
     * Creates a new blank citizen template.e
     */
    private void onNewCitizenTemplate() {
        listViewCitizenTemplates.getItems().add(model.newCitizenTemplate());
    }

    /**
     * Displays a confirmation dialog before deleting the selected citizen template.
     * If confirmed, the selected template is deleted.
     */
    private void onDeleteCitizenTemplate() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Er du sikker på at du vil slette denne borger skabelonen?");
        alert.setContentText("Dette kan ikke fortrydes.");
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            listViewCitizenTemplates.getItems().remove(listViewCitizenTemplates.getSelectionModel().getSelectedItem());
            // model.deleteCitizenTemplate();
        }
    }

    /**
     * Creates a copy of the selected citizen template.
     */
    private void onCopyCitizenTemplate() {
        listViewCitizenTemplates.getItems().add(model.copyCitizenTemplate());
    }

    private void onNewCitizenEntity() {
        try {
            model.newCitizenEntity();
            Notifications notifications = Notifications.create();
            notifications.title("Ny borger");
            notifications.text("Borger er oprettet");
            notifications.showInformation();
            notifications.hideAfter(Duration.seconds(3));
        } catch (Exception e) {
            Notifications notifications = Notifications.create();
            notifications.title("Ny borger");
            notifications.text("Borger kunne ikke oprettes");
            notifications.showError();
        }

    }

    private void setTreeTables() {/*
        //TODO: Proper table population
        // Set up the table
        CitizenModel citizenTemplateModel = new CitizenModel();

        ObservableList<CategoryEntryModel> func = citizenTemplateModel.getRelevantFunctionalAbilities();
        ObservableList<CategoryEntryModel> health = citizenTemplateModel.getRelevantHealthConditions();

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
        */

    }

    /**
     * Adds all textAreas to the list of textAreas.
     * The purpose of the list to be able to iterate over all textAreas
     * and call a method on them without creating clutter in the code by addressing them manually
     * each time.
     */
    private void initTextAreaList(){
        editableTextAreas.add(txtAreaGenInfoCoping);
        editableTextAreas.add(txtAreaGenInfoMotivation);
        editableTextAreas.add(txtAreaGenInfoResources);
        editableTextAreas.add(txtAreaGenInfoRoles);
        editableTextAreas.add(txtAreaGenInfoHabits);
        editableTextAreas.add(txtAreaGenInfoEduAndJob);
        editableTextAreas.add(txtAreaGenInfoLifeStory);
        editableTextAreas.add(txtAreaGenInfoHealthInfo);
        editableTextAreas.add(txtAreaGenInfoAssistiveDevices);
        editableTextAreas.add(txtAreaGenInfoHomeLayout);
        editableTextAreas.add(txtAreaGenInfoNetwork);
    }

    /**
     * Adds all treeTableColumns intended to be editable through a TextFieldTree tablecell
     * to the list of columns.
     * The purpose of the list to be able to iterate over all it's elements
     * and call a method on them without creating clutter in the code by addressing them manually
     * each time.
     */
    private void initColumnList() {
        editableTreeTableColumns.add(treeTblColumnFuncAssessment);
        editableTreeTableColumns.add(treeTblColumnFuncCause);
        editableTreeTableColumns.add(treeTblColumnFuncImplications);
        editableTreeTableColumns.add(treeTblColumnFuncCitizenGoals);
        editableTreeTableColumns.add(treeTblColumnFuncNote);

        editableTreeTableColumns.add(treeTblColumnHealthAssessment);
        editableTreeTableColumns.add(treeTblColumnHealthCause);
        editableTreeTableColumns.add(treeTblColumnHealthNote);

    }

    /**
     * Initializes the TreeTableColumns for the Function and Health TreeTableViews.
     * Sets the cellValueFactory for the TreeTableColumns.
     * Any standard column intended to be editable, which is every one but the ones containing the category name,
     * has a TextTreeTableCell as its cellFactory.
     */
    private void initTreeTableClmns() {
        treeTblColumnFuncCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblColumnFuncLevel.setCellValueFactory(param -> param.getValue().getValue().getLevelFuncComboBoxProperty());
        treeTblColumnFuncAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblColumnFuncCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblColumnFuncImplications.setCellValueFactory(param -> param.getValue().getValue().implicationsProperty());
        treeTblColumnFuncCitizenGoals.setCellValueFactory(param -> param.getValue().getValue().citizenGoalsProperty());
        treeTblColumnFuncExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().getExConFuncComboBoxProperty());
        treeTblColumnFuncNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());

        treeTblColumnHealthCategory.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        treeTblColumnHealthLevel.setCellValueFactory(param -> param.getValue().getValue().getLevelHealthComboBoxProperty());
        treeTblColumnHealthAssessment.setCellValueFactory(param -> param.getValue().getValue().assessmentProperty());
        treeTblColumnHealthCause.setCellValueFactory(param -> param.getValue().getValue().causeProperty());
        treeTblColumnHealthExpectedCondition.setCellValueFactory(param -> param.getValue().getValue().getExConHealthComboBoxProperty());
        treeTblColumnHealthNote.setCellValueFactory(param -> param.getValue().getValue().noteProperty());


        //Use TextFieldTreeTableCell for the editable columns
        editableTreeTableColumns.forEach(col -> col.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn()));

    }


    /**
     * Initializes the citizen templates list and its ChangeListener,
     * which calls the setDataToCitizenTemplatesList() method when the selected citizenTemplate changes.
     */
    private void initCitizenTemplatesList() {
        listViewCitizenTemplates.setItems(model.getCitizenTemplates());

        listViewCitizenTemplates.getSelectionModel().select(0);

        listViewCitizenTemplates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            model.setSelectedCitizenTemplateModel((CitizenModel) newValue);
            setDataToCitizenTemplateView();
        });
    }


    /**
     * sets every compononent of the citizen template view to the values of the selected citizen template.
     */
    private void setDataToCitizenTemplateView() {
        if (model.getSelectedCitizenTemplateModel() != null) {
            //set the base data of name, surname and age to that of the selected citizen template
            txtFieldName.setText(model.getSelectedCitizenTemplateModel().getFirstName());
            txtFieldSurname.setText(model.getSelectedCitizenTemplateModel().getLastName());
            txtFieldAge.setText(String.valueOf(model.getSelectedCitizenTemplateModel().getAge()));

            //set the functional abilities TreeTableView to the values of the selected citizen template
            TreeItem<CategoryEntryModel> funcRoot = new TreeItem<>();
            funcRoot.getChildren().addAll(model.getRelevantFuncCategoriesAsTreeItem());
            treeTblViewFunc.setRoot(funcRoot);
            treeTblViewFunc.setShowRoot(false);

            //set the health categories to the health categories of the selected citizen template
            TreeItem<CategoryEntryModel> healthRoot = new TreeItem<>();
            healthRoot.getChildren().addAll(model.getRelevantHealthCategoriesAsTreeItem());
            treeTblViewHealth.setRoot(healthRoot);
            treeTblViewHealth.setShowRoot(false);

            GeneralJournal journal = model.getSelectedCitizenTemplateModel().getBeCitizen().getGeneralInfo();

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


    /**
     * Sets the tables and relevant columns to editable or not. The same applies to the combo boxes within the level columns.
     * Also changes the visible buttons deciding whether to start, save or abandon the edit.
     *
     * @param editable
     */
    private void setEditable(boolean editable) {
        treeTblViewFunc.setEditable(editable);
        treeTblViewHealth.setEditable(editable);

        treeTblColumnFuncCategory.setEditable(false); //the category column is not editable
        treeTblColumnFuncLevel.setEditable(editable); //the level column is editable

        treeTblColumnHealthCategory.setEditable(false); //the category column is not editable
        treeTblColumnHealthLevel.setEditable(editable); //the level column is editable

        //Set all standard columns to editable, except the category column
        editableTreeTableColumns.forEach(col -> col.setEditable(editable));

        //Set all TextAreas to editable
        editableTextAreas.forEach(ta -> ta.setEditable(editable));

        //Set all ComboBoxes to editable
        for (CategoryEntryModel cat : GUIUtils.getTreeItemsFromRoot(treeTblViewFunc.getRoot())) {
            ComboBox<FunctionalLevels> funcLevelComboBox = cat.getLevelFuncLevelComboBox();
            ComboBox<FunctionalLevels> funcExConComboBox = cat.getExConFuncComboBox();
            if (funcLevelComboBox != null) {
                funcLevelComboBox.setDisable(!editable);
            }
            if (funcExConComboBox != null) {
                funcExConComboBox.setDisable(!editable);
            }
        }
        for (CategoryEntryModel cat : GUIUtils.getTreeItemsFromRoot(treeTblViewHealth.getRoot())) {
            ComboBox<HealthLevels> healthLevelComboBox = cat.getLevelHealthLevelComboBox();
            ComboBox<HealthLevels> healthExConComboBox = cat.getExConHealthLevelComboBox();
            if (healthLevelComboBox != null) {
                healthLevelComboBox.setDisable(!editable);
            }
            if (healthExConComboBox != null) {
                healthExConComboBox.setDisable(!editable);
            }
        }

        //Allow the user to edit the name and age of the citizen template
        txtFieldName.setDisable(!editable);
        txtFieldSurname.setDisable(!editable);
        txtFieldAge.setDisable(!editable);
        btnGenerateBaseData.setVisible(editable);

        //ensures another citizen template is not selected while editing
        listViewCitizenTemplates.setDisable(editable);


        btnCitizenTemplateEditOn.setVisible(!editable); //Only visible if not editable
        btnCitizenTemplateEditSave.setVisible(editable); //Only visible if editable
        btnCitizenTemplateEditCancel.setVisible(editable); //Only visible if editable
    }


    /**
     * Calls the model to save the pre-edit state of the citizen template.
     * Sets the root of the tree tables to the have every category for
     * health conditions and functional abilities respectively.
     * @param event
     */
    public void onEditOn(ActionEvent event) {
        model.savePreEditState();
        treeTblViewFunc.setRoot(model.getAllFuncCategoriesAsTreeItem());
        treeTblViewHealth.setRoot(model.getAllHealthConditionsAsTreeItem());
        setEditable(true);

    }

    /**
     * Displays a confirmation dialog to the user, asking if they want to save the changes.
     * If the user clicks yes, the model is called to save the changes.
     * Changes made to the base data and general info are applied directly to the citizen template.
     * A new root is set to the tree tables containing all relevant categories after the edit, and the edit mode is turned off.
     * @param event
     */
    public void onEditDone(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Er du sikker på at du gemme ændringerne på denne borger skabelonen?");
        alert.setContentText("Dette kan ikke fortrydes.");
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            CitizenModel selected = model.getSelectedCitizenTemplateModel();
            if (selected.getFirstName() != txtFieldName.getText() && !txtFieldName.getText().isEmpty()) {
                selected.setFirstName(txtFieldName.getText());
            }
            if (selected.getLastName() != txtFieldSurname.getText() && !txtFieldSurname.getText().isEmpty()) {
                selected.setLastName(txtFieldSurname.getText());
            }
            if (selected.getAge() != Integer.parseInt(txtFieldAge.getText()) && !txtFieldAge.getText().isEmpty()) {
                selected.setAge(Integer.parseInt(txtFieldAge.getText()));
            }


            selected.getBeCitizen().getGeneralInfo().setCoping(txtAreaGenInfoCoping.getText());
            selected.getBeCitizen().getGeneralInfo().setMotivation(txtAreaGenInfoMotivation.getText());
            selected.getBeCitizen().getGeneralInfo().setResources(txtAreaGenInfoResources.getText());
            selected.getBeCitizen().getGeneralInfo().setRoles(txtAreaGenInfoRoles.getText());
            selected.getBeCitizen().getGeneralInfo().setHabits(txtAreaGenInfoHabits.getText());
            selected.getBeCitizen().getGeneralInfo().setEduAndJob(txtAreaGenInfoEduAndJob.getText());
            selected.getBeCitizen().getGeneralInfo().setLifeStory(txtAreaGenInfoLifeStory.getText());
            selected.getBeCitizen().getGeneralInfo().setHealthInfo(txtAreaGenInfoHealthInfo.getText());
            selected.getBeCitizen().getGeneralInfo().setAssistiveDevices(txtAreaGenInfoAssistiveDevices.getText());
            selected.getBeCitizen().getGeneralInfo().setHomeLayout(txtAreaGenInfoHomeLayout.getText());
            selected.getBeCitizen().getGeneralInfo().setNetwork(txtAreaGenInfoNetwork.getText());


            model.saveEditedCitizenTemplate();

            treeTblViewFunc.setRoot(model.getRelevantFuncCategoriesAsTreeItem());
            treeTblViewHealth.setRoot(model.getRelevantHealthCategoriesAsTreeItem());
            setEditable(false);
        }
    }

    /**
     * Cancels the edit mode and returns the citizen template to its pre-edit state by replacing the
     * object present at the index of the selected citizen with the clone made before the edit was started.
     * @param event
     */
    public void onEditCancel(ActionEvent event) {
        ObservableList<CitizenModel> templateModelObservableList = listViewCitizenTemplates.getItems();
        int index = templateModelObservableList.indexOf(model.getSelectedCitizenTemplateModel());
        listViewCitizenTemplates.getItems().set(index, model.getPreEditState());
        listViewCitizenTemplates.getSelectionModel().select(index);
        setEditable(false);
    }

    /**
     * sets the onEditCommit and onEditCancel methods for each tree table column.
     * onEditCommit applies the changes made in the tree table to the edited categoryEntryModel.
     * onEditCancel returns the edited variable to its pre-edit state.
     */
    private void initTreeTblColumnEdit() {
            //Commit Edit
        treeTblColumnFuncAssessment.setOnEditCommit(event -> getItemFromEditEvent(event).setAssessment(event.getOldValue()));
        treeTblColumnFuncCause.setOnEditCommit(event -> getItemFromEditEvent(event).setCause(event.getOldValue()));
        treeTblColumnFuncImplications.setOnEditCommit(event -> getItemFromEditEvent(event).setImplications(event.getOldValue()));
        treeTblColumnFuncCitizenGoals.setOnEditCommit(event -> getItemFromEditEvent(event).setCitizenGoals(event.getOldValue()));
        treeTblColumnFuncNote.setOnEditCommit(event -> getItemFromEditEvent(event).setNote(event.getOldValue()));

        treeTblColumnHealthAssessment.setOnEditCommit(event -> getItemFromEditEvent(event).setAssessment(event.getOldValue()));
        treeTblColumnHealthCause.setOnEditCommit(event -> getItemFromEditEvent(event).setCause(event.getOldValue()));
        treeTblColumnHealthNote.setOnEditCommit(event -> getItemFromEditEvent(event).setNote(event.getOldValue()));

            //Cancel Edit
        treeTblColumnFuncAssessment.setOnEditCancel(event -> getItemFromEditEvent(event).setAssessment(event.getOldValue()));
        treeTblColumnFuncCause.setOnEditCancel(event -> getItemFromEditEvent(event).setCause(event.getOldValue()));
        treeTblColumnFuncImplications.setOnEditCancel(event -> getItemFromEditEvent(event).setImplications(event.getOldValue()));
        treeTblColumnFuncCitizenGoals.setOnEditCancel(event -> getItemFromEditEvent(event).setCitizenGoals(event.getOldValue()));
        treeTblColumnFuncNote.setOnEditCancel(event -> getItemFromEditEvent(event).setNote(event.getOldValue()));

        treeTblColumnHealthAssessment.setOnEditCancel(event -> getItemFromEditEvent(event).setAssessment(event.getOldValue()));
        treeTblColumnHealthCause.setOnEditCancel(event -> getItemFromEditEvent(event).setCause(event.getOldValue()));
        treeTblColumnHealthNote.setOnEditCancel(event -> getItemFromEditEvent(event).setNote(event.getOldValue()));
    }

    /**
     * Small utility method for getting the item from the edit events made from the onEditCommit and onEditCancel methods.
     * @param editEvent
     * @return
     */
    private CategoryEntryModel getItemFromEditEvent(TreeTableColumn.CellEditEvent<CategoryEntryModel, String> editEvent) {
        TreeItem<CategoryEntryModel> treeItem = editEvent.getTreeTablePosition().getTreeItem();
        return treeItem.getValue();
    }


    public void onGenerateBaseData(ActionEvent event) {
        Object[] baseData = model.generateBaseData();
        txtFieldName.setText((String) baseData[0]);
        txtFieldSurname.setText((String) baseData[1]);
        txtFieldAge.setText((String) baseData[2]);
    }
}

