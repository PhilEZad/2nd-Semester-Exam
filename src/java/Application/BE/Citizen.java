package Application.BE;

import com.c05mic.generictree.Tree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class Citizen implements Cloneable, IUniqueIdentifier<Integer>
{
    private int id;
    private School school;
    private String firstname;
    private String lastname;
    private int age;
    private boolean isTemplate;

    private GeneralJournal journal;
    private int zipCode;

    private Tree<HealthEntry> healthConditions;
    private Tree<FunctionalEntry> functionalAbilities;


    public Citizen(int id, GeneralJournal journal, School school, String firstname, String lastname, int age, boolean template)
    {
        this(id);
        this.journal = journal;
        this.school = school;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.isTemplate = template;
    }

    public Citizen(int id)
    {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id == null ? -1 : id;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getAge() {
        return age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public GeneralJournal getJournal()
    {
        return journal;
    }

    public void setJournal(GeneralJournal journal)
    {
        this.journal = journal;
    }

    public School getSchool()
    {
        return school;
    }

    public void setSchool(School school)
    {
        this.school = school;
    }

    public void setContent(HashMap<Category, FunctionalEntry> content)
    {
        // populate lists (healthCategoryEntries, funcCategoryEntries)
    }

    public GeneralJournal getGeneralInfo () {
        return journal;
    }

    public void setGeneralJournal(GeneralJournal generalInfo){
        this.journal = generalInfo;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean getTemplate()
    {
        return isTemplate;
    }

    public void setTemplate(boolean template)
    {
        this.isTemplate = template;
    }


    public Tree<HealthEntry> getHealthConditions() {
        return healthConditions;
    }

    public void setHealthConditions(Tree<HealthEntry> healthConditions) {
        this.healthConditions = healthConditions;
    }

    public Tree<FunctionalEntry> getFunctionalAbilities() {
        return functionalAbilities;
    }

    public void setFunctionalAbilities(Tree<FunctionalEntry> functionalAbilities) {
        this.functionalAbilities = functionalAbilities;
    }
}


