package Application.GUI.Models;

import Application.BE.Account;
import Application.BE.School;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class AccountModel
{
    private final IntegerProperty id;
    private final StringProperty password;
    private final StringProperty accountName;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private School school;
    private Account account;
    private Boolean isTeacher;
    private Boolean isAdmin;

    public AccountModel(Account account)
    {
        this.id = new SimpleIntegerProperty();
        this.password = new SimpleStringProperty();
        this.accountName =  new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.school = account.getSchool();
        this.account = account;
        this.isTeacher = account.getIsTeacher();
        this.isAdmin = account.getIsAdmin();


        id.set(account.getID());
        password.set(account.getPassword());
        accountName.set(account.getUsername());
        firstName.set(account.getFirstName());
        lastName.set(account.getLastName());
        email.set(account.getEmail());
    }

    public int getId()
    {
        return id.get();
    }

    public IntegerProperty idProperty()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id.set(id);
    }

    public String getPassword()
    {
        return password.get();
    }

    public StringProperty passwordProperty()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password.set(password);
    }

    public String getAccountName()
    {
        return accountName.get();
    }

    public StringProperty accountNameProperty()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName.set(accountName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty getFullNameProperty(){return new SimpleStringProperty(firstName.get() + " " + lastName.get());}

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public School getSchool(){return school;}

    public void setSchool(School school){this.school = school;}

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return firstName.get() + " " + lastName.get();
    }

    public Boolean getIsTeacher()
    {
        return isTeacher;
    }

    public void setIsTeacher(Boolean teacher)
    {
        isTeacher = teacher;
    }

    public Boolean getIsAdmin()
    {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin)
    {
        isAdmin = admin;
    }
}
