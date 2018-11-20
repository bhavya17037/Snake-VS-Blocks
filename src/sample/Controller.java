package sample;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller {

    private List<String> scores;
    private ObservableList<String> items;

   /* @FXML
    private ImageView play_btn;

    @FXML
    private ImageView openLeaderboad;*/

    @FXML
    private ListView<String> listOfScores;

    public void playGame() throws Exception {
        Main.playGame();
    }

    @FXML
    public void leaderboardScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("leaderboard.fxml"));
        Scene scene = new Scene(root, 500, 650);
        Main.getStage().setScene(scene);
        /*scores = Main.getRecentScores();
        items = FXCollections.observableArrayList(scores);
        listOfScores.setItems(items);*/
        Main.getStage().show();
    }

    public void settingsScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        Scene scene = new Scene(root, 500, 650);
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }

    public void closeScreen() {
        Main.getStage().close();
    }

}
