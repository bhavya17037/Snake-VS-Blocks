package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

public class leaderboardController {

    @FXML
    private ListView<String> listOfScores;

    public void goBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 500, 650);
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }

}
