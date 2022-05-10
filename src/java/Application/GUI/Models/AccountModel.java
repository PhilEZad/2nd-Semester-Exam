package Application.GUI.Models;

import Application.BE.Account;
import Application.BE.School;
import Application.BLL.AdminDataManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AccountModel
{

    private AdminDataManager adminDataManager = new AdminDataManager();

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private StringProperty accountClass;

    private Account account;

    ObservableList<Account> accounts;

    public AccountModel() {
        accounts = FXCollections.observableArrayList();
    }

    public AccountModel(Account account)
    {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.accountClass = new SimpleStringProperty();

        firstName.set(account.getFirstName());
        lastName.set(account.getLastName());
        email.set(account.getEmail());
        accountClass.set(account.getClass().getName());
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

    public StringProperty getFullNameProperty() {
        StringProperty fullName = new SimpleStringProperty();
        fullName.set(getFirstName() + " " + getLastName());
        return fullName;
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

    public ObservableList<Account> getAccountList()
    {
        return accounts;
    }

    public void createAccount(String username, String password, String firstName, String surname, String email, School school, int i)
    {
        var account = adminDataManager.createAccount(username, password, firstName, surname, email, school, i);
        accounts.add(account);
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

    @Override
    public String toString() {
        return firstName.get();
    }
}
