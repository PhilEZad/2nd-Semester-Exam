package frontend.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LocationModel
{
    private final IntegerProperty zipcode   = new SimpleIntegerProperty();
    private final StringProperty city       = new SimpleStringProperty();

    public LocationModel(int zip, String city)
    {
        this.zipcode.setValue(zip);
        this.city.setValue(city);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public int getZipcode() {
        return zipcode.get();
    }

    public IntegerProperty zipcodeProperty() {
        return zipcode;
    }
}
