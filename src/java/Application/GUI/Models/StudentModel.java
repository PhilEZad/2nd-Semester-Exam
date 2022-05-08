package Application.GUI.Models;

import Application.BE.Account;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentModel {

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private Account student;

    public StudentModel(Account student) {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.email = new SimpleStringProperty();

        firstName.set(student.getFirstName());
        lastName.set(student.getLastName());
        email.set(student.getEmail());
      
        this.student = student;
    }


    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName.set(newFirstName);

    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
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
}
