/**
 * This initializes a new Magnet object
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

public class Magnet extends Token implements Serializable {

    /**
     * This initializes a magnet power up
     * @param x float
     * @param y float
     * @param snake Snake type
     * @throws FileNotFoundException
     */
    public Magnet(float x, float y, Snake snake) throws FileNotFoundException {
        super(new Circle(20, Color.BLACK), x, y, new ImageView(new Image(new FileInputStream("src/sample/magnet.png"))), snake);
    }
}
