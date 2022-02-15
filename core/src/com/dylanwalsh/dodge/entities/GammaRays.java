package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Random;

public class GammaRays implements Disposable{


    private SpriteBatch sb;

    //

    private ArrayList<GammaRay> gammaRays;
    private ArrayList<GammaRay> gammaRaysToRemove;
    private String textureSource;

    private Timer spawnTimer;
    private Timer.Task spawnTask;

    //config//
    public static int spawnRate;
    public static int spawnDelay;
    public static float maxAmplitude;
    public static float minAmplitude;
    public static float maxWaveLength;
    public static float minWaveLength;
    public static float maxSize;
    public static float minSize;
    public static float maxStartY;
    public static float minStartY;
    public static float gammaSpeed;
    public static int maxGammaLength;
    public static int minGammaLength;
    //


    public GammaRays(SpriteBatch sb) {
        this.sb = sb;
        textureSource = "green.png";



        gammaRays = new ArrayList<GammaRay>();
        gammaRaysToRemove = new ArrayList<GammaRay>();

        spawnTimer = new Timer();
        spawnTask = new Timer.Task() {

            @Override
            public void run() {
                float a = new Random().nextFloat()*(maxSize-minSize)+minSize;
                float b = new Random().nextFloat()*(maxAmplitude-minAmplitude)+minAmplitude;
                float c = new Random().nextFloat()*(maxWaveLength-minWaveLength)+minWaveLength;
                float d = new Random().nextFloat()*(maxStartY-minStartY)+minStartY;
                int gammaLength = new Random().nextInt(maxGammaLength - minGammaLength + 1) + minGammaLength;

                gammaRays.add(new GammaRay(textureSource, a, b, c, d, gammaLength));
                gammaRays.add(new GammaRay(textureSource, a, b, c, d-Gdx.graphics.getWidth()/20f, gammaLength));
            }

        };

        spawnTimer.scheduleTask(spawnTask, spawnDelay, spawnRate);


    }

    public void update(float delta) {
        for(GammaRay gammaRay : gammaRays) {

            //y = a sin bx
            for(int i = 0; i < gammaRay.getPositions().length; i++) {

                gammaRay.getPositions()[i].set( gammaRay.getPositions()[i].x + gammaSpeed * delta,
                        gammaRay.getStartY() +
                                ( gammaRay.getAmplitude()*(float)Math.sin(gammaRay.getWaveLength()*gammaRay.getPositions()[i].x) ) );

                if(i == gammaRay.getPositions().length-1) { //last item

                    if(gammaRay.getPositions()[i].x > Gdx.graphics.getWidth()) {
                        gammaRaysToRemove.add(gammaRay);
                    }
                }
            }

        }
        for(GammaRay gammaRay : gammaRaysToRemove) {
            gammaRays.remove(gammaRay);
        }
        gammaRaysToRemove.clear();
    }

    public void draw() {

        sb.begin();

        for(GammaRay gammaRay : gammaRays) {

            for(Vector2 pos : gammaRay.getPositions()) {
                sb.draw(gammaRay.getTexture(), pos.x, pos.y,
                        gammaRay.getSize(), gammaRay.getSize());
            }



        }

        sb.end();

    }

    public ArrayList<GammaRay> getGammaRays() {
        return gammaRays;
    }

    @Override
    public void dispose() {

        spawnTimer.clear();
        for(GammaRay gammaRay : gammaRays) {
            gammaRay.dispose();
        }

    }
}
