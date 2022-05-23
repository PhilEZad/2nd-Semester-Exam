package Application.GUI.Models;

import Application.BE.Category;
import Application.BE.Citizen;
import Application.BE.ContentEntry;
import Application.BE.GeneralJournal;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.Map;

public class CitizenModel implements Cloneable{

    private StringProperty firstName;
    private StringProperty lastName;
    private IntegerProperty age;


    private StringProperty coping;
    private StringProperty motivation;
    private StringProperty resources;
    private StringProperty roles;
    private StringProperty habits;
    private StringProperty eduAndJob;
    private StringProperty lifeStory;
    private StringProperty healthInfo;
    private StringProperty assistiveDevices;
    private StringProperty homeLayout;
    private StringProperty network;

    private Citizen beCitizen;

    private Map<Category, CategoryEntryModel> relevantFunctionalAbilities;
    private Map<Category, CategoryEntryModel> relevantHealthConditions;
    private Map<Category, CategoryEntryModel> nonRelevantFunctionalAbilities;
    private Map<Category, CategoryEntryModel> nonRelevantHealthConditions;


    public CitizenModel(Citizen citizen) {
        this.beCitizen = citizen;
        initProperties();

        this.firstName.set(beCitizen.getFirstname());
        this.lastName.set(beCitizen.getLastname());
        this.age.set(beCitizen.getAge());

        GeneralJournal journal = citizen.getGeneralJournal();

        this.coping.set(journal.getCoping());
        this.motivation.set(journal.getMotivation());
        this.resources.set(journal.getResources());
        this.roles.set(journal.getRoles());
        this.habits.set(journal.getHabits());
        this.eduAndJob.set(journal.getEduAndJob());
        this.lifeStory.set(journal.getLifeStory());
        this.healthInfo.set(journal.getHealthInfo());
        this.assistiveDevices.set(journal.getAssistiveDevices());
        this.homeLayout.set(journal.getHomeLayout());
        this.network.set(journal.getNetwork());
        initBindings();


        this.relevantFunctionalAbilities = new HashMap<Category, CategoryEntryModel>();
        this.relevantHealthConditions = new HashMap<Category, CategoryEntryModel>();
        this.nonRelevantFunctionalAbilities = new HashMap<Category, CategoryEntryModel>();
        this.nonRelevantHealthConditions = new HashMap<Category, CategoryEntryModel>();

        initFunctionalAbilities();
        initHealthConditions();
    }


    private void initProperties(){
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.age = new SimpleIntegerProperty();

        this.coping = new SimpleStringProperty();
        this.motivation = new SimpleStringProperty();
        this.resources = new SimpleStringProperty();
        this.roles = new SimpleStringProperty();
        this.habits = new SimpleStringProperty();
        this.eduAndJob = new SimpleStringProperty();
        this.lifeStory = new SimpleStringProperty();
        this.healthInfo = new SimpleStringProperty();
        this.assistiveDevices = new SimpleStringProperty();
        this.homeLayout = new SimpleStringProperty();
        this.network = new SimpleStringProperty();
    }

    private void initBindings() {
        this.firstName.bindBidirectional(new SimpleStringProperty(beCitizen.getFirstname()));
        this.lastName.bindBidirectional(new SimpleStringProperty(beCitizen.getLastname()));
        this.age.bindBidirectional(new SimpleIntegerProperty(beCitizen.getAge()));

        this.coping.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getCoping()));
        this.motivation.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getMotivation()));
        this.resources.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getResources()));
        this.roles.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getRoles()));
        this.habits.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getHabits()));
        this.eduAndJob.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getEduAndJob()));
        this.lifeStory.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getLifeStory()));
        this.healthInfo.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getHealthInfo()));
        this.assistiveDevices.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getAssistiveDevices()));
        this.homeLayout.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getHomeLayout()));
        this.network.bindBidirectional(new SimpleStringProperty(beCitizen.getGeneralJournal().getNetwork()));
    }

    @Override
    public String toString() {
        return firstName.get() + " " + lastName.get();
    }


    private void initFunctionalAbilities() {
        for (ContentEntry entry : beCitizen.getFunctionalAbilities().values()) {
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
        for (ContentEntry entry : beCitizen.getFunctionalAbilities().values()) {
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
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName.set(name);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
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


    public String getCoping() {
        return coping.get();
    }

    public void setCoping(String coping) {
        this.coping.set(coping);
    }

    public String getMotivation() {
        return motivation.get();
    }

    public void setMotivation(String motivation) {
        this.motivation.set(motivation);
    }

    public StringProperty copingProperty() {
        return coping;
    }

    public StringProperty motivationProperty() {
        return motivation;
    }

    public String getResources() {
        return resources.get();
    }

    public StringProperty resourcesProperty() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources.set(resources);
    }

    public String getRoles() {
        return roles.get();
    }

    public StringProperty rolesProperty() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles.set(roles);
    }

    public String getHabits() {
        return habits.get();
    }

    public StringProperty habitsProperty() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits.set(habits);
    }

    public String getEduAndJob() {
        return eduAndJob.get();
    }

    public StringProperty eduAndJobProperty() {
        return eduAndJob;
    }

    public void setEduAndJob(String eduAndJob) {
        this.eduAndJob.set(eduAndJob);
    }

    public String getLifeStory() {
        return lifeStory.get();
    }

    public StringProperty lifeStoryProperty() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory.set(lifeStory);
    }

    public String getHealthInfo() {
        return healthInfo.get();
    }

    public StringProperty healthInfoProperty() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo.set(healthInfo);
    }

    public String getAssistiveDevices() {
        return assistiveDevices.get();
    }

    public StringProperty assistiveDevicesProperty() {
        return assistiveDevices;
    }

    public void setAssistiveDevices(String assistiveDevices) {
        this.assistiveDevices.set(assistiveDevices);
    }

    public String getHomeLayout() {
        return homeLayout.get();
    }

    public StringProperty homeLayoutProperty() {
        return homeLayout;
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayout.set(homeLayout);
    }

    public String getNetwork() {
        return network.get();
    }

    public StringProperty networkProperty() {
        return network;
    }

    public void setNetwork(String network) {
        this.network.set(network);
    }

    public Citizen getBeCitizen() {
        return beCitizen;
    }

    public void setBeCitizen(Citizen beCitizen) {
        this.beCitizen = beCitizen;
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
}
