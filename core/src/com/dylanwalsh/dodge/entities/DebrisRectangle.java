package com.dylanwalsh.dodge.entities;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DebrisRectangle extends DebrisItem{

    //Shape
    private Rectangle rectangle;

    public DebrisRectangle(String src, Vector2 vel, Vector2 pos, float size) {
        super(src, vel);

        rectangle = new Rectangle();
        rectangle.setSize(size, size);
        rectangle.setPosition(pos); //rectangle bottom left
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

}
