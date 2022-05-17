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

    private StringProperty userName;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;

    Account account;

    ObservableList<Account> accountsList;

    public AccountModel() {
        accountsList = FXCollections.observableArrayList();
    }

    public AccountModel(Account account)
    {
        this.account = account;

        this.userName = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.email = new SimpleStringProperty();

        userName.set(account.getLogin());
        firstName.set(account.getFirstName());
        lastName.set(account.getLastName());
        email.set(account.getEmail());
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

    public StringProperty getFullNameProperty(){return new SimpleStringProperty(firstName + " " + lastName);}

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
        return accountsList;
    }

    public void createAccount(String username, String password, String firstName, String surname, String email, School school, int i)
    {
        var account = adminDataManager.createAccount(username, password, firstName, surname, email, school, i);
        accountsList.add(account);
    }

    public void update(Account account)
    {
        adminDataManager.editAccount(account);
    }
}
