package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Particle extends GameObject implements Serializable {
    public Particle(double x, double y, Snake snake){
        super(new Circle(6, Color.WHITE), (float)x, (float)y, null, snake);
    }
}
