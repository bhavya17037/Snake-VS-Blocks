package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Ball extends GameObject implements Serializable {

    public Ball(int x, int y, Snake snake){
        super(new Circle(20, Color.NAVY), x, y, null, snake);
    }
}
