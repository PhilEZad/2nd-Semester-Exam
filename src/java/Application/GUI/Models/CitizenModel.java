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
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public StringProperty assistiveDevicesProperty() {
        return assistiveDevices;
    }

    public StringProperty homeLayoutProperty() {
        return homeLayout;
    }


}
