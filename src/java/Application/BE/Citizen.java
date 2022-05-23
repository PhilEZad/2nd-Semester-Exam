package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;

import java.util.HashMap;
import java.util.List;

public class Citizen implements Cloneable
{
    private int id;
    private GeneralJournal journal;
    private School school;
    private String firstname;
    private String lastname;
    private int age;
    private int zipCode;

    private HashMap<Category, ContentEntry> healthCategoryEntries;

    private HashMap<Category, ContentEntry> funcCategoryEntries;

    public Citizen(int id, GeneralJournal journal, School school, String firstname, String lastname, int age)
    {
        this.id = id;
        this.journal = journal;
        this.school = school;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;

        funcCategoryEntries = new HashMap<>();
        healthCategoryEntries = new HashMap<>();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setContent(HashMap<Category, ContentEntry> content)
    {
        // populate lists (healthCategoryEntries, funcCategoryEntries)
    }
    
    public void setFunctionalAbilities (HashMap<Category, ContentEntry> functionalAbilities) {
        funcCategoryEntries.clear();
        for (ContentEntry entry : functionalAbilities.values()) {
            funcCategoryEntries.put(entry.getCategory(), entry);
        };
    }

    public void setHealthConditions (HashMap<Category, ContentEntry> healthConditions) {
        healthCategoryEntries.clear();
        for (ContentEntry entry : healthConditions.values()) {
            healthCategoryEntries.put(entry.getCategory(), entry);
        };
    }

    public HashMap<Category, ContentEntry> getFunctionalAbilities() {
        return funcCategoryEntries;
    }

    public HashMap<Category, ContentEntry> getHealthConditions() {
        return healthCategoryEntries;
    }

    public void addFunctionalAbility (ContentEntry entry)
    {
        funcCategoryEntries.put(entry.getCategory(), entry);
    }

    public void addHealthConditions (ContentEntry entry)
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
}


