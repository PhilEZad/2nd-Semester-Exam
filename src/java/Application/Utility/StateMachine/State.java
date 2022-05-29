package Application.Utility.StateMachine;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Rasmus Sandbæk
 * @author Mads Mandahl-Barth
 * */
public class State implements IState{

    private final BorderPane viewPane;
    private final ToggleButton menuButton;
    private final String fxml;

    public State(BorderPane viewPane, String fxml, ToggleButton menuButton)
    {
        this.viewPane = viewPane;
        this.menuButton = menuButton;
        this.fxml = fxml;
    }

    @Override
    public Object getState() {
        return this;
    }

    @Override
    public void disable() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3),
                new KeyValue(viewPane.centerProperty().get().opacityProperty(), 0)));
        timeline.play();
        menuButton.setDisable(false);
    }

    @Override
    public void enable() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewPane.setCenter(root);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3),
                new KeyValue(viewPane.centerProperty().get().opacityProperty(), 1)));
        timeline.play();
    }
}