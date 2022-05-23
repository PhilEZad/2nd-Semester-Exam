package frontend.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CitizenInfoModel
{
    private final StringProperty firstName  = new SimpleStringProperty();
    private final StringProperty lastName   = new SimpleStringProperty();
    private final IntegerProperty age       = new SimpleIntegerProperty();


}
