package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends GameObject{
    public Ball(int x, int y, Snake snake){
        super(new Circle(20, Color.NAVY), x, y, null, snake);
    }
}
