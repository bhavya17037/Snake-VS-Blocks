package sample;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Shield extends Token{
    public Shield(float x, float y, Snake snake) throws FileNotFoundException {
        super(new Circle(20, Color.BLACK), x, y, new ImageView(new Image(new FileInputStream("src/sample/shield.png"))), snake);
    }
}