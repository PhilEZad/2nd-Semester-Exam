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
    private StringProperty accountClass;

    private Account account;

    ObservableList<Account> accountsList;

    public AccountModel() {
        accountsList = FXCollections.observableArrayList();
    }

    public AccountModel(Account account)
    {
        this.id = new SimpleIntegerProperty();
        this.password = new SimpleStringProperty();
        this.accountName =  new SimpleStringProperty();
        this.account = account;

        this.userName = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.authorization = new SimpleIntegerProperty();
        this.school = account.getSchool();
        this.account = account;
        this.accountClass = new SimpleStringProperty();

        id.set(account.getId());
        password.set(account.getPassword());
        accountName.set(account.getUsername());
        userName.set(account.getLogin());
        firstName.set(account.getFirstName());
        lastName.set(account.getLastName());
        email.set(account.getEmail());
        authorization.set(account.getAuthorization());
        accountClass.set(account.getSchool().getSchoolName());
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

    public Account getAccount() { return account;}

    public String getUserName() { return userName.getName();}

    public void setUserName(String userName) { this.userName.set(userName);}

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

    public StringProperty getFullNameProperty() {
        return new SimpleStringProperty(firstName.get() + " " + lastName.get());
    }

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
        return accountsList;
    }

    public void updateAccount(Account account)
    {
        adminDataManager.updateAccount(account);
    }

    public Account getAccount2() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return firstName.get() + " " + lastName.get();
        var account = adminDataManager.createAccount(username, password, firstName, surname, email, school, i);
        accountsList.add(account);
    }

    public void update(Account account)
    {
        adminDataManager.updateAccount(account);
    }

    public String getAccountClass() {
        return accountClass.get();
    }

    public StringProperty accountClassProperty() {
        return accountClass;
    }

    public void setAccountClass(String accountClass) {
        this.accountClass.set(accountClass);
    }

    public StringProperty getClassNameProperty()
    {
        StringProperty className = new SimpleStringProperty();
        className.set(accountClass.get());
        return className;
    }
}
