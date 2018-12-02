package sample;

import java.io.Serializable;

public class coordinate implements Serializable {

    private float x;
    private float y;

    public coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
