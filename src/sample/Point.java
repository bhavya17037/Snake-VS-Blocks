/**
 * This initializes a new Point token
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

public class Point extends Token implements Serializable {
    private Image img;

    /**
     * This initializes a point token
     * @param x float
     * @param y float
     * @param snake Snake type
     * @throws FileNotFoundException
     */
    public Point(float x, float y, Snake snake) throws FileNotFoundException {
        super(new Circle(20, Color.BLACK), x, y, new ImageView(new Image(new FileInputStream("src/sample/health.png"))), snake);
    }
}
