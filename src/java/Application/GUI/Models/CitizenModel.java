package Application.GUI.Models;

import Application.BE.Category;
import Application.BE.Citizen;
import Application.BE.FunctionalEntry;
import Application.BE.GeneralJournal;
import javafx.beans.property.*;
import javafx.scene.control.TreeItem;

import java.util.HashMap;
import java.util.Map;

public class CitizenModel implements Cloneable
{
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final IntegerProperty template = new SimpleIntegerProperty();
    private final StringProperty coping = new SimpleStringProperty();
    private final StringProperty motivation = new SimpleStringProperty();
    private final StringProperty resources = new SimpleStringProperty();
    private final StringProperty roles = new SimpleStringProperty();
    private final StringProperty habits = new SimpleStringProperty();
    private final StringProperty eduAndJob = new SimpleStringProperty();
    private final StringProperty lifeStory = new SimpleStringProperty();
    private final StringProperty healthInfo = new SimpleStringProperty();
    private final StringProperty assistiveDevices = new SimpleStringProperty();
    private final StringProperty homeLayout = new SimpleStringProperty();
    private final StringProperty network = new SimpleStringProperty();

    private Citizen beCitizen;



    private TreeItem<CategoryEntryModel> relevantFunctionalAbilities;
    private TreeItem<CategoryEntryModel> relevantHealthConditions;
    private TreeItem<CategoryEntryModel> nonRelevantFunctionalAbilities;
    private TreeItem<CategoryEntryModel> nonRelevantHealthConditions;


    public CitizenModel() {
        this("fornavn", "efternavn", 70, true);
    }

    public CitizenModel(String firstName, String lastName, int age, boolean template) {
        this(new Citizen(-1, new GeneralJournal(), SessionModel.getSchool(), firstName, lastName, age, template));
    }

    public CitizenModel(Citizen citizen)
    {
        this.beCitizen = citizen;

        this.firstName.set(beCitizen.getFirstname());
        this.lastName.set(beCitizen.getLastname());
        this.age.set(beCitizen.getAge());
        this.coping.set(beCitizen.getGeneralInfo().getCoping());
        this.motivation.set(beCitizen.getGeneralInfo().getMotivation());
        this.resources.set(beCitizen.getGeneralInfo().getResources());
        this.roles.set(beCitizen.getGeneralInfo().getRoles());
        this.habits.set(beCitizen.getGeneralInfo().getHabits());
        this.eduAndJob.set(beCitizen.getGeneralInfo().getEduAndJob());
        this.lifeStory.set(beCitizen.getGeneralInfo().getLifeStory());
        this.healthInfo.set(beCitizen.getGeneralInfo().getHealthInfo());
        this.assistiveDevices.set(beCitizen.getGeneralInfo().getAssistiveDevices());
        this.homeLayout.set(beCitizen.getGeneralInfo().getHomeLayout());
        this.network.set(beCitizen.getGeneralInfo().getNetwork());
        //initBindings();

       //this.relevantFunctionalAbilities = new HashMap<Category, CategoryEntryModel>();
       //this.relevantHealthConditions = new HashMap<Category, CategoryEntryModel>();
       //this.nonRelevantFunctionalAbilities = new HashMap<Category, CategoryEntryModel>();
       //this.nonRelevantHealthConditions = new HashMap<Category, CategoryEntryModel>();

        // initFunctionalAbilities();
        //initHealthConditions();
    }


    @Override
    public String toString() {
        return firstName.get() + " " + lastName.get();
    }


    private void initFunctionalAbilities() {
        for (FunctionalEntry entry : beCitizen.getFunctionalAbilities().values()) {
            Category category = entry.getCategory();
            CategoryEntryModel model = new CategoryEntryModel(entry);
            if (entry.getRelevant()) {
                relevantFunctionalAbilities.put(category, model);
            }
            else {
                nonRelevantFunctionalAbilities.put(category, model);
            }
        }
    }

    private void initHealthConditions() {
        for (FunctionalEntry entry : beCitizen.getFunctionalAbilities().values()) {
            Category category = entry.getCategory();
            CategoryEntryModel model = new CategoryEntryModel(entry);
            if (entry.getRelevant()) {
                relevantHealthConditions.put(category, model);
            }
            else {
                nonRelevantHealthConditions.put(category, model);
            }
        }
    }


    public String getFirstName() {
        return firstNameProperty().get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstNameProperty().set(name);
    }

    public String getLastName() {
        return lastNameProperty().get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastNameProperty().set(lastName);
    }

    public int getAge() {
        return ageProperty().get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.ageProperty().set(age);
    }

    public String getCoping() {
        return copingProperty().get();
    }

    public void setCoping(String coping) {
        this. copingProperty().set(coping);
    }

    public String getMotivation() {
        return motivationProperty().get();
    }

    public void setMotivation(String motivation) {
        this.motivationProperty().set(motivation);
    }

    public StringProperty copingProperty() {
        return coping;
    }

    public StringProperty motivationProperty() {
        return motivation;
    }

    public String getResources() {
        return resourcesProperty().get();
    }

    public StringProperty resourcesProperty() {
        return resources;
    }

    public void setResources(String resources) {
        this.resourcesProperty().set(resources);
    }

    public String getRoles() {
        return rolesProperty().get();
    }

    public StringProperty rolesProperty() {
        return roles;
    }

    public void setRoles(String roles) {
        this.rolesProperty().set(roles);
    }

    public String getHabits() {
        return habitsProperty().get();
    }

    public StringProperty habitsProperty() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habitsProperty().set(habits);
    }

    public String getEduAndJob() {
        return eduAndJobProperty().get();
    }

    public StringProperty eduAndJobProperty() {
        return eduAndJob;
    }

    public void setEduAndJob(String eduAndJob) {
        this.eduAndJobProperty().set(eduAndJob);
    }

    public String getLifeStory() {
        return lifeStoryProperty().get();
    }

    public StringProperty lifeStoryProperty() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStoryProperty().set(lifeStory);
    }

    public String getHealthInfo() {
        return healthInfoProperty().get();
    }

    public StringProperty healthInfoProperty() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfoProperty().set(healthInfo);
    }

    public String getAssistiveDevices() {
        return assistiveDevicesProperty().get();
    }

    public StringProperty assistiveDevicesProperty() {
        return assistiveDevices;
    }

    public void setAssistiveDevices(String assistiveDevices) {
        this.assistiveDevicesProperty().set(assistiveDevices);
    }

    public String getHomeLayout() {
        return homeLayoutProperty().get();
    }

    public StringProperty homeLayoutProperty() {
        return homeLayout;
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayoutProperty().set(homeLayout);
    }

    public String getNetwork() {
        return networkProperty().get();
    }

    public StringProperty networkProperty() {
        return network;
    }

    public void setNetwork(String network) {
        this.networkProperty().set(network);
    }



    public Map<Category, CategoryEntryModel> getNonRelevantFunctionalAbilities() {
        return nonRelevantFunctionalAbilities;
    }

    public void setNonRelevantFunctionalAbilities(Map<Category, CategoryEntryModel> nonRelevantFunctionalAbilities) {
        this.nonRelevantFunctionalAbilities = nonRelevantFunctionalAbilities;
    }

    public Map<Category, CategoryEntryModel> getNonRelevantHealthConditions() {
        return nonRelevantHealthConditions;
    }

    public void setNonRelevantHealthConditions(Map<Category, CategoryEntryModel> nonRelevantHealthConditions) {
        this.nonRelevantHealthConditions = nonRelevantHealthConditions;
    }

    public Map<Category, CategoryEntryModel> getRelevantFunctionalAbilities() {
        return relevantFunctionalAbilities;
    }

    public void setRelevantFunctionalAbilities(Map<Category, CategoryEntryModel> relevantFunctionalAbilities) {
        this.relevantFunctionalAbilities = relevantFunctionalAbilities;
    }

    public Map<Category, CategoryEntryModel> getRelevantHealthConditions() {
        return relevantHealthConditions;
    }

    public void setRelevantHealthConditions(Map<Category, CategoryEntryModel> relevantHealthConditions) {
        this.relevantHealthConditions = relevantHealthConditions;
    }

    public Map<Category, CategoryEntryModel> getAllFuncCategories() {
        HashMap<Category, CategoryEntryModel> allFuncCategories = new HashMap<>();
        allFuncCategories.putAll(nonRelevantFunctionalAbilities);
        allFuncCategories.putAll(relevantFunctionalAbilities);
        return allFuncCategories;
    }

    public Map<Category, CategoryEntryModel> getAllHealthConditions() {
        HashMap<Category, CategoryEntryModel> allHealthConditions = new HashMap<>();
        allHealthConditions.putAll(nonRelevantHealthConditions);
        allHealthConditions.putAll(relevantHealthConditions);
        return allHealthConditions;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setTemplate(int template)
    {
        this.template.set(template);
    }
}
