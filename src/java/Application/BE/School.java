package Application.BE;

public class School implements IUniqueIdentifier<Integer>
{

    private int id;
    private String schoolName;

    private int zipCode;
    private String cityName;

    public School(int id, String schoolName, int zipCode, String cityName)
    {
        this.id = id;
        this.schoolName = schoolName;
        this.zipCode = zipCode;
        this.cityName = cityName;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id == null ? -1 : id;
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
