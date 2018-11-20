package sample;
import javafx.scene.*;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameObject {
    private Node view;
    private Point2D velocity = new Point2D(0, 0);
    private boolean alive = true;
    private float x, y;
    private ImageView img;
    private Label tolerance;

    private static int colorIndex = 0;
    private Color[] colorList = {Color.RED, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.VIOLET};

    private Color getColor() {

        colorIndex = (colorIndex + 1) % 6;
        return colorList[colorIndex];
    }

    public GameObject(Node view, float x, float y, ImageView img, Snake snake){
         this.view = view;

         try{
             Rectangle rect = (Rectangle) view;
             rect.setFill(getColor());
         }catch(Exception e){

         }

         this.x = x;
         this.y = y;
         getView().setTranslateY(this.y - 20);
         getView().setTranslateX(this.x - 20);
         this.img = null;
         this.img = img;
         if(this.img != null) {
             this.img.setFitWidth(40);
             this.img.setFitHeight(40);
             this.img.setLayoutY(this.getView().getTranslateY());
             this.img.setLayoutX(this.getView().getTranslateX());
         }else{
             tolerance = new Label();
             //ArrayList<Ball> body = snake.getBody();
             //int length = body.size();
             String text = "";
             int length = snake.getLength();

             Random rand = new Random();
             int randomNum = rand.nextInt((length + 10 - 1) + 1) + 1;

             text += ((int)randomNum);
             tolerance.setText(text);
             tolerance.setMinWidth(20);
             tolerance.setMinHeight(20);
         }
    }

    public boolean isAlive(){
        return this.alive;
    }

    public Point2D getVelocity(){
        return velocity;
    }

    public void setVelocity(Point2D velocity){
        this.velocity = velocity;
    }

    public Node getView(){
        return view;
    }

    public void setView(Node view){
        this.view = view;
    }

    public boolean isColliding(GameObject other){
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }

    public void update(){
        view.setTranslateY(view.getTranslateY() + velocity.getY());
        if(this.img != null) {
            this.img.setLayoutX((double) this.getView().getTranslateX() - 15);
            this.img.setLayoutY((double) this.getView().getTranslateY() - 25);
        }

        if(this.tolerance != null){
            this.tolerance.setLayoutX((double) this.getView().getTranslateX() + 30);
            this.tolerance.setLayoutY((double) this.getView().getTranslateY() + 30);
        }
    }

    public void setImage(ImageView img){
        this.img = null;
    }
    public ImageView getImage(){
        return this.img;
    }

    public Label getLabel(){
        return this.tolerance;
    }

    public void setLabelText(String x){
        this.tolerance.setText(x);
    }

}
