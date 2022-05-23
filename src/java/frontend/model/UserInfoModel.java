package frontend.model;

import util.AccountType;
import javafx.beans.property.*;

public class UserInfoModel
{
    private final IntegerProperty id                = new SimpleIntegerProperty();
    private final StringProperty username           = new SimpleStringProperty();
    private final StringProperty hashed_password    = new SimpleStringProperty();
    private final StringProperty firstName          = new SimpleStringProperty();
    private final StringProperty lastName           = new SimpleStringProperty();
    private final StringProperty email              = new SimpleStringProperty();
    private final ObjectProperty<SchoolModel> school = new SimpleObjectProperty<>();
    private final ObjectProperty<AccountType> authorization = new SimpleObjectProperty<>();

    public UserInfoModel()
    {
        // null initialize

        this.id.setValue(null);
        this.username.setValue(null);
        this.hashed_password.setValue(null);
        this.firstName.setValue(null);
        this.lastName.setValue(null);
        this.email.setValue(null);
        this.school.setValue(null);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getHashed_password() {
        return hashed_password.get();
    }

    public StringProperty hashed_passwordProperty() {
        return hashed_password;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public Object getSchool() {
        return school.get();
    }

    public ObjectProperty<SchoolModel> schoolProperty() {
        return school;
    }

    public AccountType getAuthorization() {
        return authorization.get();
    }

    public ObjectProperty<AccountType> authorizationProperty() {
        return authorization;
    }
}
