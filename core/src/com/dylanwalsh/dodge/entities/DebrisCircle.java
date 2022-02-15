package com.dylanwalsh.dodge.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;



public class DebrisCircle extends DebrisItem {

    //Shape
    private Circle circle;

    public DebrisCircle(String src, Vector2 vel, Vector2 pos, float radius) {
        super(src, vel);

        circle = new Circle();
        circle.setRadius(radius);
        circle.setPosition(pos); //circle centre

    }

    public Circle getCircle() {
        return circle;
    }
}
