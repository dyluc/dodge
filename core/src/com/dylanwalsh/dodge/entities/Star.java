package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Star {

    private Vector2 position;
    private float size;
    private Vector2 velocity;

    public Star(float size, Vector2 velocity) {
        this.size = size;
        this.velocity = velocity;

        position = new Vector2(
                new Random().nextFloat()*( (Gdx.graphics.getWidth()-10) - 10)+10,
                Gdx.graphics.getHeight()
        );

    }


    public float getSize() {
        return size;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setX(float x) {
        position.x = x;
    }
    public void setY(float y) {
        position.y = y;
    }
    public Vector2 getPosition() {
        return position;
    }



}
