package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

public class leaderboardController {

    @FXML
    private ListView<String> listOfScores;

    @FXML
    private Label one;

    @FXML
    private Label two;

    @FXML
    private Label three;

    @FXML
    private Label four;

    @FXML
    private Label five;

    public void goBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 500, 650);
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }

    public void initialize() {
        int[] x = MainGame.recentScores;

        one.setText("1) " + Integer.toString(x[0]));
        two.setText("2) " + Integer.toString(x[1]));
        three.setText("3) " + Integer.toString(x[2]));
        four.setText("4) " + Integer.toString(x[3]));
        five.setText("5) " + Integer.toString(x[4]));

    }

}
