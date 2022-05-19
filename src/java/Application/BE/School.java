package Application.BE;

public class School {

    private int schoolID;
    private String schoolName;
    private Location location;

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
