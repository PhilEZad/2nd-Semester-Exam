package backend.entity;

public class Location
{
    private int zipcode;
    private String name;

    public Location(int zip, String city)
    {
        this.zipcode = zip;
        this.name = city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
