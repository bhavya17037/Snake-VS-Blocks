package sample;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends GameObject{
    public Block(float x, float y, Snake snake){
        super(new Rectangle(80, 80, Color.TOMATO), x, y, null, snake);
        setVelocity(new Point2D(0, 4));
    }
}
