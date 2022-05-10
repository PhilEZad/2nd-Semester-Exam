package Application.GUI.Controllers.dashboard;

import Application.BE.Citizen;
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
    mockData();
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


    // FIXME: 10/05/2022 - Mock Data, replace with real data
    private void mockData()
    {
        lblAgeCitizenTemplate.setText("51");
        lblBirthdateCitizenTemplate.setText("07-05-1970");
        lblAddressCitizenTemplate.setText("Sprangsbjerg Kirkevej 103, 2.sal TH");
        lblHelpStatusCitizenTemplate.setText("Aktiv");
        lblCivilianStatusCitizenTemplate.setText("Gift");

        listViewCitizenTemplateContactInfo.getItems().add("Tlf: 52 74 68 88");
        listViewCitizenTemplateContactInfo.getItems().add("");
        listViewCitizenTemplateContactInfo.getItems().add("Kontant Personer:");
        listViewCitizenTemplateContactInfo.getItems().add("Christian Sandbæk");
        listViewCitizenTemplateContactInfo.getItems().add("Tlf: 50 74 67 77");

        txtAreaGenInfoMastering.setText("N.N. beskriver sig selv som en person, der altid klarer udfordringer og godt kan lide at lære nyt og engagere sig.\" \"N.N. er nogle dage negativ over sin situation. Med opmuntring genfinder N.N. gejsten til at kæmpe videre.");
        txtAreaGenInfoMotivation.setText("N.N. har det bedst, når der er rent og pænt i lejligheden: \"Hvis hjemmehjælpen tager det grove, så kan jeg sagtens selv klare resten.\"\" \"N.N. spiser mere, når han sidder foran sit tv med et par stykker smørrebrød.");
        txtAreaGenInfoRessources.setText("N.N. kan godt lide udfordringer og er god til at lære nyt. N.N. har levet et aktivt liv og er derfor i udmærket fysisk form.");
        txtAreaGenInfoRoles.setText("N.N. har tidligere været formand i idrætsforeningen. N.N. var indtil fornyelig frivillig leder af den lokale genbrugsbutik.");
        txtAreaGenInfoHabits.setText("N.N. vil gerne have smykker på hver morgen. N.N vil gerne ryge efter at have spist.");
        txtAreaGenInfoEduAndJob.setText("N.N. fortæller, at han har arbejdet i det samme firma i hele sit liv. Han ser stadig sine gamle kolleger – fx til jubilæer i firmaet og til arrangementer i seniorklubben. N.N. fortæller at han var uddannet lærer, hvilket han sætter en ære i.");
        txtAreaGenInfoLifeStory.setText("N.N. fortæller at han blev gift i 1960 og har to børn. N.N. ønsker at være hjemme i den sidste tid. Han ønsker ikke indlæggelse og har aftalt alt det praktiske med børnene.");
        txtAreaGenInfoHealthInfo.setText("N.N. fortæller, at han blev opereret for prostatacancer for 3 år siden. N.N. fortæller, at han ofte får blærebetændelse.");

        txtAreaGenInfoAssistiveDevices.setText("N.N. har indkøbt badebænk, rollator og gribetang. N.N. har et høreapparat.");
        txtAreaGenInfoNetwork.setText("N.N. har en barndomsven fra Fyn, som han ringer sammen med 1 gang om ugen. N.N. fortæller, at hans nærmeste venner er fra hans skakklub – der kommer han hver tirsdag, og de ringer altid efter ham, hvis han ikke dukker op. N.N. fortæller, at han får besøg af Mia fra besøgstjenesten hver onsdag.");
        txtAreaGenInfoHomeLayout.setText("N.N. har svært ved at bevæge sig udenfor sin lejlighed, da der ikke er elevator i bygningen. N.N. har en ældrevenlig bolig med handicapvenligt badeværelse.");

        ObservableList<CitizenTemplateModel> citizenList = FXCollections.observableArrayList();

        CitizenTemplateModel bob = new CitizenTemplateModel();
        bob.setName("Bob");
        bob.setSurname("Bygger");
        citizenList.add(bob);

        CitizenTemplateModel joseph = new CitizenTemplateModel();
        joseph.setName("Joseph");
        joseph.setSurname("Hernández");
        citizenList.add(joseph);

        CitizenTemplateModel ula = new CitizenTemplateModel();
        ula.setName("Ula");
        ula.setSurname("Andersen");
        citizenList.add(ula);

        CitizenTemplateModel anders = new CitizenTemplateModel();
        anders.setName("Anders");
        anders.setSurname("And");
        citizenList.add(anders);

        CitizenTemplateModel stegger = new CitizenTemplateModel();
        stegger.setName("Peter");
        stegger.setSurname("Stegger");
        citizenList.add(stegger);

        CitizenTemplateModel jeppe = new CitizenTemplateModel();
        jeppe.setName("Jeppe");
        jeppe.setSurname("Jyske");
        citizenList.add(jeppe);

        CitizenTemplateModel mads = new CitizenTemplateModel();
        mads.setName("Mads");
        mads.setSurname("Barth");
        citizenList.add(mads);

        CitizenTemplateModel philip = new CitizenTemplateModel();
        philip.setName("Philip");
        philip.setSurname("Zadeh");
        citizenList.add(philip);

        CitizenTemplateModel ramus = new CitizenTemplateModel();
        ramus.setName("Ramus");
        ramus.setSurname("Sandbøk");
        citizenList.add(ramus);

        CitizenTemplateModel kasper = new CitizenTemplateModel();
        kasper.setName("Kasper");
        kasper.setSurname("Rasmussen");
        citizenList.add(kasper);

        listViewCitizenTemplates.setItems(citizenList);
    }
}
