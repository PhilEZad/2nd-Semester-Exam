package backend.entity;

public class School
{
    private int id;
    private String name;
    private Location location;

    public School(int id, String name, Location location)
    {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public School(School obj)
    {
        this.id = obj.getId();
        this.location = new Location(obj.getLocation().getZipcode(), obj.getLocation().getName());
        this.name = obj.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
