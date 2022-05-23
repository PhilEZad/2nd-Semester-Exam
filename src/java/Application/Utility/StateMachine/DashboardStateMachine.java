package Application.Utility.StateMachine;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public enum DashboardStateMachine {

    HOME("/fxml/dashboard/component/HomeTab.fxml"),

    CITIZEN("/fxml/dashboard/component/CitizenTab.fxml"),

    TEMPLATE("/Views/dashboard/component/TemplateTab.fxml"),

    STUDENT("/fxml/dashboard/component/StudentTab.fxml"),

    ADMIN("/fxml/dashboard/component/AdminTab.fxml"),

            ;private final Parent _this;

    private static Parent currentState = HOME._this;

    private static Runnable onChange = () -> {};

    DashboardStateMachine(String fxmlPath)
    {
        try
        {
            this._this = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource(fxmlPath)));
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
    }

    public static Node current()
    {
        return currentState;
    }

    public static void setTarget(Pane target)
    {
        onChange = () -> target.getChildren().setAll(currentState);
    }

    public void setTransition(Property<?> action)
    {
        action.addListener(observable -> this.makeCurrent());
    }

    private void makeCurrent()
    {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3), new KeyValue(currentState.opacityProperty(), 0)));
        timeline.play();

        currentState = _this;
        onChange.run();

        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3), new KeyValue(currentState.opacityProperty(), 1)));
        timeline.play();
    }
}
