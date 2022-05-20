package Application.BE;
import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

public class School {

    private int schoolID;
    private String schoolName;
    private Location location;

    public School()
    {
        this.schoolID = -1;
        this.schoolName = "";
        this.location = new Location();
    }

    public School(int id)
    {
        this();

        this.schoolID = id;
    }

    public School(int schoolID, String schoolName, Location location)
    {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.location = location;
    }



    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
