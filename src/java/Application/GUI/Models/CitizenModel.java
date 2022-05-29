package Application.GUI.Models;

import Application.BE.*;
import Application.BLL.SessionManager;
import javafx.beans.property.*;
import javafx.scene.control.TreeItem;

import java.util.*;

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

    private ObjectProperty<TreeItem<CategoryEntryModel>> functionalAbilitiesTree = new SimpleObjectProperty<>(new TreeItem<>());
    private ObjectProperty<TreeItem<CategoryEntryModel>> healthConditionsTree = new SimpleObjectProperty<>(new TreeItem<>());


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

        updateTreeStructure();
    }

    public CitizenModel() {}

    public static CitizenModel convert(Citizen citizen) {
        return new CitizenModel(citizen);
    }

    public static Citizen convert(CitizenModel citizen)
    {
        var citizenCopy = new Citizen(citizen.getID(), SessionManager.getCurrent().getSchool(), citizen.getFirstName(), citizen.getLastName(), citizen.getAge(), citizen.template.get() == 1);

        citizenCopy.setGeneralJournal(new GeneralJournal(citizen.beCitizen.getGeneralInfo().getID(), citizen.getID(), citizen.getCoping(), citizen.getMotivation(), citizen.getResources(), citizen.getRoles(), citizen.getHabits(), citizen.getEduAndJob(), citizen.getLifeStory(), citizen.getHealthInfo(), citizen.getAssistiveDevices(), citizen.getHomeLayout(), citizen.getNetwork()));

        citizenCopy.getFunctionalAbilities().clear();
        citizenCopy.getHealthConditions().clear();

        citizen.getRelevantFunctionalAbilities().forEach(model -> citizenCopy.getFunctionalAbilities().add((FunctionalEntry) CategoryEntryModel.convert(model, CategoryType.FUNCTIONAL_ABILITY)));
        citizen.getNonRelevantFunctionalAbilities().forEach(model -> citizenCopy.getFunctionalAbilities().add((FunctionalEntry) CategoryEntryModel.convert(model, CategoryType.FUNCTIONAL_ABILITY)));

        citizen.getRelevantHealthConditions().forEach(model -> citizenCopy.getHealthConditions().add((HealthEntry) CategoryEntryModel.convert(model, CategoryType.HEALTH_CONDITION)));
        citizen.getNonRelevantHealthConditions().forEach(model -> citizenCopy.getHealthConditions().add((HealthEntry) CategoryEntryModel.convert(model, CategoryType.HEALTH_CONDITION)));

        return citizenCopy;
    }

    public void updateTreeStructure()
    {
        functionalAbilitiesTree.set(createTreeStructure(true, CategoryType.FUNCTIONAL_ABILITY));
        healthConditionsTree.set(createTreeStructure(true, CategoryType.HEALTH_CONDITION));
    }

    public TreeItem<CategoryEntryModel> createTreeStructure(boolean includeNonRelevant, CategoryType type)
    {
        TreeItem<CategoryEntryModel> root = new TreeItem<>();
        root.setExpanded(true);

        var all = new ArrayList<CategoryEntryModel>();

        if(type == CategoryType.FUNCTIONAL_ABILITY)
        {
            all.addAll(this.relevantFunctionalAbilities);
            if (includeNonRelevant)
                all.addAll(this.nonRelevantFunctionalAbilities);
        }
        else if(type == CategoryType.HEALTH_CONDITION)
        {
            all.addAll(this.relevantHealthConditions);
            if (includeNonRelevant)
                all.addAll(this.nonRelevantHealthConditions);
        }
        else throw new UnsupportedOperationException("CategoryType not supported");

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
    public String toString() {
        return firstName.get() + " " + lastName.get();
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public int getID()
    {
        return this.id;
    }

    public ObjectProperty<TreeItem<CategoryEntryModel>> functionalAbilitiesTreeProperty() {
        return functionalAbilitiesTree;
    }

    public ObjectProperty<TreeItem<CategoryEntryModel>> healthConditionsTreeProperty() {
        return healthConditionsTree;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public StringProperty copingProperty() {
        return coping;
    }

    public StringProperty motivationProperty() {
        return motivation;
    }

    public StringProperty resourcesProperty() {
        return resources;
    }

    public StringProperty rolesProperty() {
        return roles;
    }

    public StringProperty habitsProperty() {
        return habits;
    }

    public StringProperty eduAndJobProperty() {
        return eduAndJob;
    }

    public StringProperty lifeStoryProperty() {
        return lifeStory;
    }

    public StringProperty healthInfoProperty() {
        return healthInfo;
    }

    public StringProperty networkProperty() {
        return network;
    }

    public Citizen getBeCitizen() {
        return beCitizen;
    }


    @Override
    public CitizenModel clone() throws CloneNotSupportedException
    {
        super.clone();
        CitizenModel clone = new CitizenModel();

        clone.setID(this.getID());
        clone.setFirstName(this.getFirstName());
        clone.setLastName(this.getLastName());
        clone.setAge(this.getAge());
        clone.setCoping(this.getCoping());
        clone.setMotivation(this.getMotivation());
        clone.setResources(this.getResources());
        clone.setRoles(this.getRoles());
        clone.setHabits(this.getHabits());
        clone.setEduAndJob(this.getEduAndJob());
        clone.setLifeStory(this.getLifeStory());
        clone.setHealthInfo(this.getHealthInfo());
        clone.setNetwork(this.getNetwork());
        clone.setBeCitizen(this.getBeCitizen());

        var entries = new ArrayList<CategoryEntryModel>();
        this.getRelevantFunctionalAbilities().forEach(categoryEntryModel -> entries.add(categoryEntryModel.clone()));
        clone.setRelevantFunctionalAbilities(entries);

        entries.clear();
        this.getRelevantHealthConditions().forEach(categoryEntryModel -> entries.add(categoryEntryModel.clone()));
        clone.setRelevantHealthConditions(entries);

        entries.clear();
        this.getNonRelevantFunctionalAbilities().forEach(categoryEntryModel -> entries.add(categoryEntryModel.clone()));
        clone.setNonRelevantFunctionalAbilities(entries);

        entries.clear();
        this.getNonRelevantHealthConditions().forEach(categoryEntryModel -> entries.add(categoryEntryModel.clone()));
        clone.setNonRelevantHealthConditions(entries);

        clone.updateTreeStructure();

        return clone;
    }

    public StringProperty assistiveDevicesProperty() {
        return assistiveDevices;
    }

    public StringProperty homeLayoutProperty() {
        return homeLayout;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public int getTemplate() {
        return template.get();
    }

    public IntegerProperty templateProperty() {
        return template;
    }

    public void setTemplate(int template) {
        this.template.set(template);
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

    public String getResources() {
        return resources.get();
    }

    public void setResources(String resources) {
        this.resources.set(resources);
    }

    public String getRoles() {
        return roles.get();
    }

    public void setRoles(String roles) {
        this.roles.set(roles);
    }

    public String getHabits() {
        return habits.get();
    }

    public void setHabits(String habits) {
        this.habits.set(habits);
    }

    public String getEduAndJob() {
        return eduAndJob.get();
    }

    public void setEduAndJob(String eduAndJob) {
        this.eduAndJob.set(eduAndJob);
    }

    public String getLifeStory() {
        return lifeStory.get();
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory.set(lifeStory);
    }

    public String getHealthInfo() {
        return healthInfo.get();
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo.set(healthInfo);
    }

    public String getAssistiveDevices() {
        return assistiveDevices.get();
    }

    public void setAssistiveDevices(String assistiveDevices) {
        this.assistiveDevices.set(assistiveDevices);
    }

    public String getHomeLayout() {
        return homeLayout.get();
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayout.set(homeLayout);
    }

    public String getNetwork() {
        return network.get();
    }

    public void setNetwork(String network) {
        this.network.set(network);
    }

    public void setBeCitizen(Citizen beCitizen) {
        this.beCitizen = beCitizen;
    }

    public List<CategoryEntryModel> getRelevantFunctionalAbilities() {
        return relevantFunctionalAbilities;
    }

    public void setRelevantFunctionalAbilities(List<CategoryEntryModel> relevantFunctionalAbilities) {
        this.relevantFunctionalAbilities = relevantFunctionalAbilities;
    }

    public List<CategoryEntryModel> getRelevantHealthConditions() {
        return relevantHealthConditions;
    }

    public void setRelevantHealthConditions(List<CategoryEntryModel> relevantHealthConditions) {
        this.relevantHealthConditions = relevantHealthConditions;
    }

    public List<CategoryEntryModel> getNonRelevantFunctionalAbilities() {
        return nonRelevantFunctionalAbilities;
    }

    public void setNonRelevantFunctionalAbilities(List<CategoryEntryModel> nonRelevantFunctionalAbilities) {
        this.nonRelevantFunctionalAbilities = nonRelevantFunctionalAbilities;
    }

    public List<CategoryEntryModel> getNonRelevantHealthConditions() {
        return nonRelevantHealthConditions;
    }

    public void setNonRelevantHealthConditions(List<CategoryEntryModel> nonRelevantHealthConditions) {
        this.nonRelevantHealthConditions = nonRelevantHealthConditions;
    }

    public TreeItem<CategoryEntryModel> getFunctionalAbilitiesTree() {
        return functionalAbilitiesTree.get();
    }

    public void setFunctionalAbilitiesTree(TreeItem<CategoryEntryModel> functionalAbilitiesTree) {
        this.functionalAbilitiesTree.set(functionalAbilitiesTree);
    }

    public TreeItem<CategoryEntryModel> getHealthConditionsTree() {
        return healthConditionsTree.get();
    }

    public void setHealthConditionsTree(TreeItem<CategoryEntryModel> healthConditionsTree) {
        this.healthConditionsTree.set(healthConditionsTree);
    }

}
