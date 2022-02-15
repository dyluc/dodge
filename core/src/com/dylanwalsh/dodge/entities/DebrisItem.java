package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

public class DebrisItem {
    //

    private Texture texture;
    private Vector2 vel;
    private float opacity;

    //timer
    private Timer fadeTimer;
    private Timer.Task fadeTask;
    public boolean timerCleared;


    //config//
    public static float fadeInterval; //every 0.1 seconds
    public static float fadeIncrement; //has to eventually reach 1f
    public static float spawnDelay;
    //add some randomness to spawn delay


    public DebrisItem(String src, Vector2 vel) {
        texture = new Texture(src);
        this.vel = new Vector2(vel); //make shore it is not a reference to the vector2 in Debris.

        //random invert initial velocity
        if(Math.round(Math.random()) == 1) {
            this.vel.x = -this.vel.x;
        }
        if(Math.round(Math.random()) == 1) {
            this.vel.y = -this.vel.y;
        }



        opacity = 0f;

        timerCleared = false;
        fadeTimer = new Timer();
        fadeTask = new Timer.Task() {
            @Override
            public void run() {
                if(opacity >= 1f) {
                    fadeTimer.clear();
                    timerCleared = true;
                    opacity = 1f; //in case it went over
                    return;
                }


                opacity += fadeIncrement;
            }
        };
        fadeTimer.scheduleTask(fadeTask, spawnDelay, fadeInterval);
    }

    public Vector2 getVelocity() {
        return vel;
    }
    public void setVelocityX(float x) {
        this.vel.x = x;
    }
    public void setVelocityY(float y) {
        this.vel.y = y;
    }
    public float getOpacity() { return opacity; }
    public Texture getTexture() {
        return texture;
    }
}
