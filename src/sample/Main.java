package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Insurance Management");
        primaryStage.setScene(new Scene(root, 1600, 800));
        primaryStage.show();
        primaryStage.setResizable(true);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
