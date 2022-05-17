package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

import java.util.HashMap;

@SQLTable(name = "citizen")
public class Citizen
{
    @SQLColumn(name = "CID")
    private Integer id = null;

    @SQLColumn(name = "firstName")
    private String firstname;

    @SQLColumn(name = "lastname")
    private String lastname;

    @SQLColumn(name = "age")
    private Integer age = null;

    @SQLColumn(name = "FK_SchoolOwner")
    private School owner;

    // TODO: 17-05-2022 integrate new general info
    @SQLColumn(name = "FK_Info")
    private GeneralJournal info;

    public Citizen() {}

    public Citizen(int id, String firstname, String lastname, int age, int schoolID) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public School getOwner() {
        return owner;
    }

    public void setOwner(School owner) {
        this.owner = owner;
    }
}
