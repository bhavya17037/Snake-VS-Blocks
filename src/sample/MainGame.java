package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.Random;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;


public class MainGame extends Application {

    public static Pane root;
    public int width = 400;
    private static ArrayList<Chain> chainsOnScreen = new ArrayList<Chain>();
    private static ArrayList<Token> Tokens = new ArrayList<Token>();
    private static ArrayList<Token> Points = new ArrayList<Token>();
    private static ArrayList<Token> Bombs = new ArrayList<Token>();
    private static ArrayList<Token> Shields = new ArrayList<Token>();
    private static ArrayList<Token> Magnets = new ArrayList<Token>();

    Timer time;
    Timer time1;
    Snake snake;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private boolean shieldActive = false;
    private boolean magnetActive = false;

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    class shieldTimer extends TimerTask{
        @Override
        public void run() {
            shieldActive = false;
            time.cancel();
        }
    }

    class magnetTimer extends TimerTask{
        @Override
        public void run(){
            magnetActive = false;
            for(int p = 0; p < Points.size(); p++){
                Token curr = Points.get(p);
                curr.setInfluenced(false);
            }
            time1.cancel();
        }
    }

    private Parent createContent(){
        root = new Pane();
        root.setPrefSize(width,800);
        root.setBackground(new Background((new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
        snake = new Snake(root);
        snake.addPart(root);
        snake.addPart(root);
        snake.addPart(root);
        snake.addPart(root);
        snake.addPart(root);
        snake.addPart(root);
        snake.addPart(root);
        snake.addPart(root);
        root = snake.addLabel(root);
        URL resource = getClass().getResource("gameMainMusic.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(resource.toString()));
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        mediaPlayer.play();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    public void addChain(){
        ArrayList<Block> blocks = new ArrayList<Block>();
        /*int num = (int)(Math.random() * 5 + 1);
        if(num < 2){
            num = 2;
        }*/
        //float startx = (float)randInt(20, 400);
        int height = -20;
        /*if(chainsOnScreen.size() != 0){
            if(chainsOnScreen.get(chainsOnScreen.size() - 1).getBlocks().get(0).getView().getTranslateY() > 500){
                height = -20;
            }else{
                height = (int) chainsOnScreen.get(chainsOnScreen.size() - 1).getBlocks().get(0).getView().getTranslateY() - 40;
            }
        }*/
        Block block = new Block(20, height, snake);
        blocks.add(block);
        root.getChildren().add(block.getView());
        root.getChildren().add(block.getLabel());
        for(int i = 1; i <= 5; i++){
            block = new Block(i * 80 + 20, height, snake);
            blocks.add(block);
            root.getChildren().add(block.getView());
            root.getChildren().add(block.getLabel());
        }

        Chain newchain = new Chain(blocks);
        chainsOnScreen.add(newchain);
    }

    public void addPoints() throws FileNotFoundException {
        Token point = null;
        try {
            int x = randInt(20, width - 20);
            Token p = new Point((float) x, -20, snake);
            root.getChildren().add(p.getView());
            root.getChildren().add(p.getImage());
            point = p;
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }
        Tokens.add(point);
        Points.add(point);
    }

    public void addBombs() throws FileNotFoundException{
        Token bomb = null;
        try {
            int x = randInt(20, width - 20);
            Token b = new Bomb((float)x, -20, snake);
            root.getChildren().add(b.getView());
            root.getChildren().add(b.getImage());
            bomb = b;
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }
        Tokens.add(bomb);
        Bombs.add(bomb);
    }

    public void addMagnets() throws FileNotFoundException{
        Token magnet = null;
        try {
            int x = randInt(20, width - 20);
            Token m = new Magnet((float) x, -20, snake);
            root.getChildren().add(m.getView());
            root.getChildren().add(m.getImage());
            magnet = m;
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }
        Tokens.add(magnet);
        Magnets.add(magnet);
    }

    public void addShields() throws FileNotFoundException{
        Token shield = null;
        try {
            int x = randInt(20, width - 20);
            Token b = new Shield((float) x, -20, snake);
            root.getChildren().add(b.getView());
            root.getChildren().add(b.getImage());
            shield = b;
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }
        Tokens.add(shield);
        Shields.add(shield);
    }

    private void onUpdate(){

        Ball head = snake.getHead();
        snake.updateLabel();
        for(int i = 0; i < chainsOnScreen.size(); i++){
            Chain curr = chainsOnScreen.get(i);
            curr.setY(curr.getBlocks().get(0).getView().getTranslateY());
            ArrayList<Block> blocks = curr.getBlocks();
            for(int j = 0; j < blocks.size(); j++){
                if(head.isColliding(blocks.get(j)) && !shieldActive){
                    double y = blocks.get(j).getView().getTranslateY() - 40;
                    int tolerance = Integer.parseInt(blocks.get(j).getLabel().getText());
                    tolerance--;
                    blocks.get(j).setLabelText(Integer.toString(tolerance));
                    if(tolerance == 0){
                        root.getChildren().remove(blocks.get(j).getView());
                        root.getChildren().remove(blocks.get(j).getLabel());
                        blocks.remove(j);
                        curr.setBlocks(blocks);
                    }
                    root = snake.deletePart(root);
                    for(int k = 0; k < blocks.size(); k++){
                        TranslateTransition translateTransition = new TranslateTransition();
                        translateTransition.setDuration(Duration.seconds(0.3));
                        translateTransition.setNode(blocks.get(k).getView());
                        translateTransition.setFromY(blocks.get(k).getView().getTranslateY());
                        translateTransition.setToY(blocks.get(k).getView().getTranslateY() - 40);
                        translateTransition.play();
                    }
                    curr.setY(y);
                    for(int k = 0; k < Tokens.size(); k++){
                        TranslateTransition translateTransition = new TranslateTransition();
                        translateTransition.setDuration(Duration.seconds(0.3));
                        translateTransition.setNode(Tokens.get(k).getView());
                        translateTransition.setFromY(Tokens.get(k).getView().getTranslateY());
                        translateTransition.setToY(Tokens.get(k).getView().getTranslateY() - 40);
                        translateTransition.play();
                    }

                    break;
                    /*root.getChildren().remove(blocks.get(j).getView());
                    root.getChildren().remove(blocks.get(j).getLabel());
                    blocks.remove(j);
                    curr.setBlocks(blocks);*/
                }
            }
        }

        for (int i = 0; i < Points.size(); i++) {
            Token curr = Points.get(i);
            head = snake.getHead();
            if (head.isColliding(curr)) {
                snake.addPart(root);
                root.getChildren().remove(curr.getView());
                root.getChildren().remove(curr.getImage());
                Points.remove(i);
                Tokens.remove(curr);
                break;
            }
        }



        for(int i = 0; i < Points.size(); i++){
            Token curr = Points.get(i);
            head = snake.getHead();
            double ax = head.getView().getTranslateX();
            double ay = head.getView().getTranslateY();
            double bx = curr.getView().getTranslateX();
            double by = curr.getView().getTranslateY();

            double dist = Math.sqrt((ax - bx) * (ax - bx) + (ay - by) * (ay - by));
            if(dist <= 100 && !curr.getInfluenced() && magnetActive){
                curr.setInfluenced(true);
                TranslateTransition translateTransition = new TranslateTransition();
                translateTransition.setDuration(Duration.seconds(0.3));
                translateTransition.setNode(curr.getView());
                translateTransition.setFromY(curr.getView().getTranslateY());
                translateTransition.setToY(head.getView().getTranslateY());
                translateTransition.setFromX(curr.getView().getTranslateX());
                translateTransition.setToX(head.getView().getTranslateX());
                translateTransition.play();
            }
        }



        for(int i = 0; i < Shields.size(); i++){
            Token curr = Shields.get(i);
            head = snake.getHead();

            if(head.isColliding(curr)){
                shieldActive = true;
                root.getChildren().remove(curr.getView());
                root.getChildren().remove(curr.getImage());
                Shields.remove(i);
                Tokens.remove(curr);
                time = new Timer();
                time.schedule(new shieldTimer(), 5000);
            }
        }

        for(int i = 0; i < Magnets.size(); i++){
            Token curr = Magnets.get(i);
            head = snake.getHead();

            if(head.isColliding(curr)){
                magnetActive = true;
                root.getChildren().remove(curr.getView());
                root.getChildren().remove(curr.getImage());
                Magnets.remove(i);
                Tokens.remove(curr);
                time1 = new Timer();
                time1.schedule(new magnetTimer(), 10000);
            }
        }

        for(int i = 0; i < Bombs.size(); i++){
            Token curr = Bombs.get(i);
            head = snake.getHead();
            if(head.isColliding(curr)){
                root.getChildren().remove(curr.getView());
                root.getChildren().remove(curr.getImage());
                Bombs.remove(i);
                Tokens.remove(curr);
                System.out.println(chainsOnScreen.size());
                while(chainsOnScreen.size() != 0){
                    ArrayList<Block> blocks = chainsOnScreen.get(0).getBlocks();
                    Chain currentChain = chainsOnScreen.get(0);
                    for(int k = 0; k < blocks.size(); k++){
                        root.getChildren().remove(blocks.get(k).getView());
                        root.getChildren().remove(blocks.get(k).getLabel());
                    }

                    while(blocks.size() != 0){
                        blocks.remove(0);
                        currentChain.setBlocks(blocks);
                    }
                    chainsOnScreen.remove(0);
                }

                break;
            }
        }

        int i = 0;
        while(i < chainsOnScreen.size()){
            Chain curr = chainsOnScreen.get(i);
            ArrayList<Block> blocks = curr.getBlocks();
            for(int j = 0; j < blocks.size(); j++){
                blocks.get(j).update();
            }
            Block first = curr.getBlocks().get(0);
            if(first.getView().getTranslateY() > 800){
                for(int j = 0; j < chainsOnScreen.get(i).getBlocks().size(); j++){
                    chainsOnScreen.get(i).getBlocks().get(j).setImage(null);
                    root.getChildren().remove(chainsOnScreen.get(i).getBlocks().get(j).getLabel());
                }
                root.getChildren().remove(chainsOnScreen.get(i));
                chainsOnScreen.remove(i);
            }else{
                i++;
            }
        }
        i = 0;

        while(i < Tokens.size()){
            Token curr = Tokens.get(i);
            curr.update();

            if(curr.getView().getTranslateY() > 800){
                root.getChildren().remove(curr);
                root.getChildren().remove(curr.getImage());
                Tokens.remove(curr);
            }
            i++;
        }

        ArrayList<Ball> body = snake.getBody();
        for(int j = 0; j < body.size(); j++){
            body.get(j).update();
        }

        if(chainsOnScreen.size() > 0) {
            if (chainsOnScreen.get(chainsOnScreen.size() - 1).getBlocks().get(0).getView().getTranslateY() > 700) {
                addChain();
            }
        }else{
            addChain();
        }

        if(Math.random() < 0.005){
            try {
                addPoints();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }

        if(Math.random() < 0.001){
            try {
                addBombs();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }

        if(Math.random() < 0.001){
            try {
                addMagnets();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }

        if(Math.random() < 0.001){
            try {
                addShields();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }

        if(isLeftPressed){
            snake.setX(snake.getX() - 10, isLeftPressed, isRightPressed);
        }
        else if(isRightPressed){
            snake.setX(snake.getX() + 10, isLeftPressed, isRightPressed);
        }else{
            snake.setX(snake.getX(), false, false);
        }
    }

    public ArrayList<Chain> getChains(){
        return chainsOnScreen;
    }

    public void setChainsOnScreen(ArrayList<Chain> chain){
        chainsOnScreen = chain;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.getScene().setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.LEFT){
                isLeftPressed = true;
            }else if(e.getCode() == KeyCode.RIGHT){
                isRightPressed = true;
            }
        });

        primaryStage.getScene().setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.LEFT){
                isLeftPressed = false;
            }else if(e.getCode() == KeyCode.RIGHT){
                isRightPressed = false;
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
