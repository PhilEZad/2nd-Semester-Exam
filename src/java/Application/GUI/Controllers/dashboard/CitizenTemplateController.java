package Application.GUI.Controllers.dashboard;

import Application.BE.CategoryType;
import Application.BE.Citizen;
import Application.BLL.CitizenManager;
import Application.BLL.TeacherDataManager;
import Application.GUI.Models.*;
import Application.Utility.GUIUtils;
import com.github.javafaker.Faker;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.*;


public class CitizenTemplateController implements Initializable
{
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

    private final List<TreeTableColumn<CategoryEntryModel, String>> editableTreeTableColumns = new ArrayList<>();
    private final List<TextArea> editableTextAreas = new ArrayList<>();


    private CitizenModel selected;
    private CitizenModel selectedBackup;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        editableTreeTableColumns.addAll(List.of(treeTblColumnFuncAssessment,treeTblColumnFuncCause,treeTblColumnFuncImplications,treeTblColumnFuncCitizenGoals,treeTblColumnFuncNote,treeTblColumnHealthAssessment,treeTblColumnHealthCause,treeTblColumnHealthNote));
        editableTextAreas.addAll(List.of(txtAreaGenInfoCoping, txtAreaGenInfoMotivation, txtAreaGenInfoResources,txtAreaGenInfoRoles,txtAreaGenInfoHabits,txtAreaGenInfoEduAndJob,txtAreaGenInfoLifeStory,txtAreaGenInfoHealthInfo,txtAreaGenInfoAssistiveDevices,txtAreaGenInfoHomeLayout,txtAreaGenInfoNetwork));

        setCellValuesForTreeTable();
        setTreeTableEditCallbacks();

        this.btnActions.setContextMenu(createContextMenuForTemplateActions());

        // textformatter to the age textfield, allowing only integers to be entered.
        txtFieldAge.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(),0, GUIUtils.getIntegerFilter()));

        initializeAvailableTemplates();

        setEditable(false); // this must not be called before the templates are loaded.
    }


    /**
     * Shows the context menu with the available actions for the selected citizen template
     * right above the clicked button.
     * @param event
     */
    public void onActions(ActionEvent event) {
        double offsetX = -15;
        double offsetY = btnActions.getHeight() / 2.2;

        btnActions.getContextMenu().show(btnActions, Side.TOP, offsetX, -offsetY);
    }

    /**
     * Initializes the context menu with the available actions for the selected citizen template
     */
    private ContextMenu createContextMenuForTemplateActions()
    {
        var menu = new ContextMenu();
        menu.setAutoHide(true);

        MenuItem newCitizenTemplate = new MenuItem("Ny Borger Skabelon");
        newCitizenTemplate.setOnAction(event -> onNewCitizenTemplate());
        menu.getItems().add(newCitizenTemplate);

        MenuItem copyCitizenTemplate = new MenuItem("Kopier Borger Skabelon");
        copyCitizenTemplate.setOnAction(event -> onCopyCitizenTemplate());
        menu.getItems().add(copyCitizenTemplate);

        MenuItem deleteCitizenTemplate = new MenuItem("Slet Borger Skabelon");
        deleteCitizenTemplate.setOnAction(event -> onDeleteCitizenTemplate());
        menu.getItems().add(deleteCitizenTemplate);

        return menu;
    }

    /**
     * Creates a new blank citizen template and adds it to the list of citizen templates.
     */
    private void onNewCitizenTemplate()
    {
        try
        {
            listViewCitizenTemplates.getItems().add(new CitizenModel(new CitizenManager().createCitizenTemplate()));
            initializeAvailableTemplates();

            Notifications notifications = Notifications.create();
            notifications.title("Ny borger skabelon");
            notifications.text("Borger skabelon er oprettet");
            notifications.showInformation();
            notifications.hideAfter(Duration.seconds(3));
        }
        catch (Exception e)
        {
            Notifications notifications = Notifications.create();
            notifications.title("Ny borger skabelon");
            notifications.text("Borger skabelon kunne ikke oprettes");
            notifications.showError();
        }
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

        if (result.get() == ButtonType.OK)
        {
            new CitizenManager().deleteCitizenTemplate(selected.getBeCitizen());
            listViewCitizenTemplates.getItems().remove(selected);
            listViewCitizenTemplates.getSelectionModel().selectNext();
            initializeAvailableTemplates();
        }
    }

    /**
     * Creates a copy of the selected citizen template.
     */
    private void onCopyCitizenTemplate()
    {
        try
        {
            listViewCitizenTemplates.getItems().add(new CitizenModel(new CitizenManager().createCitizenTemplateCopy(selected.clone())));
            initializeAvailableTemplates();
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the TreeTableColumns for the Function and Health TreeTableViews.
     * Sets the cellValueFactory for the TreeTableColumns.
     * Any standard column intended to be editable, which is every one but the ones containing the category name,
     * has a TextTreeTableCell as its cellFactory.
     */
    private void setCellValuesForTreeTable()
    {
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
    private void initializeAvailableTemplates()
    {
        listViewCitizenTemplates.getItems().clear();
        new CitizenManager().getAllTemplates().forEach(citizen -> listViewCitizenTemplates.getItems().add(new CitizenModel(citizen)));

        listViewCitizenTemplates.getSelectionModel().selectedItemProperty().removeListener(citizenTemplateListener());
        listViewCitizenTemplates.getSelectionModel().selectedItemProperty().addListener(citizenTemplateListener());

        listViewCitizenTemplates.getSelectionModel().selectFirst();
    }

    private ChangeListener<CitizenModel> citizenTemplateListener() {
        return (observable, oldValue, newValue) -> {
            this.selected = newValue;
            rebindDataBidirectional(oldValue, newValue);
        };
    }

    private <T> void rebindProperty(Property<T> lhs, Property<T> old, Property<T> _new)
    {
        if (lhs == null)
            return;

        lhs.unbind(); // can be very hard to debug, bug if you don't do this.

        if (old != null)
        {
            lhs.unbindBidirectional(old);
        }

        lhs.bindBidirectional(_new);
    }


    /**
     * sets every compononent of the citizen template view to the values of the selected citizen template.
     */
    private void rebindDataBidirectional(CitizenModel oldValue, CitizenModel newValue)
    {
        if (newValue == null)
            return;

        if (oldValue == null)
            oldValue = newValue;

        txtFieldAge.textProperty().unbindBidirectional(oldValue.ageProperty());
        txtFieldAge.textProperty().bindBidirectional(newValue.ageProperty(), new NumberStringConverter());
        rebindProperty(txtFieldName.textProperty(), oldValue.firstNameProperty(), newValue.firstNameProperty());
        rebindProperty(txtFieldSurname.textProperty(), oldValue.lastNameProperty(), newValue.lastNameProperty());
        rebindProperty(txtAreaGenInfoCoping.textProperty(), oldValue.copingProperty(), newValue.copingProperty());
        rebindProperty(txtAreaGenInfoMotivation.textProperty(), oldValue.motivationProperty(), newValue.motivationProperty());
        rebindProperty(txtAreaGenInfoResources.textProperty(), oldValue.resourcesProperty(), newValue.resourcesProperty());
        rebindProperty(txtAreaGenInfoRoles.textProperty(), oldValue.rolesProperty(), newValue.rolesProperty());
        rebindProperty(txtAreaGenInfoHabits.textProperty(), oldValue.habitsProperty(), newValue.habitsProperty());
        rebindProperty(txtAreaGenInfoEduAndJob.textProperty(), oldValue.eduAndJobProperty(), newValue.eduAndJobProperty());
        rebindProperty(txtAreaGenInfoLifeStory.textProperty(), oldValue.lifeStoryProperty(), newValue.lifeStoryProperty());
        rebindProperty(txtAreaGenInfoHealthInfo.textProperty(), oldValue.healthInfoProperty(), newValue.healthInfoProperty());
        rebindProperty(txtAreaGenInfoAssistiveDevices.textProperty(), oldValue.assistiveDevicesProperty(), newValue.assistiveDevicesProperty());
        rebindProperty(txtAreaGenInfoHomeLayout.textProperty(), oldValue.homeLayoutProperty(), newValue.homeLayoutProperty());
        rebindProperty(txtAreaGenInfoNetwork.textProperty(), oldValue.networkProperty(), newValue.networkProperty());

        //set the functional abilities TreeTableView to the values of the selected citizen template
        treeTblViewFunc.rootProperty().set(newValue.functionalAbilitiesTreeProperty().getValue());
        treeTblViewFunc.setShowRoot(false);

        //set the health categories to the health categories of the selected citizen template
        treeTblViewHealth.rootProperty().set(newValue.healthConditionsTreeProperty().getValue());
        treeTblViewHealth.setShowRoot(false);
    }


    /**
     * Sets the tables and relevant columns to editable or not. The same applies to the combo boxes within the level columns.
     * Also changes the visible buttons deciding whether to start, save or abandon the edit.
     *
     * @param editable
     */
    private void setEditable(boolean editable)
    {
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
       for (CategoryEntryModel cat : GUIUtils.getTreeItemsFromRoot(treeTblViewFunc.getRoot()))
       {
           ComboBox<FunctionalLevels> funcLevelComboBox = cat.getLevelFuncLevelComboBox();
           ComboBox<FunctionalLevels> funcExConComboBox = cat.getExConFuncComboBox();
           if (funcLevelComboBox != null) {
               funcLevelComboBox.setDisable(!editable);
           }
           if (funcExConComboBox != null) {
               funcExConComboBox.setDisable(!editable);
           }
       }

       for (CategoryEntryModel cat : GUIUtils.getTreeItemsFromRoot(treeTblViewHealth.getRoot()))
       {
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
    public void onEditModeRequested(ActionEvent event) {

        try {
            selectedBackup = selected.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        setEditable(true);
    }

    /**
     * Displays a confirmation dialog to the user, asking if they want to save the changes.
     * If the user clicks yes, the model is called to save the changes.
     * Changes made to the base data and general info are applied directly to the citizen template.
     * A new root is set to the tree tables containing all relevant categories after the edit, and the edit mode is turned off.
     * @param event
     */
    public void onEditDone(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Er du sikker på at du gemme ændringerne på denne borger skabelonen?");
        alert.setContentText("Dette kan ikke fortrydes.");
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/MainStylesheet.css")).toExternalForm());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) 
        {
            new CitizenManager().updateCitizen(CitizenModel.convert(selected));
            setEditable(false);
        }
    }

    /**
     * Cancels the edit mode and returns the citizen template to its pre-edit state by replacing the
     * object present at the index of the selected citizen with the clone made before the edit was started.
     * @param event
     */
    public void onEditCancel(ActionEvent event)
    {
        int index = listViewCitizenTemplates.getItems().indexOf(selected);
        listViewCitizenTemplates.getItems().set(index, selectedBackup);

        listViewCitizenTemplates.getSelectionModel().selectNext();
        listViewCitizenTemplates.getSelectionModel().selectPrevious();

        setEditable(false);
    }


    /**
     * sets the onEditCommit and onEditCancel methods for each tree table column.
     * onEditCommit applies the changes made in the tree table to the edited categoryEntryModel.
     * onEditCancel returns the edited variable to its pre-edit state.
     */
    private void setTreeTableEditCallbacks()
    {
        treeTblColumnFuncAssessment.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setAssessment(event.getNewValue()));
        treeTblColumnFuncAssessment.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setAssessment(event.getOldValue()));

        treeTblColumnFuncCause.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setCause(event.getNewValue()));
        treeTblColumnFuncCause.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setCause(event.getOldValue()));

        treeTblColumnFuncImplications.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setImplications(event.getNewValue()));
        treeTblColumnFuncImplications.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setImplications(event.getOldValue()));

        treeTblColumnFuncCitizenGoals.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setCitizenGoals(event.getNewValue()));
        treeTblColumnFuncCitizenGoals.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setCitizenGoals(event.getOldValue()));

        treeTblColumnFuncNote.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setNote(event.getNewValue()));
        treeTblColumnFuncNote.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setNote(event.getOldValue()));

        treeTblColumnHealthAssessment.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setAssessment(event.getNewValue()));
        treeTblColumnHealthAssessment.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setAssessment(event.getOldValue()));

        treeTblColumnHealthCause.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setCause(event.getNewValue()));
        treeTblColumnHealthCause.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setCause(event.getOldValue()));

        treeTblColumnHealthNote.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setNote(event.getNewValue()));
        treeTblColumnHealthNote.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setNote(event.getOldValue()));

        treeTblColumnFuncLevel.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setLevelFunc(event.getNewValue().getValue()));
        treeTblColumnFuncLevel.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setLevelFunc(event.getOldValue().getValue()));

        treeTblColumnHealthLevel.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setCurrentHealthStatus(event.getNewValue().getValue()));
        treeTblColumnHealthLevel.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setCurrentHealthStatus(event.getOldValue().getValue()));

        treeTblColumnFuncExpectedCondition.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setExConFunc(event.getNewValue().getValue()));
        treeTblColumnFuncExpectedCondition.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setExConFunc(event.getOldValue().getValue()));

        treeTblColumnHealthExpectedCondition.setOnEditCommit(event -> event.getTreeTablePosition().getTreeItem().getValue().setExpectedHealthStatus(event.getNewValue().getValue()));
        treeTblColumnHealthExpectedCondition.setOnEditCancel(event -> event.getTreeTablePosition().getTreeItem().getValue().setExpectedHealthStatus(event.getOldValue().getValue()));
    }



    public void onGenerateBaseData(ActionEvent event) {
        Object[] baseData = CitizenManager.generateBaseData();
        txtFieldName.setText((String) baseData[0]);
        txtFieldSurname.setText((String) baseData[1]);
        txtFieldAge.setText((String) baseData[2]);
    }
}

