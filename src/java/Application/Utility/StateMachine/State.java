package Application.Utility.StateMachine;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class State implements IState{

    private final Pane viewPane;
    private final ToggleButton menuButton;

    public State(Pane viewPane, ToggleButton menuButton)
    {
        this.viewPane = viewPane;
        this.menuButton = menuButton;
    }

    @Override
    public Object getState() {
        return viewPane;
    }

    @Override
    public void disable() {
        Runnable runnable = () -> {
            viewPane.setVisible(false);
            menuButton.setDisable(false);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3), new KeyValue(viewPane.opacityProperty(), 0)));
            timeline.play();
        };
        runnable.run();
    }

    @Override
    public void enable() {
        Runnable runnable = () -> {
            viewPane.toFront();
            viewPane.setVisible(true);
            menuButton.setDisable(true);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3), new KeyValue(viewPane.opacityProperty(), 1)));
            timeline.play();
        };
        runnable.run();
    }
}