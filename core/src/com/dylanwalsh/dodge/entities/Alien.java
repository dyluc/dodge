package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Random;

public class Alien implements Disposable {

    //

    private Texture texture;
    private TextureRegion[][] regions;
    private int renderIndex;


    private Vector2 position;
    private Vector2 velocity;
    private float width;
    private float height;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> bulletsToRemove;
    private Vector2 playerPosition;


    private Timer shootTimer;
    private Timer.Task shootTask;

    //config//
    public static float fireRate;
    public static float bulletSpeed;
    public static float fireDelay;
    public static int fireProbability;
    public static int velocityChangeProbability;
    public static float bulletSize;
    //

    public Alien(String source, final float width, Vector2 startPosition, Vector2 velocity) {
        position = new Vector2(startPosition.x, startPosition.y);
        texture = new Texture(source);
        regions = TextureRegion.split(texture, 28, 17);

        this.width = width;
        height = texture.getHeight()*(width/regions[0][0].getRegionWidth()); //use first region, all the same: 28

        bullets = new ArrayList<Bullet>();
        bulletsToRemove = new ArrayList<Bullet>();
        this.velocity = new Vector2(velocity.x, velocity.y); //1/2f

        //random invert initial velocity
        if(Math.round(Math.random()) == 1) {
            this.velocity.x = -this.velocity.x;
        }
        if(Math.round(Math.random()) == 1) {
            this.velocity.y = -this.velocity.y;
        }

        playerPosition = new Vector2();

        shootTimer = new Timer();
        shootTask = new Timer.Task() {
            @Override
            public void run() {

                if( (new Random().nextInt(fireProbability - 1 + 1) + 1) == fireProbability ) {
                    bullets.add(new Bullet(position.x+width/2f, position.y, playerPosition.x, playerPosition.y, bulletSpeed, bulletSize));
                }


                if( (new Random().nextInt(velocityChangeProbability - 1 + 1) + 1) == velocityChangeProbability ) { //random number 1 - 4 -> 1 in 4 chance
                    Alien.this.setVelocityX( -Alien.this.getVelocity().x );
                }
                if( (new Random().nextInt(velocityChangeProbability - 1 + 1) + 1) == velocityChangeProbability ) { //random number 1 - 4 -> 1 in 4 chance
                    Alien.this.setVelocityY( -Alien.this.getVelocity().y );
                }

                //do it here, why not
                for(Bullet bullet : bullets) {
                    //using a 50 gap so that even when aliens go out of view, they still fire
                    if(bullet.getX()+bullet.getWidth() < -50 ||
                            bullet.getX() > Gdx.graphics.getWidth()+50 ||
                            bullet.getY()+bullet.getHeight() < -50 ||
                            bullet.getY() > Gdx.graphics.getHeight()+50) {
                        bulletsToRemove.add(bullet);
                    }
                }

                for(Bullet bullet : bulletsToRemove) {
                    bullets.remove(bullet);
                }
                bulletsToRemove.clear();

            }
        };
        shootTimer.scheduleTask(shootTask, fireDelay, fireRate);

    }

    public void setPlayerPosition(float x, float y) {
        playerPosition.x = x;
        playerPosition.y = y;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    public void setPosition(float x, float y) { position.set(x, y); }
    public Vector2 getPosition() { return position; }
    public Vector2 getVelocity() { return velocity; }
    public float getWidth() { return width; }

    public void setVelocityX(float x) {
        velocity.x = x;
    }
    public void setVelocityY(float y) {
        velocity.y = y;
    }

    public float getHeight() { return height; }

    public TextureRegion getTextureRegion() {
        return regions[0][renderIndex]; //return correct region based on renderIndex
    }

    public void setRenderIndex(int renderIndex) {
        this.renderIndex = renderIndex;
    }

    @Override
    public void dispose() {
        texture.dispose();
        shootTimer.clear();
        for(Bullet bullet : bullets) {
            bullet.getTexture().dispose();
        }
    }
}
