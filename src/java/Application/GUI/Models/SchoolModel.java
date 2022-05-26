package Application.GUI.Models;

import Application.BE.School;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SchoolModel {

    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty zipCode;
    private final StringProperty city;
    private School schoolBE;

    public SchoolModel(School school)
    {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.zipCode = new SimpleIntegerProperty();
        this.city = new SimpleStringProperty();

        this.schoolBE = school;

        id.set(school.getID());
        name.setValue(school.getSchoolName());
        zipCode.setValue(school.getZipCode());
        city.setValue(school.getCityName());

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

    public StringProperty getName(){
        return name;
    }

    public IntegerProperty getZipCode(){
        return zipCode;
    }

    public StringProperty getCity(){
        return city;
    }

    public School getSchool () { return schoolBE;}

    public void setSchoolBE (School school) {this.schoolBE = school;}
}
