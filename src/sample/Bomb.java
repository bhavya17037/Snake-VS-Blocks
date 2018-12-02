/**
 * This initializes a new Bomb object
 * @author Bhavya Srivastava,Raghav Gupta
 * @version 1.0
 */

package sample;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class Bomb extends Token implements Serializable {

    /**
     * This initializes a bomb power up
     * @param x float
     * @param y float
     * @param snake Snake type
     * @throws FileNotFoundException
     */
    public Bomb(float x, float y, Snake snake) throws FileNotFoundException {
        super(new Circle(20, Color.BLACK), x, y, new ImageView(new Image(new FileInputStream("src/sample/bomb.png"))), snake);
    }
}
