package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Sprite{

    //bullet
    private float speed;
    private boolean hit;
    private Vector2 velocities;

    public Bullet(float spawnX, float spawnY, float targetX, float targetY, float speed, float size) {
        super(new Sprite(new Texture("red.png")));
        setPosition(spawnX, spawnY);
        setSize(size, size);
        this.speed = speed;
        hit = false;
        velocities = new Vector2( (targetX - getX()), (targetY - getY()) );
    }

    public float getSpeed() {
        return speed;
    }

    public void setHit() {
        hit = true;
    } //use these when animating player death
    public boolean isHit() {
        return hit;
    }
    public Vector2 getVelocities() {
        return velocities;
    }
}
