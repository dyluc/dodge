package com.dylanwalsh.dodge.config;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LevelNumbers { //class to load number textures and return appropriate textures

    public static Texture numbersTexture; //0
    public static Texture numbersTimerTexture; //1
    public static Texture numbersRedTexture; //2
    public static TextureRegion[][] numbers;
    public static TextureRegion[][] numbersTimer;
    public static TextureRegion[][] numbersRed;
    public static final int tileWidth = 6;
    public static final int tileHeight = 10;

    public static void load() {

        numbersTexture = new Texture("ui/numbers.png");
        numbersTimerTexture = new Texture("ui/numbers_timer.png");
        numbersRedTexture = new Texture("ui/numbers_red.png");

        numbers = TextureRegion.split(numbersTexture, tileWidth, tileHeight);
        numbersTimer = TextureRegion.split(numbersTimerTexture, tileWidth, tileHeight);
        numbersRed = TextureRegion.split(numbersRedTexture, tileWidth, tileHeight);

    }
    public static TextureRegion[] getTextureRegions(String number, int type) {

        TextureRegion[] regions = new TextureRegion[number.length()];

        if(type==0) {
            for (int i = 0; i < number.length(); i++){
                char c = number.charAt(i);
                int digit = Character.getNumericValue(c);

                regions[i] = numbers[0][digit];
            }
        } else if(type == 1) {
            for (int i = 0; i < number.length(); i++){
                char c = number.charAt(i);
                int digit = Character.getNumericValue(c);

                regions[i] = numbersTimer[0][digit];
            }
        } else if(type== 2) {
            for (int i = 0; i < number.length(); i++){
                char c = number.charAt(i);
                int digit = Character.getNumericValue(c);

                regions[i] = numbersRed[0][digit];
            }
        }

        return regions;
    }

}
