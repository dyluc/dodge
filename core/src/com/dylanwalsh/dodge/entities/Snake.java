package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.dylanwalsh.dodge.screens.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Snake implements Disposable {

    private SpriteBatch sb;
    //

    private ArrayList<String> sources;
    private Texture[] textures;
    private Circle[] circles;
    private float speed; //smaller -> faster
    private float size;
    private int circleCount = 6;


    public Snake(SpriteBatch sb) {
        this.sb = sb;
        size = Gdx.graphics.getWidth()/15f;

        sources = new ArrayList<String>(); //length should be same size as circleCount
        sources.add("snake/circle_one.png");
        sources.add("snake/circle_two.png");
        sources.add("snake/circle_three.png");
        sources.add("snake/circle_four.png");
        sources.add("snake/circle_five.png");
        sources.add("snake/circle_six.png");


        textures = new Texture[sources.size()];
        for(int i = 0; i < textures.length; i++) {

            int index = new Random().nextInt((sources.size()-1) - 0 + 1) + 0;
            textures[i] = new Texture( sources.get(index) );
            sources.remove(index);

        }


        circles = new Circle[circleCount];
        for(int i = 0; i < circles.length; i++) {
            circles[i] = new Circle();
            circles[i].setPosition(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
            circles[i].setRadius((size/2f)); //radius is half of size
        }
        speed = 1/10f;

    }

    public void update(float delta)  {
        for(int i = 0; i < circles.length; i++) {

            if(i == 0) {

                if(Gdx.input.isTouched()){


                    circles[i].x += ((Gdx.input.getX() - circles[i].x) / speed) * delta;
                    circles[i].y += (((Gdx.graphics.getHeight() - Gdx.input.getY()) - circles[i].y) / speed) * delta;

                }

            } else {
                circles[i].x += (((circles[i-1].x - circles[i].x) / speed) * delta);
                circles[i].y += (((circles[i-1].y - circles[i].y) / speed) * delta);
            }

        }


    }

    public void draw() {
        sb.begin();


        for(Circle circle : circles) {
            sb.draw(textures[ Arrays.asList(circles).indexOf(circle) ], circle.x-circle.radius, circle.y-circle.radius, circle.radius*2, circle.radius*2);
        }

        sb.end();

    }

    public Circle[] getCircles() {
        return circles;
    }

    @Override
    public void dispose() {
        for(Texture texture : textures) { texture.dispose(); }
    }

    ////Getters and Setters////

}
