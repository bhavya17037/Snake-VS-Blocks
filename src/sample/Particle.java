/**
 * This class initializes a particle
 * @author Bhavya Srivastava,Raghav Gupta
 * @version 1.0
 */

package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Particle extends GameObject implements Serializable {

    /**
     * This initializes a new particle
     * @param x float
     * @param y float
     * @param snake Snake type
     */
    public Particle(double x, double y, Snake snake){
        super(new Circle(6, Color.WHITE), (float)x, (float)y, null, snake);
    }
}
