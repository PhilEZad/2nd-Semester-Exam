package Application.GUI.Models;

import Application.BE.CategoryEntry;
import Application.BE.CitizenBaseData;
import Application.BE.CitizenTemplate;
import Application.BE.ContactInfo;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.time.LocalDate;
import java.util.stream.Collectors;


public class CitizenTemplateModel implements Cloneable {

    CitizenTemplate template;

    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname;
    private IntegerProperty age;
    private LocalDate birthDate;
    private StringProperty helpStatus;
    private StringProperty civilianStatus;
    private StringProperty address;
    private ListProperty<ContactInfo> contactInfo;


    private ObservableList<CategoryEntryModel> relevantFunctionalAbilities;
    private ObservableList<CategoryEntryModel> relevantHealthConditions;
    private ObservableList<CategoryEntryModel> nonRelevantFunctionalAbilities;
    private ObservableList<CategoryEntryModel> nonRelevantHealthConditions;



    public CitizenTemplateModel(String name, String surname, LocalDate birthDate, String helpStatus, String civilianStatus, String address, ObservableList<ContactInfo> contactInfo) {
        this();

        this.name.set(name);
        this.surname.set(surname);
        this.age.set((LocalDate.now().getYear()) - (birthDate.getYear()));
        this.helpStatus.set(helpStatus);
        this.civilianStatus.set(civilianStatus);
        this.address.set(address);
        this.contactInfo.set(contactInfo);
        this.birthDate = birthDate;

        this.relevantFunctionalAbilities = FXCollections.observableArrayList();
        this.relevantHealthConditions = FXCollections.observableArrayList();
        this.nonRelevantFunctionalAbilities = FXCollections.observableArrayList();
        this.nonRelevantHealthConditions = FXCollections.observableArrayList();

        initFunctionalAbilities();
        initHealthConditions();
    }

    public CitizenTemplateModel()
    {
        template = new CitizenTemplate();

        this.name.bindBidirectional(new SimpleStringProperty(template.getBaseData().getName()));

        this.relevantFunctionalAbilities = FXCollections.observableArrayList();
        this.relevantHealthConditions = FXCollections.observableArrayList();

        this.nonRelevantFunctionalAbilities = FXCollections.observableArrayList();
        this.nonRelevantHealthConditions = FXCollections.observableArrayList();

        initProperties();
        initFunctionalAbilities();
        initHealthConditions();
    }

    public CitizenTemplate getTemplate()
    {
        return this.template;
    }

    private void initProperties() {
        this.name = new SimpleStringProperty();
        this.surname = new SimpleStringProperty();
        this.age = new SimpleIntegerProperty();
        this.helpStatus = new SimpleStringProperty();
        this.civilianStatus = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.contactInfo = new SimpleListProperty<>();
    }

    @Override
    public String toString() {
        return name.get() + " " + surname.get();
    }

    private void initFunctionalAbilities() {


        template.addFunctionalAbility(new CategoryEntry(0, "Walking", 1, true, false));

        template.addFunctionalAbility(new CategoryEntry(0, "Climbing", 1, true, false));
        template.addFunctionalAbility(new CategoryEntry(0, "Swimming", 1, true, false));
        template.addFunctionalAbility(new CategoryEntry(0, "Bathing", 4, true, false));

        nonRelevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Sleeping", 0, true, false)));
        nonRelevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Eating", 0, true, false)));
        nonRelevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Toileting", 0, true, false)));

        relevantFunctionalAbilities.add(new CategoryEntryModel("Other", 2, "note", true));
        nonRelevantFunctionalAbilities.add(new CategoryEntryModel("Other", 0, "note", true));
    }

    private void initHealthConditions() {
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Diabetes", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "High Blood Pressure", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Heart Disease", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Asthma", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Epilepsy", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Allergies", 1, false, false)));
        relevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "Other", 1, false, false)));

        nonRelevantHealthConditions.add(new CategoryEntryModel(new CategoryEntry(0, "None", 0, false, false)));

        relevantHealthConditions.add(new CategoryEntryModel("Other", 1, "note", false));
        nonRelevantHealthConditions.add(new CategoryEntryModel("Other", 0, "note", false));
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getHelpStatus() {
        return helpStatus.get();
    }

    public StringProperty helpStatusProperty() {
        return helpStatus;
    }

    public void setHelpStatus(String helpStatus) {
        this.helpStatus.set(helpStatus);
    }

    public String getCivilianStatus() {
        return civilianStatus.get();
    }

    public StringProperty civilianStatusProperty() {
        return civilianStatus;
    }

    public void setCivilianStatus(String civilStatus) {
        this.civilianStatus.set(civilStatus);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public ObservableList<ContactInfo> getContactInfo() {
        return contactInfo.get();
    }

    public ListProperty<ContactInfo> contactInfoProperty() {
        return contactInfo;
    }

    public void setContactInfo(ObservableList<ContactInfo> contactInfo) {
        this.contactInfo.set(contactInfo);
    }


    public ObservableList<CategoryEntryModel> getFunctionalAbilities() {
        return new SimpleListProperty(FXCollections.observableArrayList(template.getFunctionalAbilities()));
    }

    public ObservableList<CategoryEntryModel> getNonRelevantFunctionalAbilities() {
        return nonRelevantFunctionalAbilities;
    }

    public void setNonRelevantFunctionalAbilities(ObservableList<CategoryEntryModel> nonRelevantFunctionalAbilities) {
        this.nonRelevantFunctionalAbilities = relevantFunctionalAbilities;
    }

    public ObservableList<CategoryEntryModel> getNonRelevantHealthConditions() {
        return nonRelevantHealthConditions;
    }

    public void setNonRelevantHealthConditions(ObservableList<CategoryEntryModel> nonRelevantHealthConditions) {
        this.nonRelevantHealthConditions = relevantHealthConditions;
    }
    public ObservableList<CategoryEntryModel> getRelevantFunctionalAbilities() {
        return relevantFunctionalAbilities;
    }

    public void setRelevantFunctionalAbilities(ObservableList<CategoryEntryModel> relevantFunctionalAbilities) {
        this.relevantFunctionalAbilities = relevantFunctionalAbilities;
    }

    public ObservableList<CategoryEntryModel> getRelevantHealthConditions() {
        return relevantHealthConditions;
    }

    public void setRelevantHealthConditions(ObservableList<CategoryEntryModel> relevantHealthConditions) {
        this.relevantHealthConditions = relevantHealthConditions;
    }

    public ObservableList<CategoryEntryModel> getAllFuncCategories() {
        ObservableList<CategoryEntryModel> allFuncConditions = FXCollections.observableArrayList();
        allFuncConditions.addAll(nonRelevantFunctionalAbilities);
        allFuncConditions.addAll(relevantFunctionalAbilities);
        return allFuncConditions;
    }

    public ObservableList<CategoryEntryModel> getAllHealthConditions() {
        ObservableList<CategoryEntryModel> allHealthConditions = FXCollections.observableArrayList();
        allHealthConditions.addAll(nonRelevantHealthConditions);
        allHealthConditions.addAll(relevantHealthConditions);
        return allHealthConditions;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
