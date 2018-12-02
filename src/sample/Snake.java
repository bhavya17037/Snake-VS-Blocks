/**
 * This class basically represents our snake
 * @author Bhavya Srivastava,Raghav Gupta
 * @version 1.0
 */

package sample;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Snake implements Serializable {
    private static ArrayList<Ball> body;
    private Ball head;
    private float x;
    private float tailYPos;
    private Label score;
    private int maxScore;

    /**
     * This initializes properties for our snake
     * @param root Pane type
     */
    public Snake(Pane root){
        body = new ArrayList<Ball>();
        this.x = 400;
        head = new Ball(200, 440, this);
        Node headview = head.getView();
        head.setView(headview);
        body.add(head);
        root.getChildren().add(head.getView());
        score = new Label();
        score.setText(Integer.toString(body.size()));

    }

    /**
     * This adds score label which is on the snake
     * @param root Pane type
     * @return A Pane object
     */
    public Pane addLabel(Pane root){
        root.getChildren().add(score);
        return root;
    }

    /**
     * This updates score label on the snake
     */
    public void updateLabel(){
        score.setLayoutY(body.get(0).getView().getTranslateY());
        score.setLayoutX(body.get(0).getView().getTranslateX());
        if (body.size() > maxScore) {
            maxScore = body.size();
        }
        score.setText(Integer.toString(body.size()));
    }

    /**
     * This adds a part to the snakes body at the specified x position
     * @param root Pane type
     * @param x double type
     */
    public void addPartAtX(Pane root, double x) {
        if(body.size() == 0){
            head = new Ball((int) x, 400, this);
            Node headview = head.getView();
            head.setView(headview);
            body.add(head);
            root.getChildren().add(head.getView());
        }else{
            Ball last = body.get(body.size() - 1);
            Node lastview = last.getView();
            Ball newpart = new Ball(0,0, this);
            Node view = newpart.getView();
            view.setTranslateY(lastview.getTranslateY() + 40);
            view.setTranslateX((int) x);
            newpart.setView(view);
            body.add(newpart);
            root.getChildren().add(view);
        }
    }

    /**
     * This adds a part to the snakes body
     * @param root Pane type
     */
    public void addPart(Pane root){
        if(body.size() == 0){
            head = new Ball(200, 400, this);
            Node headview = head.getView();
            head.setView(headview);
            body.add(head);
            root.getChildren().add(head.getView());
        }else{
            Ball last = body.get(body.size() - 1);
            Node lastview = last.getView();
            Ball newpart = new Ball(0,0, this);
            Node view = newpart.getView();
            view.setTranslateY(lastview.getTranslateY() + 40);
            view.setTranslateX(200);
            newpart.setView(view);
            body.add(newpart);
            root.getChildren().add(view);
        }
    }

    public int getMaxScore() {
        return maxScore;
    }

    /**
     * This deletes a part of the snake
     * @param root Pane type
     */
    public Pane deletePart(Pane root){
        if(body.size() == 1){
            MainGame.isGameOver = true;
            System.out.println("Game Over");
            return root;
        }else{
            Ball temp = body.get(body.size() - 1);
            body.remove(temp);
            root.getChildren().remove(temp.getView());
            root.getChildren().remove(temp.getImage());
            return root;
        }
    }

    /**
     * This sets X coordinate for each part of body
     * @param x float type
     * @param left and right which are of type Boolean
     */
    public void setX(float x, boolean left, boolean right){
        if(!left && !right) {
            this.x = x;
            for (int i = 0; i < body.size(); i++) {
                body.get(i).getView().setTranslateX(x);
            }
        }else{
            if(left){
                this.x = x;
                for (int i = 0; i < body.size(); i++) {
                    body.get(i).getView().setTranslateX(x - (body.size() - i) * 10);
                }
            }
            else{
                this.x = x;
                for (int i = 0; i < body.size(); i++) {
                    body.get(i).getView().setTranslateX(x + (body.size() - i) * 10);
                }
            }
        }
    }

    /**
     * This gets X coordinate of head of snake
     * @return A float value
     */
    public float getX(){
        return this.x;
    }

    /**
     * This is getter for head of snake
     * @return A Ball Object
     */
    public Ball getHead(){
        return this.head;
    }

    /**
     * This is getter for arrayList of balls which makes up the snakes body
     * @return An ArrayList of Ball type
     */
    public ArrayList<Ball> getBody() {
        return this.body;
    }

    /**
     * This is getter for length of snake
     * @return An Integer value
     */
    public int getLength(){
        return body.size();
    }
}
