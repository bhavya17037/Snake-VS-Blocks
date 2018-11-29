package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class settingsController {

    @FXML
    private Label mute_label;

    @FXML
    private ImageView mute_btn;

    public void goBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 500, 650);
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }

    @FXML
    public void initialize() {
        if (Main.isGameMuted == false) {
            mute_btn.setImage(new Image("sample/unmute_button.png"));
            mute_label.setText("Mute");
        } else {
            mute_btn.setImage(new Image("sample/mute_button.png"));
            mute_label.setText("Unmute");
        }
    }

    public void unmute() throws IOException {

        if (mute_label.getText().equals("Unmute")) {
            mute_btn.setImage(new Image("sample/unmute_button.png"));
            mute_label.setText("Mute");
            Main.isGameMuted = false;
        } else {
            mute_btn.setImage(new Image("sample/mute_button.png"));
            mute_label.setText("Unmute");
            Main.isGameMuted = true;
        }
    }

}