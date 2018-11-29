package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Particle extends GameObject{
    public Particle(double x, double y, Snake snake){
        super(new Circle(6, Color.WHITE), (float)x, (float)y, null, snake);
    }
}
