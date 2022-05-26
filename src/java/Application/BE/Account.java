package Application.BE;

public class Account implements IUniqueIdentifier<Integer>
{
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private School school;

    private Boolean isTeacher;
    private Boolean isAdmin;

    private Boolean isStudent;

    public Account()
    {
        // TODO: 26-05-2022 default init
    }

    public Account(int id)
    {
        this();

        this.setID(id);
    }


    public Account(int id, String login, String password, String firstName, String lastName, String email, School school, Boolean isTeacher, Boolean isAdmin) {
        this(id);

        this.username = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.school = school;
        this.isTeacher = isTeacher;
        this.isAdmin = isAdmin;
        this.isStudent = !isAdmin && !isTeacher;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id == null ? -1 : id;
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

    public void setUsername(String username) { this.username = username;}

    public String getUsername(){return username;}

    public void setSchool(School school){this.school = school;}

    public School getSchool(){return school;}

    public Boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Boolean teacher) {
        isTeacher = teacher;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public int getAuthLevel()
    {
        return this.getIsAdmin() ? 0x10 : this.getIsTeacher() ? 0x01 : 0x00;
    }

    public Boolean getStudent() {
        return isStudent;
    }

    public void setStudent(Boolean student) {
        isStudent = student;
    }
}
