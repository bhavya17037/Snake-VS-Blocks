package sample;

public class resumeScreenController {



    public void play() throws Exception {
        Main.playNewGame();
    }

    public void resumeGame() throws Exception {
        Main.playGame();
    }

    public void exit() {
        Main.getStage().close();
    }
}
