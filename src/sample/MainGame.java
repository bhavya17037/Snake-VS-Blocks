package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
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
    Timer time2;
    boolean particlesPresent = false;
    Snake snake;
    Particle p1, p2, p3, p4, p5, p6, p7, p8, p9;
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

    class particleTimer extends TimerTask{
        @Override
        public void run(){
            particlesPresent = false;

            time2.cancel();
        }
    }

    public void deleteParticles(){
        root.getChildren().remove(p1.getView());
        root.getChildren().remove(p2.getView());
        root.getChildren().remove(p3.getView());
        root.getChildren().remove(p4.getView());
        root.getChildren().remove(p5.getView());
        root.getChildren().remove(p6.getView());
        root.getChildren().remove(p7.getView());
        root.getChildren().remove(p8.getView());
        root.getChildren().remove(p9.getView());

        root.getChildren().remove(p1.getImage());
        root.getChildren().remove(p2.getImage());
        root.getChildren().remove(p3.getImage());
        root.getChildren().remove(p4.getImage());
        root.getChildren().remove(p5.getImage());
        root.getChildren().remove(p6.getImage());
        root.getChildren().remove(p7.getImage());
        root.getChildren().remove(p8.getImage());
        root.getChildren().remove(p9.getImage());
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

    public void burst(GameObject obj, boolean isBlock){
        double sx = 0;
        double sy = 0;
        if(isBlock){
            sx = obj.getView().getTranslateX() + 40;
            sy = obj.getView().getTranslateY() + 40;
        }else {
            sx = obj.getView().getTranslateX();
            sy = obj.getView().getTranslateY();
        }
        p1 = new Particle(sx, sy, snake);
        p2 = new Particle(sx, sy, snake);
        p3 = new Particle(sx, sy, snake);
        p4 = new Particle(sx, sy, snake);
        p5 = new Particle(sx, sy, snake);
        p6 = new Particle(sx, sy, snake);
        p7 = new Particle(sx, sy, snake);
        p8 = new Particle(sx, sy, snake);
        p9 = new Particle(sx, sy, snake);
        root.getChildren().add(p1.getView());
        root.getChildren().add(p2.getView());
        root.getChildren().add(p3.getView());
        root.getChildren().add(p4.getView());
        root.getChildren().add(p5.getView());
        root.getChildren().add(p6.getView());
        root.getChildren().add(p7.getView());
        root.getChildren().add(p8.getView());
        root.getChildren().add(p9.getView());
        Random r = new Random();

        double alpha1 = 0 + (2 * 3.1415 - 0) * r.nextDouble();
        double dist1 = 10 + (50 - 10) * r.nextDouble();
        double fx1 = sx + 100 * Math.cos(alpha1);
        double fy1 = sy + 100 * Math.sin(alpha1);

        double alpha2 = 0 + (2 * 3.1415 - 0) * r.nextDouble();
        double dist2 = 10 + (50 - 10) * r.nextDouble();
        double fx2 = sx + 100 * Math.cos(alpha2);
        double fy2 = sy + 100 * Math.sin(alpha2);

        double alpha3 = 0 + (2 * 3.1415 - 0) * r.nextDouble();
        double dist3 = 10 + (50 - 10) * r.nextDouble();
        double fx3 = sx + 100 * Math.cos(alpha3);
        double fy3 = sy + 100 * Math.sin(alpha3);

        double alpha4 = 0 + (2 * 3.1415 - 0) * r.nextDouble();
        double dist4 = 10 + (50 - 10) * r.nextDouble();
        double fx4 = sx + 100 * Math.cos(alpha4);
        double fy4 = sy + 100 * Math.sin(alpha4);

        double alpha5 = 0 + (2 * 3.1415 - 0) * r.nextDouble();
        double dist5 = 10 + (50 - 10) * r.nextDouble();
        double fx5 = sx + 100 * Math.cos(alpha5);
        double fy5 = sy + 100 * Math.sin(alpha5);

        double alpha6 = 0 + (2 * 3.1415 - 0) * r.nextDouble();
        double dist6 = 10 + (50 - 10) * r.nextDouble();
        double fx6 = sx + 100 * Math.cos(alpha6);
        double fy6 = sy + 100 * Math.sin(alpha6);

        double alpha7 = 0 + (2 * 3.1415 - 0) * r.nextDouble();
        double dist7 = 10 + (50 - 10) * r.nextDouble();
        double fx7 = sx + 100 * Math.cos(alpha7);
        double fy7 = sy + 100 * Math.sin(alpha7);

        double alpha8 = 0 + (2 * 3.1415 - 0) * r.nextDouble();
        double dist8 = 10 + (50 - 10) * r.nextDouble();
        double fx8 = sx + 100 * Math.cos(alpha8);
        double fy8 = sy + 100 * Math.sin(alpha8);

        double alpha9 = 0 + (2 * 3.1415 - 0) * r.nextDouble();
        double dist9 = 10 + (50 - 10) * r.nextDouble();
        double fx9 = sx + 100 * Math.cos(alpha9);
        double fy9 = sy + 100 * Math.sin(alpha9);

        TranslateTransition translateTransition1 = new TranslateTransition();
        translateTransition1.setDuration(Duration.seconds(0.2));
        translateTransition1.setNode(p1.getView());
        translateTransition1.setFromY(sy);
        translateTransition1.setToY(fy1);
        translateTransition1.setFromX(sx);
        translateTransition1.setToX(fx1);

        TranslateTransition translateTransition2 = new TranslateTransition();
        translateTransition2.setDuration(Duration.seconds(0.2));
        translateTransition2.setNode(p2.getView());
        translateTransition2.setFromY(sy);
        translateTransition2.setToY(fy2);
        translateTransition2.setFromX(sx);
        translateTransition2.setToX(fx2);

        TranslateTransition translateTransition3 = new TranslateTransition();
        translateTransition3.setDuration(Duration.seconds(0.2));
        translateTransition3.setNode(p3.getView());
        translateTransition3.setFromY(sy);
        translateTransition3.setToY(fy3);
        translateTransition3.setFromX(sx);
        translateTransition3.setToX(fx3);

        TranslateTransition translateTransition4 = new TranslateTransition();
        translateTransition4.setDuration(Duration.seconds(0.2));
        translateTransition4.setNode(p4.getView());
        translateTransition4.setFromY(sy);
        translateTransition4.setToY(fy4);
        translateTransition4.setFromX(sx);
        translateTransition4.setToX(fx4);

        TranslateTransition translateTransition5 = new TranslateTransition();
        translateTransition5.setDuration(Duration.seconds(0.2));
        translateTransition5.setNode(p5.getView());
        translateTransition5.setFromY(sy);
        translateTransition5.setToY(fy5);
        translateTransition5.setFromX(sx);
        translateTransition5.setToX(fx5);

        TranslateTransition translateTransition6 = new TranslateTransition();
        translateTransition6.setDuration(Duration.seconds(0.2));
        translateTransition6.setNode(p6.getView());
        translateTransition6.setFromY(sy);
        translateTransition6.setToY(fy6);
        translateTransition6.setFromX(sx);
        translateTransition6.setToX(fx6);

        TranslateTransition translateTransition7 = new TranslateTransition();
        translateTransition7.setDuration(Duration.seconds(0.2));
        translateTransition7.setNode(p1.getView());
        translateTransition7.setFromY(sy);
        translateTransition7.setToY(fy7);
        translateTransition7.setFromX(sx);
        translateTransition7.setToX(fx7);

        TranslateTransition translateTransition8 = new TranslateTransition();
        translateTransition8.setDuration(Duration.seconds(0.2));
        translateTransition8.setNode(p8.getView());
        translateTransition8.setFromY(sy);
        translateTransition8.setToY(fy8);
        translateTransition8.setFromX(sx);
        translateTransition8.setToX(fx8);

        TranslateTransition translateTransition9 = new TranslateTransition();
        translateTransition9.setDuration(Duration.seconds(0.2));
        translateTransition9.setNode(p1.getView());
        translateTransition9.setFromY(sy);
        translateTransition9.setToY(fy9);
        translateTransition9.setFromX(sx);
        translateTransition9.setToX(fx9);

        particlesPresent = true;

        time2 = new Timer();
        time2.schedule(new particleTimer(), 200);

        translateTransition1.play();
        translateTransition2.play();
        translateTransition3.play();
        translateTransition4.play();
        translateTransition5.play();
        translateTransition6.play();
        translateTransition7.play();
        translateTransition8.play();
        translateTransition9.play();


        FadeTransition ft1 = new FadeTransition(Duration.millis(3000), p1.getView());
        ft1.setFromValue(1.0);
        ft1.setToValue(0.0);
        ft1.setCycleCount(4);
        ft1.setAutoReverse(true);

        FadeTransition ft2 = new FadeTransition(Duration.millis(3000), p2.getView());
        ft2.setFromValue(1.0);
        ft2.setToValue(0.0);
        ft2.setCycleCount(4);
        ft2.setAutoReverse(true);

        FadeTransition ft3 = new FadeTransition(Duration.millis(3000), p3.getView());
        ft3.setFromValue(1.0);
        ft3.setToValue(0.0);
        ft3.setCycleCount(4);
        ft3.setAutoReverse(true);

        FadeTransition ft4 = new FadeTransition(Duration.millis(3000), p4.getView());
        ft4.setFromValue(1.0);
        ft4.setToValue(0.0);
        ft4.setCycleCount(4);
        ft4.setAutoReverse(true);

        FadeTransition ft5 = new FadeTransition(Duration.millis(3000), p5.getView());
        ft5.setFromValue(1.0);
        ft5.setToValue(0.0);
        ft5.setCycleCount(4);
        ft5.setAutoReverse(true);

        FadeTransition ft6 = new FadeTransition(Duration.millis(3000), p6.getView());
        ft6.setFromValue(1.0);
        ft6.setToValue(0.0);
        ft6.setCycleCount(4);
        ft6.setAutoReverse(true);

        FadeTransition ft7 = new FadeTransition(Duration.millis(3000), p7.getView());
        ft7.setFromValue(1.0);
        ft7.setToValue(0.0);
        ft7.setCycleCount(4);
        ft7.setAutoReverse(true);

        FadeTransition ft8 = new FadeTransition(Duration.millis(3000), p8.getView());
        ft8.setFromValue(1.0);
        ft8.setToValue(0.0);
        ft8.setCycleCount(4);
        ft8.setAutoReverse(true);

        FadeTransition ft9 = new FadeTransition(Duration.millis(3000), p9.getView());
        ft9.setFromValue(1.0);
        ft9.setToValue(0.0);
        ft9.setCycleCount(4);
        ft9.setAutoReverse(true);

        ft1.play();
        ft2.play();
        ft3.play();
        ft4.play();
        ft5.play();
        ft6.play();
        ft7.play();
        ft8.play();
        ft9.play();


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

        if(!particlesPresent){
            if(p1 != null && p2 != null && p3 != null && p4 != null && p5 != null && p6 != null && p7 != null && p8 != null && p9 != null)
                deleteParticles();
        }

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
                        burst(blocks.get(j), true);
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
                translateTransition.setDuration(Duration.seconds(0.18));
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
