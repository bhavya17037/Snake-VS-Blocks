/**
 * This class initializes a Token
 * @author Bhavya Srivastava,Raghav Gupta
 * @version 1.0
 */

package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Token extends GameObject implements Serializable {

    /**
     * This initializes a new Token object
     * @param x float,
     * @param y float
     * @param snake Snake type
     * @param shape Shape type
     * @param img ImageView type
     */
    public Token(Node shape, float x, float y, ImageView img, Snake snake){
        super(shape, x, y, img, snake);
        setVelocity(new Point2D(0, 4));
    }
}
