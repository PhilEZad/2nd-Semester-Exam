package Application.BE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CitizenBaseData
{
    private String name;
    private String surname;
    private int age;
    private LocalDate birthDate;
    private String helpStatus;
    private String civilianStatus;
    private String address;
    private List<ContactInfo> contactInfo;

    public CitizenBaseData()
    {
        this.name = "";
        this.surname = "";
        this.age = 0;
        this.birthDate = LocalDate.now();
        this.helpStatus = "";
        this.civilianStatus = "";
        this.address = "";
        this.contactInfo = new ArrayList<>();
    }

    public CitizenBaseData(String name, String surname, int age, LocalDate birthDate, String helpStatus, String civilianStatus, String address, List<ContactInfo> contactInfo) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.birthDate = birthDate;
        this.helpStatus = helpStatus;
        this.civilianStatus = civilianStatus;
        this.address = address;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getHelpStatus() {
        return helpStatus;
    }

    public void setHelpStatus(String helpStatus) {
        this.helpStatus = helpStatus;
    }

    public String getCivilianStatus() {
        return civilianStatus;
    }

    public void setCivilianStatus(String civilianStatus) {
        this.civilianStatus = civilianStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }
}
