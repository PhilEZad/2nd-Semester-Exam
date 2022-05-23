package frontend.view;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class NavigationItemControl extends ToggleButton
{
    @FXML
    private ImageView imgIcon;

    @FXML
    private Text lblTitle;

    public NavigationItemControl()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/NavigationItem.fxml"));

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

    public String getTitle()
    {
        return titleProperty().get();
    }

    public void setTitle(String title)
    {
        titleProperty().setValue(title);
    }

    public String getIcon()
    {
        return iconProperty().get().getUrl();
    }

    public void setIcon(String url)
    {
        this.iconProperty().setValue(new Image(url));
    }

    public StringProperty titleProperty() {
        return lblTitle.textProperty();
    }

    public ObjectProperty<Image> iconProperty()
    {
        return imgIcon.imageProperty();
    }


}
