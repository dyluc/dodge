package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;


public class GammaRay implements Disposable{

    private Texture texture;
    private float size;
    private float startY;

    private float amplitude;
    private float waveLength;
    private Vector2[] positions;



    public GammaRay(String source, float size, float amplitude, float waveLength, float startY, int length) {
        texture = new Texture(source);

        this.size = size;
        this.startY = startY;


        positions = new Vector2[length];
        for(int i = 0; i < positions.length; i++) {
            positions[i] = new Vector2();
            positions[i].set(-size-(i*Gdx.graphics.getWidth()/200f), this.startY);
        }

        this.amplitude = amplitude;
        this.waveLength = waveLength;

    }

    public Vector2[] getPositions() { return positions; }

    public Texture getTexture() { return texture; }
    public float getSize() { return size; }
    public float getAmplitude() { return amplitude; }
    public float getWaveLength() { return waveLength; }
    public float getStartY() {
        return startY;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
