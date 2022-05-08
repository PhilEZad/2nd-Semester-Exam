package Application.BE;

public class Account {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private School school;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPassword(String password) { this.password = password;}

    public String getPassword(){ return password;}

    public void setLogin(String login) { this.login = login;}

    public String getLogin(){return login;}

    public void setAuthorization(int authorization){ this.authorization = authorization;}

    public int getAuthorization(){ return authorization;}

    public void setSchool(School school){this.school = school;}

    public School getSchool(){return school;}

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
