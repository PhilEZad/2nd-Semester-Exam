package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

@SQLTable(name = "Zipcode")
public class City
{
    @SQLColumn(name = "city")
    private String CityName;

    @SQLColumn(name = "Zip")
    private int zipCode;

    public City(int zipCode, String cityName)
    {
        this.zipCode = zipCode;
        this.CityName = cityName;
    }

    public City(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
