/**
 * This class sets up a new chain of blocks
 * @author Bhavya Srivastava,Raghav Gupta
 * @version 1.0
 */


package sample;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class Chain implements Serializable {
    private ArrayList<Block> blocks;

    /**
     * This initializes a new chain of blocks
     * @param blocks ArrayList of type Block
     */
    public Chain(ArrayList<Block> blocks){
        this.blocks = blocks;
    }

    /**
     * Setter for Y coordinate of a chain
     * @param y double type
     */
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

    /**
     * Getter for blocks ArrayList
     * @return An ArrayList of type Block
     */
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    /**
     * Setter for blocks ArrayList
     * @param blocks ArrayList of type Block
     */
    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }
}

