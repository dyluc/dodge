package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Random;

public class Debris implements Disposable{

    private SpriteBatch sb;

    //

    //debris
    public static ArrayList<DebrisCircle> debrisCircles;
    public static ArrayList<DebrisRectangle> debrisRectangles;


    //spawns and fade
    private Timer spawnTimer;
    private Timer.Task spawnTask;
    private boolean timerCleared;

    //config//
    public static float spawnRate;
    public static int spawnRoundDelay;
    public static int spawnDebrisCount;
    public static float maxVel;
    public static float minVel;
    //

    //sources
    private String[] sources;
    //sizes
    private float[] sizes; //radius will be half the sizes
    //positions
    private Vector2[] positions;

    //gap
    private int gap; //gap outside of screen to bounce debris off


    public Debris(SpriteBatch sb) {
        this.sb = sb;

        //debris
        debrisCircles = new ArrayList<DebrisCircle>();
        debrisRectangles = new ArrayList<DebrisRectangle>();

        //sources - check sources to determine whether circle or rectangle
        sources = new String[2];
        sources[0] = "debris_one.png"; //circle
        sources[1] = "debris_two.png"; //rect
        //sizes - radius will be half the sizes
        sizes = new float[3];
        sizes[0] = Gdx.graphics.getWidth()/14f;
        sizes[1] = Gdx.graphics.getWidth()/12f;
        sizes[2] = Gdx.graphics.getWidth()/9f;
        //positions - circle from centre and rectangle from bottom left
        positions = new Vector2[3];
        positions[0] = new Vector2(Gdx.graphics.getWidth()/2f, (Gdx.graphics.getHeight()/4f));
        positions[1] = new Vector2(Gdx.graphics.getWidth()/2f, (Gdx.graphics.getHeight()/4f)*2f);
        positions[2] = new Vector2(Gdx.graphics.getWidth()/2f, (Gdx.graphics.getHeight()/4f)*3f);

        //gap - set gap based off maximum size - index at 1 is biggest - 250 extra value
        gap = (int)sizes[2] + 150;

        //spawns
        spawnTimer = new Timer();

        timerCleared = false;
        spawnTask = new Timer.Task() {
            int count = 0;
            @Override
            public void run() {
                if(count == spawnDebrisCount) {
                    spawnTimer.clear();
                    timerCleared = true;
                    return;
                }
                count++;

                String source = sources[ Math.round((float)Math.random()) ];
                if(source == sources[0]) { //Circle
                    debrisCircles.add(new DebrisCircle(
                            source,
                            new Vector2( new Random().nextFloat()*(maxVel-minVel)+minVel,
                                    new Random().nextFloat()*(maxVel-minVel)+minVel ),
                            positions[ new Random().nextInt((positions.length-1) - 0 + 1) + 0 ],
                            sizes[ new Random().nextInt((sizes.length-1) - 0 + 1) + 0 ] / 2f
                            ));
                }
                if(source == sources[1]) { //Rectangle

                    debrisRectangles.add(new DebrisRectangle(
                            source,
                            new Vector2( new Random().nextFloat()*(maxVel-minVel)+minVel,
                                    new Random().nextFloat()*(maxVel-minVel)+minVel ),
                            positions[ new Random().nextInt((positions.length-1) - 0 + 1) + 0 ],
                            sizes[ new Random().nextInt((sizes.length-1) - 0 + 1) + 0 ]
                    ));

                }


            }
        };
        spawnTimer.scheduleTask(spawnTask, spawnRoundDelay, spawnRate);
    }

    public void update(float delta) {

        //render debris
        for(DebrisRectangle r : debrisRectangles) {

            if( (r.getRectangle().getX() + r.getRectangle().getWidth() > Gdx.graphics.getWidth() + gap) ||
                    (r.getRectangle().getX() < -gap) ) {
                r.setVelocityX( -r.getVelocity().x );
            }
            if( (r.getRectangle().getY() + r.getRectangle().getHeight() > Gdx.graphics.getHeight() + gap) ||
                    (r.getRectangle().getY() < -gap) ) {
                r.setVelocityY( -r.getVelocity().y );
            }


            r.getRectangle().setX( r.getRectangle().getX() + r.getVelocity().x * delta);
            r.getRectangle().setY( r.getRectangle().getY() + r.getVelocity().y * delta);

        }
        for(DebrisCircle c : debrisCircles) {

            if( (c.getCircle().x + c.getCircle().radius > Gdx.graphics.getWidth() + gap) ||
                    (c.getCircle().x < -gap) ) {
                c.setVelocityX( -c.getVelocity().x );
            }
            if( (c.getCircle().y + c.getCircle().radius > Gdx.graphics.getHeight() + gap) ||
                    (c.getCircle().y < -gap) ) {
                c.setVelocityY( -c.getVelocity().y );
            }


            c.getCircle().setX( c.getCircle().x + c.getVelocity().x * delta);
            c.getCircle().setY( c.getCircle().y + c.getVelocity().y * delta);

        }

    }

    public void draw() {

        sb.begin();

        //check if debris are even on screen before they get renderered

        for(DebrisCircle c : debrisCircles) {
            sb.setColor(sb.getColor().r, sb.getColor().g, sb.getColor().b, c.getOpacity());
            sb.draw(c.getTexture(), c.getCircle().x-c.getCircle().radius, c.getCircle().y-c.getCircle().radius, c.getCircle().radius*2, c.getCircle().radius*2);
            sb.setColor(sb.getColor().r, sb.getColor().g, sb.getColor().b, 1f);
        }

        for(DebrisRectangle r : debrisRectangles) {
            sb.setColor(sb.getColor().r, sb.getColor().g, sb.getColor().b, r.getOpacity());
            sb.draw(r.getTexture(), r.getRectangle().getX(), r.getRectangle().getY(), r.getRectangle().getWidth(), r.getRectangle().getHeight());
            sb.setColor(sb.getColor().r, sb.getColor().g, sb.getColor().b, 1f);
        }

        sb.end();
    }


    public ArrayList<DebrisCircle> getDebrisCircles() {
        return debrisCircles;
    }
    public ArrayList<DebrisRectangle> getDebrisRectangles() {
        return debrisRectangles;
    }

    @Override
    public void dispose() {
        for(DebrisCircle c : debrisCircles) {
            if(c.timerCleared) spawnTimer.clear();
            c.getTexture().dispose();
        }
        for(DebrisRectangle r : debrisRectangles) {
            if(r.timerCleared) spawnTimer.clear();
            r.getTexture().dispose();
        }

        if(!timerCleared){
            spawnTimer.clear();
        }

    }
}

