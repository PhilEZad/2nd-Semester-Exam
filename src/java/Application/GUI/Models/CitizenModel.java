package Application.GUI.Models;

import Application.BE.CategoryEntry;
import Application.BE.ContactInfo;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class CitizenModel {

    private StringProperty name;
    private StringProperty surname;
    private IntegerProperty age;
    private LocalDate birthDate;
    private StringProperty helpStatus;
    private StringProperty civilianStatus;
    private StringProperty address;
    private ListProperty<ContactInfo> contactInfo;

    private String mastering;
    private String motivation;
    private String resources;
    private String roles;
    private String habits;
    private String eduAndJob;
    private String lifeStory;
    private String healthInfo;
    private String assistiveDevices;
    private String homeLayout;
    private String network;

    private ObservableList<CategoryEntryModel> relevantFunctionalAbilities;
    private ObservableList<CategoryEntryModel> relevantHealthConditions;
    private ObservableList<CategoryEntryModel> nonRelevantFunctionalAbilities;
    private ObservableList<CategoryEntryModel> nonRelevantHealthConditions;


    public CitizenModel(String name, String surname, int age, LocalDate birthDate, String helpStatus, String civilianStatus, String address, ObservableList<ContactInfo> contactInfo) {
        initProperties();
        this.name.set(name);
        this.surname.set(surname);
        this.age.set(age);
        this.helpStatus.set(helpStatus);
        this.civilianStatus.set(civilianStatus);
        this.address.set(address);
        this.contactInfo.set(contactInfo);
        this.birthDate = birthDate;
        this.mastering = "";
        this.motivation = "";
        this.resources = "";
        this.roles = "";
        this.habits = "";
        this.eduAndJob = "";
        this.lifeStory = "";
        this.healthInfo = "";
        this.assistiveDevices = "";
        this.homeLayout = "";
        this.network = "";

        this.relevantFunctionalAbilities = FXCollections.observableArrayList();
        this.relevantHealthConditions = FXCollections.observableArrayList();
        this.nonRelevantFunctionalAbilities = FXCollections.observableArrayList();
        this.nonRelevantHealthConditions = FXCollections.observableArrayList();

        initFunctionalAbilities();
        initHealthConditions();
    }

    public CitizenModel() {
        initProperties();
        this.name = new SimpleStringProperty();
        this.surname = new SimpleStringProperty();
        this.age = new SimpleIntegerProperty();
        this.helpStatus = new SimpleStringProperty();
        this.civilianStatus = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.contactInfo = new SimpleListProperty<>();
        this.birthDate = LocalDate.now();
        this.mastering = "";
        this.motivation = "";
        this.resources = "";
        this.roles = "";
        this.habits = "";
        this.eduAndJob = "";
        this.lifeStory = "";
        this.healthInfo = "";
        this.assistiveDevices = "";
        this.homeLayout = "";
        this.network = "";


        this.relevantFunctionalAbilities = FXCollections.observableArrayList();
        this.relevantHealthConditions = FXCollections.observableArrayList();
        this.nonRelevantFunctionalAbilities = FXCollections.observableArrayList();
        this.nonRelevantHealthConditions = FXCollections.observableArrayList();

        initFunctionalAbilities();
        initHealthConditions();
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
        relevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Walking", 1, true, false)));
        relevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Climbing", 1, true, false)));
        relevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Swimming", 1, true, false)));
        relevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Bathing", 4, true, false)));

        nonRelevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Sleeping", 0, true, false)));
        nonRelevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Eating", 0, true, false)));
        nonRelevantFunctionalAbilities.add(new CategoryEntryModel(new CategoryEntry(0, "Toileting", 0, true, false)));
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

    public String getMastering() {
        return mastering;
    }

    public void setMastering(String mastering) {
        this.mastering = mastering;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public String getEduAndJob() {
        return eduAndJob;
    }

    public void setEduAndJob(String eduAndJob) {
        this.eduAndJob = eduAndJob;
    }

    public String getLifeStory() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory = lifeStory;
    }

    public String getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo = healthInfo;
    }

    public String getAssistiveDevices() {
        return assistiveDevices;
    }

    public void setAssistiveDevices(String assistiveDevices) {
        this.assistiveDevices = assistiveDevices;
    }

    public String getHomeLayout() {
        return homeLayout;
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayout = homeLayout;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
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
}
