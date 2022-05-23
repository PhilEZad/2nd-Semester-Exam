package backend.entity;

import java.util.Map;

public class Citizen implements Cloneable
{
    private Integer id = null;
    private String firstname;
    private String lastname;
    private Integer age;
    private School owner;


    // General information
    private Map<Category, Entry> overview;

    // functional abilities
    private Map<Category, Entry> abilities;

    // health information
    private Map<Category, Entry> health;

    public Citizen(String firstname, String lastname, int age, School owner)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.owner = owner;
    }

    public Citizen(Citizen partial, int id)
    {
        this(id, partial.firstname, partial.lastname, partial.age, partial.owner);
    }

    public Citizen(int cid, String firstName, String lastName, int age, School school)
    {
        this(firstName, lastName, age, school);
        this.id = cid;
    }

    public Citizen(int id, User journal, School school, String firstname, String lastname, int age)
    {
        this(id, firstname, lastname, age, school);
    }

    @Override
    public Citizen clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Citizen) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Category, Entry> getOverview() {
        return overview;
    }

    public void setOverview(Map<Category, Entry> overview) {
        this.overview = overview;
    }

    public Map<Category, Entry> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<Category, Entry> abilities) {
        this.abilities = abilities;
    }

    public Map<Category, Entry> getHealth() {
        return health;
    }

    public void setHealth(Map<Category, Entry> health) {
        this.health = health;
    }

    public School getOwner() {
        return owner;
    }

    public void setOwner(School owner) {
        this.owner = owner;
    }

    public User getJournal() {
        return null;
    }
}
