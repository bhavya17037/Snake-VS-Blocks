package sample;

import java.lang.reflect.Array;
import java.util.*;

public class Chain {
    private ArrayList<Block> blocks;
    public Chain(ArrayList<Block> blocks){
        this.blocks = blocks;
    }

    public void setY(double y){

        boolean f = false;
        for(int i = 0; i < blocks.size(); i++){
            for(int j = i + 1; j < blocks.size(); j++){
                if(blocks.get(i).getView().getTranslateY() != blocks.get(j).getView().getTranslateY()){
                    blocks.get(i).getView().setTranslateY(y);
                }
            }
        }
        for(int i = 0; i < blocks.size(); i++){
            blocks.get(i).getView().setTranslateY(y);
        }
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }
}

