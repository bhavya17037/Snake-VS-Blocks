package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.Random;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;

public class Main extends Application {

    private static Stage stage;
    private static MainGame game;
    public static Boolean isGameMuted = false;
    public static Boolean showResumeScreen = true;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        if (showResumeScreen) {
            Parent root = FXMLLoader.load(getClass().getResource("resumeScreen.fxml"));
            primaryStage.setTitle("SnakeVsBlock");
            primaryStage.setScene(new Scene(root, 500, 650));
            primaryStage.show();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("SnakeVsBlock");
            primaryStage.setScene(new Scene(root, 500, 650));
            primaryStage.show();
        }

    }

    public static void playGame() throws Exception {
        game = new MainGame();
        game.start(stage);
    }

    public static Stage getStage() {
        return stage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
