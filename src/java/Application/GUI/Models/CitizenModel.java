package Application.GUI.Models;

import Application.BE.Category;
import Application.BE.Citizen;
import Application.BE.ContentEntry;
import Application.BE.GeneralJournal;
import javafx.beans.property.*;

import java.util.HashMap;

public class CitizenModel implements Cloneable
{
    private final IntegerProperty id = new SimpleIntegerProperty();

    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
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

    private HashMap<Category, CategoryEntryModel> relevantFunctionalAbilities = new HashMap<>();
    private HashMap<Category, CategoryEntryModel> relevantHealthConditions = new HashMap<>();
    private HashMap<Category, CategoryEntryModel> nonRelevantFunctionalAbilities = new HashMap<>();
    private HashMap<Category, CategoryEntryModel> nonRelevantHealthConditions = new HashMap<>();


    public static Citizen convert(CitizenModel model)
    {
        GeneralJournal general = new GeneralJournal();

        general.setCoping(model.getCoping());
        general.setMotivation(model.getMotivation());
        general.setResources(model.getResources());
        general.setRoles(model.getRoles());
        general.setHabits(model.getHabits());
        general.setEduAndJob(model.getEduAndJob());
        general.setLifeStory(model.getLifeStory());
        general.setHealthInfo(model.getHealthInfo());
        general.setAssistiveDevices(model.getAssistiveDevices());
        general.setHomeLayout(model.getHomeLayout());
        general.setNetwork(model.getNetwork());

        Citizen citizen = new Citizen(model.getId(), general, SessionModel.getSchool(), model.getFirstName(), model.getLastName(), model.getAge());

        citizen.getHealthConditions().clear();
        citizen.getFunctionalAbilities().clear();

        model.getAllFuncCategories().forEach((category, categoryEntryModel) -> {
            citizen.getFunctionalAbilities().put(category, CategoryEntryModel.convert(categoryEntryModel));
        });

        model.getAllHealthConditions().forEach((category, categoryEntryModel) -> {
            citizen.getHealthConditions().put(category, CategoryEntryModel.convert(categoryEntryModel));
        });

        return citizen;
    }

    public static CitizenModel convert(Citizen citizen)
    {
        CitizenModel model = new CitizenModel();

        model.setId(citizen.getId());
        model.setFirstName(citizen.getFirstname());
        model.setLastName(citizen.getLastname());
        model.setAge(citizen.getAge());

        if (citizen.getGeneralJournal() != null)
        {
            model.setCoping(citizen.getGeneralJournal().getCoping());
            model.setMotivation(citizen.getGeneralJournal().getMotivation());
            model.setResources(citizen.getGeneralJournal().getResources());
            model.setRoles(citizen.getGeneralJournal().getRoles());
            model.setHabits(citizen.getGeneralJournal().getHabits());
            model.setEduAndJob(citizen.getGeneralJournal().getEduAndJob());
            model.setLifeStory(citizen.getGeneralJournal().getLifeStory());
            model.setHealthInfo(citizen.getGeneralJournal().getHealthInfo());
            model.setAssistiveDevices(citizen.getGeneralJournal().getAssistiveDevices());
            model.setHomeLayout(citizen.getGeneralJournal().getHomeLayout());
            model.setNetwork(citizen.getGeneralJournal().getNetwork());
        }

        for (ContentEntry entry : citizen.getFunctionalAbilities().values())
        {
            Category category = entry.getCategory();
            CategoryEntryModel cat = new CategoryEntryModel(entry);
            if (entry.getRelevant())
            {
                model.getRelevantFunctionalAbilities().put(category, cat);
            }
            else {
                model.getNonRelevantFunctionalAbilities().put(category, cat);
            }
        }

        for (ContentEntry entry : citizen.getHealthConditions().values())
        {
            Category category = entry.getCategory();
            CategoryEntryModel cat = new CategoryEntryModel(entry);

            if (entry.getRelevant()) {
                model.getRelevantHealthConditions().put(category, cat);
            }
            else {
                model.getNonRelevantHealthConditions().put(category, cat);
            }
        }

        return model;
    }


    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }


    public int getId() {
        return idProperty().get();
    }

    public void setId(int id) {
        idProperty().setValue(id);
    }

    public IntegerProperty idProperty() {
        return id;
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
        this.copingProperty().set(coping);
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


    public HashMap<Category, CategoryEntryModel> relevantFunctionalAbilitiesProperty() {
        return relevantFunctionalAbilities;
    }

    public HashMap<Category, CategoryEntryModel> getRelevantFunctionalAbilities() {
        return relevantFunctionalAbilitiesProperty();
    }

    public void setRelevantFunctionalAbilities(HashMap<Category, CategoryEntryModel> map) {
        this.relevantFunctionalAbilities = map;
    }


    public HashMap<Category, CategoryEntryModel> relevantHealthConditionsProperty()
    {
        return relevantHealthConditions;
    }

    public HashMap<Category, CategoryEntryModel> getRelevantHealthConditions() {
        return relevantHealthConditions;
    }

    public void setRelevantHealthConditions(HashMap<Category, CategoryEntryModel> map) {
        relevantHealthConditions = map;
    }

    public HashMap<Category, CategoryEntryModel> nonRelevantFunctionalAbilitiesProperty() {
        return nonRelevantFunctionalAbilities;
    }

    public HashMap<Category, CategoryEntryModel> getNonRelevantFunctionalAbilities() {
        return nonRelevantFunctionalAbilities;
    }

    public void setNonRelevantFunctionalAbilities(HashMap<Category, CategoryEntryModel> map) {
        nonRelevantFunctionalAbilities = map;
    }

    public HashMap<Category, CategoryEntryModel> nonRelevantHealthConditionsProperty()
    {
        return nonRelevantHealthConditions;
    }

    public HashMap<Category, CategoryEntryModel> getNonRelevantHealthConditions() {
        return nonRelevantHealthConditions;
    }

    public void setNonRelevantHealthConditions(HashMap<Category, CategoryEntryModel> map) {
        this.nonRelevantHealthConditions = map;
    }


    public HashMap<Category, CategoryEntryModel> getAllFuncCategories()
    {
        HashMap<Category, CategoryEntryModel> allFuncCategories = new HashMap<>();

        allFuncCategories.putAll(nonRelevantFunctionalAbilities);
        allFuncCategories.putAll(relevantFunctionalAbilities);

        return allFuncCategories;
    }

    public HashMap<Category, CategoryEntryModel> getAllHealthConditions()
    {
        HashMap<Category, CategoryEntryModel> allHealthConditions = new HashMap<>();

        allHealthConditions.putAll(nonRelevantHealthConditions);
        allHealthConditions.putAll(relevantHealthConditions);

        return allHealthConditions;
    }

    @Override
    public CitizenModel clone() {
        CitizenModel model = new CitizenModel();

        model.setId(this.getId());
        model.setFirstName(this.getFirstName());
        model.setLastName(this.getLastName());
        model.setAge(this.getAge());

        model.setCoping(this.getCoping());
        model.setMotivation(this.getMotivation());
        model.setResources(this.getResources());
        model.setRoles(this.getRoles());
        model.setHabits(this.getHabits());
        model.setEduAndJob(this.getEduAndJob());
        model.setLifeStory(this.getLifeStory());
        model.setHealthInfo(this.getHealthInfo());
        model.setAssistiveDevices(this.getAssistiveDevices());
        model.setHomeLayout(this.getHomeLayout());
        model.setNetwork(this.getNetwork());

        model.setRelevantFunctionalAbilities(this.getRelevantFunctionalAbilities());
        model.setNonRelevantFunctionalAbilities(this.getNonRelevantFunctionalAbilities());
        model.setRelevantHealthConditions(this.getRelevantHealthConditions());
        model.setNonRelevantHealthConditions(this.getNonRelevantHealthConditions());

        return model;
    }


}
