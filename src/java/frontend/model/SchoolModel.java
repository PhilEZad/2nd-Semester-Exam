package frontend.model;

import javafx.beans.property.*;

public class SchoolModel
{
    private final IntegerProperty id = new SimpleIntegerProperty();

    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<LocationModel> location = new SimpleObjectProperty<>();

    public SchoolModel(int id, String name, int zip, String city)
    {
        this.id.setValue(id);
        this.name.setValue(name);
        this.location.setValue(new LocationModel(zip, city));
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public Object getLocation() {
        return location.get();
    }

    public ObjectProperty<LocationModel> locationProperty() {
        return location;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }
}
