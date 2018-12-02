/**
 * This class initializes a block obstacle
 * @author Bhavya Srivastava,Raghav Gupta
 * @version 1.0
 */

package sample;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Block extends GameObject implements Serializable {

    /**
     * This initializes a new Block obstacle
     * @param x float
     * @param y float
     * @param snake Snake
     */
    public Block(float x, float y, Snake snake){
        super(new Rectangle(80, 80, Color.TOMATO), x, y, null, snake);
        setVelocity(new Point2D(0, 4));
    }
}
