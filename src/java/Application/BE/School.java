package Application.BE;
import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

@SQLTable(name = "schools")
public class School {

    @SQLColumn(name = "schoolId")
    private int schoolID;

    @SQLColumn(name = "schoolName")
    private String schoolName;

    @SQLColumn(name = "schoolZipCode")
    private int zipCode;

    @SQLColumn(name = "cityName")
    private String cityName;

    public School(int schoolID, String schoolName, int zipCode, String cityName)
    {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.zipCode = zipCode;
        this.cityName = cityName;
    }

    @SQLGetter(name = "schoolId")
    public int getSchoolID() {
        return schoolID;
    }

    @SQLSetter(name = "schoolId")
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    @SQLGetter(name = "schoolName")
    public String getSchoolName() {
        return schoolName;
    }

    @SQLSetter(name = "schoolName")
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @SQLGetter(name = "schoolZipCode")
    public int getZipCode() {
        return zipCode;
    }

    @SQLSetter(name = "schoolZipCode")
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @SQLGetter(name = "cityName")
    public String getCityName() {
        return cityName;
    }

    @SQLSetter(name = "cityName")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
