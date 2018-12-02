package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class gameOverController {

    @FXML
    public Label score;

    public void initialize() {
        score.setText(Integer.toString(MainGame.score));
        MainGame.score = 0;
    }

    public void mainMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 500, 650);
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }

    public void newGame() throws Exception {
        Main.playNewGame();
    }

    public void exit() {
        Main.getStage().close();
    }



}
