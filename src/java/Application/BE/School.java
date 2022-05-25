package Application.BE;

public class School {

    private int id;
    private String schoolName;
    private String cityName;
    private int zipCode;

    public School(int id, String schoolName, int zipCode, String cityName)
    {
        this.id = id;
        this.schoolName = schoolName;
        this.zipCode = zipCode;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
