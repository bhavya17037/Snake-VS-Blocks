package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class Token extends GameObject {
    public Token(Node shape, float x, float y, ImageView img, Snake snake){
        super(shape, x, y, img, snake);
        setVelocity(new Point2D(0, 4));
    }
}
