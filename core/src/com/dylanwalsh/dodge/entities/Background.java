package com.dylanwalsh.dodge.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.dylanwalsh.dodge.GameClass;
import com.dylanwalsh.dodge.screens.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Background implements Disposable{

    private boolean createStars;

    private SpriteBatch sb;

    private Texture texture;
    private ArrayList<Star> stars;
    private ArrayList<Star> starsToRemove; //to avoid ConcurrentModificationException

    //config//
    public static int starsPerSpawn;
    public static float maxSize;
    public static float minSize;
    public static float maxSpeed;
    public static float minSpeed;
    //

    private Timer starSpawnTimer;
    private Timer.Task starSpawnTask;

    public Background() {

        texture = new Texture("white.png");
        stars = new ArrayList<Star>();
        starsToRemove = new ArrayList<Star>();
        createStars = true;

        starSpawnTimer = new Timer();
        starSpawnTask = new Timer.Task() {
            @Override
            public void run() {

                if(createStars) {
                    for(int i = 0 ; i < starsPerSpawn; i ++) {
                        if(Math.round(Math.random()) == 1) {
                            stars.add( new Star(
                                    new Random().nextFloat()*(maxSize-minSize)+minSize,
                                    new Vector2( 0, new Random().nextFloat()*(maxSpeed-minSpeed)+minSpeed ))
                            );
                        }
                    }
                }


            }
        };

        starSpawnTimer.scheduleTask(starSpawnTask, 1f, .75f);

    }
    public void setSb(SpriteBatch sb) {
        this.sb = sb;
    }

    public void update(float delta) {

        for(Star star : stars) {
            star.setY( star.getPosition().y - star.getVelocity().y * delta);

            if(star.getPosition().y < -maxSize) {
                starsToRemove.add(star);
            }
        }

        for(Star star : starsToRemove) {
            stars.remove(star);
        }
        starsToRemove.clear();


    }

    public void draw() {
        sb.begin();

        for(Star star : stars) {

            sb.draw(texture, star.getPosition().x, star.getPosition().y, star.getSize(), star.getSize());

        }

        sb.end();
    }

    public void setCreateStars(boolean createStars) {
        this.createStars = createStars;
    }

    public boolean isCreateStars() {
        return createStars;
    }

    @Override
    public void dispose() {
        starSpawnTimer.clear();
        texture.dispose();
    }
}
