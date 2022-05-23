import backend.entity.Citizen;
import backend.entity.Location;
import backend.entity.School;
import backend.entity.User;
import backend.logic.DataAccessFactory;
import backend.logic.factories.AccountFactory;
import backend.logic.factories.CitizenFactory;
import backend.logic.factories.LocationFactory;
import backend.logic.factories.SchoolFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application
{
    public static void main(String[] args)
    {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/fxml/LoginScreen.fxml")));

        primaryStage.setTitle("FS3 læringsværktøj");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



}
