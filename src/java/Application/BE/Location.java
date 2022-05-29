package Application.BE;

public class Location {
    private String CityName;
    private int zipCode;

    public Location(int zipCode, String cityName)
    {
        this.zipCode = zipCode;
        this.CityName = cityName;
    }
    public Location()
    {
    }

    public Location(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return CityName;
    }


    public int getZipCode() {
        return zipCode;
    }

}
