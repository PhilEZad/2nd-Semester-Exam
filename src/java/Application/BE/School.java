package Application.BE;

public class School {

    private int schoolID;
    private String schoolName;
    private int zipCode;
    private String cityName;

    public School(int schoolID, String schoolName, int zipCode, String cityName)
    {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.zipCode = zipCode;
        this.cityName = cityName;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
