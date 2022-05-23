package frontend.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class HeaderControl extends HBox
{
    @FXML private Button btnLogout;

    public HeaderControl()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Header.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try
        {
            fxmlLoader.load();
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }



    public final void setOnLogout(EventHandler<ActionEvent> value) { btnLogout.onActionProperty().set(value); }
    public final EventHandler<ActionEvent> getOnLogout() { return btnLogout.onActionProperty().get(); }

    private ObjectProperty<EventHandler<ActionEvent>> onLogout = new ObjectPropertyBase<>() {
        @Override
        protected void invalidated() {
            setEventHandler(ActionEvent.ACTION, get());
        }

        @Override
        public Object getBean() {
            return HeaderControl.this;
        }

        @Override
        public String getName() {
            return "onLogout";
        }
    };
}
