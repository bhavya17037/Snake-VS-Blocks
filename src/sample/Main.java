
/**
 * Main.java - It is the class which contains methods for setting up the game
 * @author Bhavya Srivastava,Raghav Gupta
 * @version 1.0
 */

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

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Random;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;


public class Main extends Application implements Serializable {

    private static Stage stage;
    private static MainGame game;
    public static Boolean isGameMuted = false;
    public static Boolean showResumeScreen = false;
    public static int high;

    /**
     * Sets up stage
     * @param primaryStage A variable of type Stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        if (showResumeScreen) {
            Parent root = FXMLLoader.load(getClass().getResource("resumeScreen.fxml"));
            primaryStage.setTitle("SnakeVsBlock");
            primaryStage.setScene(new Scene(root, 500, 650));
            primaryStage.show();
        } else {
            game = Main.deserialize();
            if (game != null) {
                MainGame.highscore = game.highScore;
                for (int i = 0; i < 10; i++) {
                    MainGame.recentScores[i] = game.topScores[i];
                }
            }
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("SnakeVsBlock");
            primaryStage.setScene(new Scene(root, 500, 650));
            primaryStage.show();
        }

    }

    /**
     * Saves game state
     * @param m of type MainGame
     * @param show of type Boolean
     */
    public static void serialize(MainGame m, Boolean show) throws IOException {
        ObjectOutputStream out = null;
        ObjectOutputStream out1 = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("gameState.txt"));
            out1 = new ObjectOutputStream(new FileOutputStream("resumeState.txt"));
            out1.writeObject(show);
            out.writeObject(m);
        } finally {
            out.close();
        }
    }

    /**
     * Loads latest game state
     * @return A variable of type MainGame
     */
    public static MainGame deserialize() {
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new FileInputStream("gameState.txt"));
            return (MainGame) in.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Loads latest game state if player resumes game
     * @return A Boolean value
     */
    public static Boolean resumeScreenStatus() {
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new FileInputStream("resumeState.txt"));
            Boolean x = (Boolean) in.readObject();
            System.out.println(x);
            return x;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * starts a new game
     * @throws Exception
     */
    public static void playNewGame() throws Exception {
        game = new MainGame();
        showResumeScreen = false;
        game.start(stage);
    }

    /**
     * starts a game after deserialize
     * @throws Exception
     */
    public static void playGame() throws Exception {
        game = Main.deserialize();
        if (game == null) {
            game = new MainGame();
        }
        game.start(stage);
    }

    /**
     * getter method for retrieving stage variable
     * @return Current instance of type Stage
     */
    public static Stage getStage() {
        return stage;
    }


    public static void main(String[] args) {
        showResumeScreen = Main.resumeScreenStatus();
        System.out.println(showResumeScreen);
        launch(args);
    }
}
