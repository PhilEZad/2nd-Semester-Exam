package Application.GUI.Models;

import Application.BE.*;
import javafx.beans.property.*;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;

public class CitizenModel implements Cloneable
{
    private int id;

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

    private Map<Category, CategoryEntryModel> relevantFunctionalAbilities = new HashMap<>();
    private Map<Category, CategoryEntryModel> relevantHealthConditions = new HashMap<>();
    private Map<Category, CategoryEntryModel> nonRelevantFunctionalAbilities = new HashMap<>();
    private Map<Category, CategoryEntryModel> nonRelevantHealthConditions = new HashMap<>();


    public CitizenModel(String firstName, String lastName, int age, boolean template)
    {
        this(new Citizen(-1, SessionModel.getSchool(), firstName, lastName, age, template));
    }

    public CitizenModel(Citizen citizen)
    {
        this();

        this.beCitizen = citizen;

        this.id = citizen.getID();
        this.firstName.set(citizen.getFirstname());
        this.lastName.set(citizen.getLastname());
        this.age.set(citizen.getAge());
        this.coping.set(citizen.getGeneralInfo().getCoping());
        this.motivation.set(citizen.getGeneralInfo().getMotivation());
        this.resources.set(citizen.getGeneralInfo().getResources());
        this.roles.set(citizen.getGeneralInfo().getRoles());
        this.habits.set(citizen.getGeneralInfo().getHabits());
        this.eduAndJob.set(citizen.getGeneralInfo().getEduAndJob());
        this.lifeStory.set(citizen.getGeneralInfo().getLifeStory());
        this.healthInfo.set(citizen.getGeneralInfo().getHealthInfo());
        this.assistiveDevices.set(citizen.getGeneralInfo().getAssistiveDevices());
        this.homeLayout.set(citizen.getGeneralInfo().getHomeLayout());
        this.network.set(citizen.getGeneralInfo().getNetwork());

        for (FunctionalEntry entry : citizen.getFunctionalAbilities().values())
        {
            Category category = entry.getCategory();
            CategoryEntryModel model = new CategoryEntryModel(entry);
            if (entry.isRelevant())
            {
                relevantFunctionalAbilities.put(category, model);
            }
            else
            {
                nonRelevantFunctionalAbilities.put(category, model);
            }
        }

        for (HealthEntry entry : citizen.getHealthConditions().values())
        {
            Category category = entry.getCategory();
            CategoryEntryModel model = new CategoryEntryModel(entry);

            if (entry.isRelevant())
            {
                relevantHealthConditions.put(category, model);
            }
            else
            {
                nonRelevantHealthConditions.put(category, model);
            }
        }
    }

    public CitizenModel() {}

    public static CitizenModel convert(Citizen citizen) {
        return new CitizenModel(citizen);
    }


    @Override
    public String toString() {
        return firstName.get() + " " + lastName.get();
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

    public void setTemplate(int template)
    {
        this.template.set(template);
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public int getID()
    {
        return this.id;
    }
}
