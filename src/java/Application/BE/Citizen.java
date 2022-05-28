package Application.BE;

import java.util.HashMap;

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

    private HashMap<Category, HealthEntry> healthCategoryEntries;

    private HashMap<Category, FunctionalEntry> funcCategoryEntries;


    public Citizen(int id, School school, String firstname, String lastname, int age, boolean template)
    {
        this(id);
        this.school = school;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.isTemplate = template;

        funcCategoryEntries = new HashMap<>();
        healthCategoryEntries = new HashMap<>();
    }

    public Citizen(int id)
    {
        this.id = id;
    }

    public Citizen() {
        this(-1);
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
    
    public void setFunctionalAbilities (HashMap<Category, FunctionalEntry> functionalAbilities) {
        funcCategoryEntries.clear();
        for (FunctionalEntry entry : functionalAbilities.values()) {
            funcCategoryEntries.put(entry.getCategory(), entry);
        };
    }

    public void setHealthConditions (HashMap<Category, HealthEntry> healthConditions) {
        healthCategoryEntries.clear();
        for (HealthEntry entry : healthConditions.values()) {
            healthCategoryEntries.put(entry.getCategory(), entry);
        };
    }

    public HashMap<Category, FunctionalEntry> getFunctionalAbilities() {
        return funcCategoryEntries;
    }

    public HashMap<Category, HealthEntry> getHealthConditions() {
        return healthCategoryEntries;
    }

    public void addFunctionalAbility (FunctionalEntry entry)
    {
        funcCategoryEntries.put(entry.getCategory(), entry);
    }

    public void addHealthConditions (HealthEntry entry)
    {
        healthCategoryEntries.put(entry.getCategory(), entry);
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


}


