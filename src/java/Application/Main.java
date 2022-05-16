package Application;

import Application.BE.School;
import Application.DAL.TemplateMethod.DefaultDAO;
import Application.DAL.TemplateMethod.SchoolDAO2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Parent root = FXMLLoader.load(this.getClass().getResource("/views/TeacherView.fxml"));

        primaryStage.setTitle("main");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
