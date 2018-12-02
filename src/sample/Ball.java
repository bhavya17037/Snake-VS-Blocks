/**
 * This class initializes a snake body part
 * @author Bhavya Srivastava,Raghav Gupta
 * @version 1.0
 */

package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Ball extends GameObject implements Serializable {


    /**
     * This initializes a new circle GameObject
     * @param x Integer
     * @param y Integer,
     * @param snake Snake
     */
    public Ball(int x, int y, Snake snake){
        super(new Circle(20, Color.NAVY), x, y, null, snake);
    }
}
