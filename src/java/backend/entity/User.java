package backend.entity;

import util.AccountType;

public class User
{
    private Integer id = null;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private AccountType authorization;

    public User(String username, String password, String firstName, String lastName, String email, AccountType authorization) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authorization = authorization;
    }

    public User(User partial, int UserID) {
        this(partial.username, partial.password, partial.firstName, partial.lastName, partial.email, partial.authorization);
        this.id = UserID;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public AccountType getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AccountType authorization) {
        this.authorization = authorization;
    }

}
