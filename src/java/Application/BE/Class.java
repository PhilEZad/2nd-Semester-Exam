package Application.BE;

public class Class {

    private int id;
    private String className;

    public Class(int id, String className)
    {
        this.className = className;
        this.id = id;

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return className;
    }

    public void setName(String name)
    {
        this.className = name;
    }
}
