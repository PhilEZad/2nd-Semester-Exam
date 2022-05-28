package Application.GUI.Models;

import Application.BE.*;
import javafx.beans.property.*;
import javafx.collections.ObservableMap;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private List<CategoryEntryModel> relevantFunctionalAbilities = new ArrayList<>();
    private List<CategoryEntryModel> relevantHealthConditions = new ArrayList<>();

    private List<CategoryEntryModel> nonRelevantFunctionalAbilities = new ArrayList<>();
    private List<CategoryEntryModel> nonRelevantHealthConditions = new ArrayList<>();


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

        for (FunctionalEntry entry : citizen.getFunctionalAbilities())
        {
            Category category = entry.getCategory();
            CategoryEntryModel model = new CategoryEntryModel(entry);
            model.setCategory(category);

            if (entry.isRelevant())
            {
                relevantFunctionalAbilities.add(model);
            }
            else
            {
                nonRelevantFunctionalAbilities.add(model);
            }
        }

        for (HealthEntry entry : citizen.getHealthConditions())
        {
            CategoryEntryModel model = new CategoryEntryModel(entry);
            model.setCategory(entry.getCategory());

            if (entry.isRelevant())
            {
                relevantHealthConditions.add(model);
            }
            else
            {
                nonRelevantHealthConditions.add(model);
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


    public Map<Category, CategoryEntryModel> getRelevantFunctionalAbilities() {
        return null;
    }


    public Map<Category, CategoryEntryModel> getRelevantHealthConditions() {
        return null;
    }


    public Map<Category, CategoryEntryModel> getAllFuncCategories() {
        HashMap<Category, CategoryEntryModel> allFuncCategories = new HashMap<>();
        //allFuncCategories.putAll(null);
        //allFuncCategories.putAll(null);
        return allFuncCategories;
    }

    public Map<Category, CategoryEntryModel> getAllHealthConditions() {
        HashMap<Category, CategoryEntryModel> allHealthConditions = new HashMap<>();
        //allHealthConditions.putAll(null);
        //allHealthConditions.putAll(null);
        return allHealthConditions;
    }

    public TreeItem<CategoryEntryModel> createNestedTreeOfAllHealthConditions()
    {
        TreeItem<CategoryEntryModel> root = new TreeItem<>();
        root.setExpanded(true);

        var all = new ArrayList<CategoryEntryModel>();
        all.addAll(this.relevantHealthConditions);
        all.addAll(this.nonRelevantHealthConditions);

        Map<Category, List<CategoryEntryModel>> map = new HashMap<>();

        for (var outer : all)
        {
            if (map.containsKey(outer.getCategory().getParent()))
            {
                map.get(outer.getCategory().getParent()).add(outer);
            }
            else
            {
                map.put(outer.getCategory().getParent(), new ArrayList<>() {{add(outer);}});
            }
        }

        for (Category key : map.keySet())
        {
            TreeItem<CategoryEntryModel> leafs = new TreeItem<>(new CategoryEntryModel(key.getName()));

            for (var value : map.get(key))
            {
                leafs.getChildren().add(new TreeItem<>(value));
            }

            root.getChildren().add(leafs);
        }

        return root;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
