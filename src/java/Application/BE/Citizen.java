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

    private GeneralJournal generalJournal;

    private HashMap<Category, ContentEntry> healthCategoryEntries;

    private HashMap<Category, ContentEntry> funcCategoryEntries;

    public Citizen(int id)
    {

        this.id = id;
    }

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


    @SQLGetter(name = "cZipCode")
    public int getZipCode() {
        return zipCode;
    }

    @SQLSetter(name = "cZipCode")
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @SQLGetter(name = "cAge")
    public int getAge() {
        return age;
    }

    @SQLGetter(name = "cFirstName")
    public String getFirstname() {
        return firstname;
    }

    @SQLSetter(name = "cFirstName")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @SQLGetter(name = "citizenId")
    public int getId() {
        return id;
    }

    @SQLSetter(name = "citizenId")
    public void setId(int id) {
        this.id = id;
    }

    @SQLGetter(name = "cSurname")
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

    public void setContent(List<ContentEntry> content)
    {
        // populate lists (healthCategoryEntries, funcCategoryEntries)
    }
    
    public void setFunctionalAbilities (List<ContentEntry> functionalAbilities) {
        funcCategoryEntries.clear();
        for (ContentEntry entry : functionalAbilities) {
            funcCategoryEntries.put(entry.getCategory(), entry);
        };
    }

    public void setHealthConditions (List < ContentEntry > healthConditions) {
        healthCategoryEntries.clear();
        for (ContentEntry entry : healthConditions) {
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
        return generalJournal;
    }

    public void setGeneralJournal(GeneralJournal generalInfo){
        this.generalJournal = generalInfo;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


