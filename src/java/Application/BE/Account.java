package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

@SQLTable(name = "accounts")
public class Account {

    @SQLColumn(name = "accountId")
    private int id;

    @SQLColumn(name = "firstName")
    private String firstName;

    @SQLColumn(name = "surname")
    private String lastName;

    @SQLColumn(name = "email")
    private String email;

    @SQLColumn(name = "login")
    private String login;

    @SQLColumn(name = "password")
    private String password;

    @SQLColumn(name = "school")
    private School school;

    @SQLColumn(name = "auth")
    private int authorization;

    public Account(int id, String login, String password, String firstName, String lastName, String email, School school, int authorization) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.school = school;
        this.authorization = authorization;
    }

    @SQLGetter(name = "accountId")
    public int getId() {
        return id;
    }

    @SQLSetter(name = "accountId")
    public void setId(int id) {
        this.id = id;
    }


    @SQLGetter(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    @SQLSetter(name = "firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @SQLGetter(name = "surname")
    public String getLastName() {
        return lastName;
    }

    @SQLSetter(name = "surname")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName()
    {
        return this.firstName + " " + this.lastName;
    }

    @SQLGetter(name = "email")
    public String getEmail() {
        return email;
    }

    @SQLSetter(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }


    @SQLSetter(name = "password")
    public void setPassword(String password) { this.password = password;}

    @SQLGetter(name = "password")
    public String getPassword(){ return password;}


    @SQLSetter(name = "login")
    public void setLogin(String login) { this.login = login;}

    @SQLGetter(name = "login")
    public String getLogin(){return login;}


    @SQLSetter(name = "auth")
    public void setAuthorization(int authorization){ this.authorization = authorization;}

    @SQLGetter(name = "auth")
    public int getAuthorization(){ return authorization;}


    @SQLSetter(name = "school")
    public void setSchool(School school){this.school = school;}

    @SQLGetter(name = "school")
    public School getSchool(){return school;}


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
