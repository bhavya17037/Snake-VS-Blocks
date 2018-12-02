/**
 * This game contains important methods for manipulation, update and collision of game objects
 * @author Bhavya Srivastava,Raghav Gupta
 * @version 1.0
 */

package sample;
import javafx.scene.*;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GameObject implements Serializable {
    private Node view;
    private Point2D velocity = new Point2D(0, 0);
    private boolean alive = true;
    private float x, y;
    private ImageView img;
    private Label tolerance;
    private boolean influenced = false;

    private static int colorIndex = 0;
    private Color[] colorList = {Color.RED, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.VIOLET};

    private Color getColor() {

        colorIndex = (colorIndex + 1) % 6;
        return colorList[colorIndex];
    }

    /**
     * This initializes various properties and attributes of a game object
     * @param view Node
     * @param x float
     * @param y float
     * @param img ImageView
     * @param snake Snake
     */
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

    /**
     * This is getter for alive Boolean variable
     * @return Boolean value
     */
    public boolean isAlive(){
        return this.alive;
    }

    /**
     * This is getter for Velocity of game object
     * @return A point2D object
     */
    public Point2D getVelocity(){
        return velocity;
    }

    /**
     * This is setter for Velocity of game object
     * @param velocity Point2D
     */
    public void setVelocity(Point2D velocity){
        this.velocity = velocity;
    }

    /**
     * This is getter for view attribute of our game object
     * @return An object of Node type
     */
    public Node getView(){
        return view;
    }

    /**
     * This is setter for view attribute of game object
     * @param view Node
     */
    public void setView(Node view){
        this.view = view;
    }

    /**
     * This checks for collision between game objects
     * @param other which has GameObject type
     * @return It returns a Boolean value
     */
    public boolean isColliding(GameObject other){
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }

    /**
     * This updates state of object
     */
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

    /**
     * This is setter for img attribute of game object
     * @param img ImageView type
     */
    public void setImage(ImageView img){
        this.img = null;
    }

    /**
     * This is getter for img attribute of game object
     * @return ImageView object
     */
    public ImageView getImage(){
        return this.img;
    }

    public Label getLabel(){
        return this.tolerance;
    }

    /**
     * This is setter for tolerance attribute of game object
     * @param x String
     */
    public void setLabelText(String x){
        this.tolerance.setText(x);
    }

    /**
     * This is getter for influenced attribute of game object
     * @return Boolean value
     */
    public boolean getInfluenced(){
        return this.influenced;
    }

    /**
     * This is setter for influenced attribute of game object
     * @param f Boolean type
     */
    public void setInfluenced(boolean f){
        this.influenced = f;
    }

}
