package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Random;

public class Aliens implements Disposable{ //manager class for spawning Alien

    private SpriteBatch sb;

    private String textureSource;
    private ArrayList<Alien> aliens;
    private float gap;

    private Timer alienTimer;
    private Timer.Task alienTask;
    private boolean timerCleared;

    //config//
    public static int spawnDelay;
    public static int spawnAlienCount;
    public static float maxStartY;
    public static float minStartY;
    public static float maxVel;
    public static float minVel;
    //

    public Aliens(SpriteBatch sb) {
        this.sb = sb;
        textureSource = "aliens.png";
        aliens = new ArrayList<Alien>();
        gap = 30; //never set to 0

        timerCleared = false;
        alienTimer = new Timer();
        alienTask = new Timer.Task() {
            int i = 0;
            @Override
            public void run() {

                if(i == spawnAlienCount) {
                    alienTimer.clear();
                    timerCleared = true;
                    return;
                }
                i++;

                aliens.add(new Alien(textureSource,
                        Gdx.graphics.getWidth()/6f,
                        new Vector2(-Gdx.graphics.getWidth()/6f,
                                new Random().nextFloat()*(maxStartY-minStartY)+minStartY),
                        new Vector2(new Random().nextFloat()*(maxVel-minVel)+minVel,
                                new Random().nextFloat()*(maxVel-minVel)+minVel)));

            }
        };

        alienTimer.scheduleTask(alienTask, spawnDelay, 0);

    }


    public void update(float delta, float playerPositionX, float playerPositionY) {
        for(Alien alien : aliens) {

            alien.setPlayerPosition(playerPositionX, playerPositionY);

            alien.setPosition(
                    alien.getPosition().x + alien.getVelocity().x * delta,
                    alien.getPosition().y + alien.getVelocity().y * delta);


            if(alien.getPosition().x > Gdx.graphics.getWidth() + gap) {
                alien.setPosition(-alien.getWidth(), alien.getPosition().y);

            }
            if(alien.getPosition().x + alien.getWidth() < -gap) {
                alien.setPosition(Gdx.graphics.getWidth(), alien.getPosition().y);
            }

            if(alien.getPosition().y > Gdx.graphics.getHeight() + gap) {
                alien.setPosition(alien.getPosition().x, -alien.getHeight());
            }
            if(alien.getPosition().y+alien.getHeight() < -gap) {
                alien.setPosition(alien.getPosition().x, Gdx.graphics.getHeight());
            }

            for(Bullet bullet : alien.getBullets()) {
                bullet.setX( bullet.getX() + ( (bullet.getVelocities().x / bullet.getSpeed()) )*delta );
                bullet.setY( bullet.getY() + ( (bullet.getVelocities().y / bullet.getSpeed()) )*delta );
            }

            //set correct render index
            if(alien.getPosition().x + (alien.getWidth()/2f) > playerPositionX) {//right

                if(alien.getPosition().y + (alien.getHeight()/2f) > playerPositionY) {//top
                    //dl
                    alien.setRenderIndex(3);
                } else if(alien.getPosition().y + (alien.getHeight()/2f) < playerPositionY) {//bottom
                    //ul
                    alien.setRenderIndex(0);
                }

            } else if(alien.getPosition().x + (alien.getWidth()/2f) < playerPositionX) {//left

                if(alien.getPosition().y + (alien.getHeight()/2f) > playerPositionY) {//top
                    //dr
                    alien.setRenderIndex(2);
                } else if(alien.getPosition().y + (alien.getHeight()/2f) < playerPositionY) {//bottom
                    //ur
                    alien.setRenderIndex(1);
                }
            }

        }


    }


    public void draw() {

        sb.begin();

        for(Alien alien : aliens) {
            //alien
            sb.draw(alien.getTextureRegion(), alien.getPosition().x, alien.getPosition().y, alien.getWidth(), alien.getHeight());
            //bullets
            for(Bullet bullet : alien.getBullets()) {
                sb.draw(bullet.getTexture(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
            }
        }

        sb.end();

    }

    public ArrayList<Alien> getAliens() {
        return aliens;
    }

    @Override
    public void dispose() {
        if(!timerCleared) {
            alienTimer.clear();
        }

        for(Alien alien : aliens) {
            alien.dispose();
        }

    }
}
