package Application.GUI.Models;

import Application.BE.School;
import Application.BLL.AdminDataManager;
import Application.DAL.SchoolDAO;
import Application.DAL.TemplatePatternDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SchoolModel {
    StringProperty name;
    IntegerProperty zipCode;
    StringProperty city;

    ObservableList<School> schools;

    private AdminDataManager DAO = new AdminDataManager();

    public SchoolModel(School school)
    {
        name.setValue(school.getSchoolName());
        zipCode.setValue(school.getZipCode());
        city.setValue(school.getCityName());
    }

    public SchoolModel() {
        schools = FXCollections.observableArrayList();
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

    public void create(String random, int i) {
        var school = DAO.createSchool(random, i);
        schools.add(school);
    }
}
