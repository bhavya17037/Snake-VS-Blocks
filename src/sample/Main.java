package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

    private Button play;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Registration Form JavaFX Application");

        // Create the registration form grid pane
        GridPane gridPane = MainMenuPane();
        addMenuComponents(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void addMenuComponents(GridPane gridPane){
        Label headerLabel = new Label("SNAKE VS BLOCKS");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Label HighscoreLabel = new Label("HIGHSCORE(ALL TIME): 902184");
        HighscoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gridPane.add(HighscoreLabel, 0,1,2,1);
        GridPane.setHalignment(HighscoreLabel, HPos.CENTER);
        GridPane.setMargin(HighscoreLabel, new Insets(20, 0,20,0));

        play = new Button("PLAY");
        play.setPrefHeight(60);
        play.setDefaultButton(true);
        play.setPrefWidth(150);
        gridPane.add(play, 0, 2, 2, 1);
        GridPane.setHalignment(play, HPos.CENTER);
        GridPane.setMargin(play, new Insets(20, 0,20,0));

        Button settings = new Button("SETTINGS");
        settings.setPrefHeight(60);
        settings.setDefaultButton(true);
        settings.setPrefWidth(150);
        gridPane.add(settings, 0, 3, 2, 1);
        GridPane.setHalignment(settings, HPos.CENTER);
        GridPane.setMargin(settings, new Insets(20, 0,20,0));

        Button leaderboard = new Button("LEADERBOARD");
        leaderboard.setPrefHeight(60);
        leaderboard.setDefaultButton(true);
        leaderboard.setPrefWidth(150);
        gridPane.add(leaderboard, 0, 4, 2, 1);
        GridPane.setHalignment(leaderboard, HPos.CENTER);
        GridPane.setMargin(leaderboard, new Insets(20, 0,20,0));

        Button exit = new Button("EXIT");
        exit.setPrefHeight(60);
        exit.setDefaultButton(true);
        exit.setPrefWidth(150);
        gridPane.add(exit, 0, 5, 2, 1);
        GridPane.setHalignment(exit, HPos.CENTER);
        GridPane.setMargin(exit, new Insets(20, 0,20,0));

    }

    private GridPane MainMenuPane(){
        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
