package Application.GUI.Models;

import Application.BE.Account;
import Application.BE.School;
import Application.BLL.AdminDataManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AccountModel
{

    private AdminDataManager adminDataManager = new AdminDataManager();

    private IntegerProperty id;
    private StringProperty password;
    private StringProperty accountName;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private IntegerProperty authorization;
    private School school;
    private Account account;

    ObservableList<Account> accounts;

    public AccountModel()
    {

    }

    public AccountModel(Account account)
    {
        this.id = new SimpleIntegerProperty();
        this.password = new SimpleStringProperty();
        this.accountName =  new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.authorization = new SimpleIntegerProperty();
        this.school = account.getSchool();
        this.account = account;

        id.set(account.getId());
        password.set(account.getPassword());
        accountName.set(account.getUsername());
        firstName.set(account.getFirstName());
        lastName.set(account.getLastName());
        email.set(account.getEmail());
        authorization.set(account.getAuthorization());
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

    public int getAuthorization()
    {
        return authorization.get();
    }

    public IntegerProperty authorizationProperty()
    {
        return authorization;
    }

    public void setAuthorization(int authorization)
    {
        this.authorization.set(authorization);
    }

    public School getSchool(){return school;}

    public void setSchool(School school){this.school = school;}

    public ObservableList<Account> getAccountList()
    {
        return accounts;
    }

    public void updateAccount(Account account)
    {
        adminDataManager.updateAccount(account);
    }

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
}
