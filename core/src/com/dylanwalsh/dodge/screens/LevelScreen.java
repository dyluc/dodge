package com.dylanwalsh.dodge.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dylanwalsh.dodge.GameClass;
import com.dylanwalsh.dodge.config.LevelEight;
import com.dylanwalsh.dodge.config.LevelEighteen;
import com.dylanwalsh.dodge.config.LevelEleven;
import com.dylanwalsh.dodge.config.LevelFifteen;
import com.dylanwalsh.dodge.config.LevelFive;
import com.dylanwalsh.dodge.config.LevelFour;
import com.dylanwalsh.dodge.config.LevelFourteen;
import com.dylanwalsh.dodge.config.LevelNine;
import com.dylanwalsh.dodge.config.LevelNineteen;
import com.dylanwalsh.dodge.config.LevelNumbers;
import com.dylanwalsh.dodge.config.LevelOne;
import com.dylanwalsh.dodge.config.LevelSeven;
import com.dylanwalsh.dodge.config.LevelSeventeen;
import com.dylanwalsh.dodge.config.LevelSix;
import com.dylanwalsh.dodge.config.LevelSixteen;
import com.dylanwalsh.dodge.config.LevelTen;
import com.dylanwalsh.dodge.config.LevelThirteen;
import com.dylanwalsh.dodge.config.LevelThree;
import com.dylanwalsh.dodge.config.LevelTwelve;
import com.dylanwalsh.dodge.config.LevelTwenty;
import com.dylanwalsh.dodge.config.LevelTwo;
import com.dylanwalsh.dodge.entities.Background;
import com.sun.glass.ui.Window;

import java.util.ArrayList;


public class LevelScreen implements Screen{


    public Stage stage;

    private int levels;
    private float cellWidth;
    private float cellHeight;


    private Texture titleTexture;
    private Texture backTexture;

    public LevelScreen(final GameClass game) {
        stage = new Stage(new ScreenViewport());


        titleTexture = new Texture("ui/game_title.png");
        Image title = new Image(titleTexture);
        title.setSize(
                Gdx.graphics.getWidth()/1.25f,
                titleTexture.getHeight()*((Gdx.graphics.getWidth()/1.25f)/titleTexture.getWidth()));
        title.setPosition(Gdx.graphics.getWidth()/2f-(title.getWidth()/2f), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4f);

        backTexture = new Texture("ui/arrow.png");
        Image back = new Image(backTexture);
        back.setSize(
                Gdx.graphics.getWidth()/9f,
                backTexture.getHeight()*((Gdx.graphics.getWidth()/9f)/backTexture.getWidth()));
        back.setPosition(Gdx.graphics.getWidth()/2f-(back.getWidth()/2f), Gdx.graphics.getHeight()/15f);

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.mainMenu);
                Gdx.input.setInputProcessor(game.mainMenu.stage);
            }
        });


        stage.addActor(title);
        stage.addActor(back);

        levels = 20;
        cellWidth = Gdx.graphics.getWidth()/20f;
        cellHeight = LevelNumbers.tileHeight*(cellWidth / LevelNumbers.tileWidth);
        float currentY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()/2.5f);

        for(int i = 1; i <= levels; i++) {

            String number = Integer.toString(i);

            //check if number is in completed levels
            int type = 2; //assume incomplete
            boolean levelComplete =
                    game.completedLevels.getBoolean(number, false); //if the value isnt in preferences, then assume level isnt complete
            game.completedLevels.putBoolean(number, levelComplete);
            if(levelComplete)
                type = 0;
            //check if number is in locked levels

            game.completedLevels.flush();

            final int num = i;
            if(number.length() == 2) { //double digits

                TextureRegion[] digits = LevelNumbers.getTextureRegions(number, type);
                Image digitImageOne = new Image(digits[0]);
                Image digitImageTwo = new Image(digits[1]);
                digitImageOne.setSize(cellWidth, cellHeight);
                digitImageTwo.setSize(cellWidth, cellHeight);

                digitImageOne.setPosition((Gdx.graphics.getWidth()/5f)*(((i-1)%4)+1) - (cellWidth)-2f, currentY);
                digitImageTwo.setPosition((Gdx.graphics.getWidth()/5f)*(((i-1)%4)+1) +2f, currentY);

                ClickListener listener = new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        switch (num) {
                            case 10:
                                game.setScreen(new GameScreen(game, new LevelTen()));
                                break;
                            case 11:
                                game.setScreen(new GameScreen(game, new LevelEleven()));
                                break;
                            case 12:
                                game.setScreen(new GameScreen(game, new LevelTwelve()));
                                break;
                            case 13:
                                game.setScreen(new GameScreen(game, new LevelThirteen()));
                                break;
                            case 14:
                                game.setScreen(new GameScreen(game, new LevelFourteen()));
                                break;
                            case 15:
                                game.setScreen(new GameScreen(game, new LevelFifteen()));
                                break;
                            case 16:
                                game.setScreen(new GameScreen(game, new LevelSixteen()));
                                break;
                            case 17:
                                game.setScreen(new GameScreen(game, new LevelSeventeen()));
                                break;
                            case 18:
                                game.setScreen(new GameScreen(game, new LevelEighteen()));
                                break;
                            case 19:
                                game.setScreen(new GameScreen(game, new LevelNineteen()));
                                break;
                            case 20:
                                game.setScreen(new GameScreen(game, new LevelTwenty()));
                                break;
                        }
                    }
                };

                digitImageOne.addListener(listener);
                digitImageTwo.addListener(listener);

                stage.addActor(digitImageOne);
                stage.addActor(digitImageTwo);


            } else { //single digit number

                Image digitImage = new Image(LevelNumbers.getTextureRegions(number, type)[0]);
                digitImage.setSize(cellWidth, cellHeight);
                digitImage.setPosition((Gdx.graphics.getWidth()/5f)*(((i-1)%4)+1) - cellWidth/2f, currentY);

                digitImage.addListener(new ClickListener() {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        switch(num) {
                            case 1:
                                game.setScreen(new GameScreen(game, new LevelOne()));
                                break;
                            case 2:
                                game.setScreen(new GameScreen(game, new LevelTwo()));
                                break;
                            case 3:
                                game.setScreen(new GameScreen(game, new LevelThree()));
                                break;
                            case 4:
                                game.setScreen(new GameScreen(game, new LevelFour()));
                                break;
                            case 5:
                                game.setScreen(new GameScreen(game, new LevelFive()));
                                break;
                            case 6:
                                game.setScreen(new GameScreen(game, new LevelSix()));
                                break;
                            case 7:
                                game.setScreen(new GameScreen(game, new LevelSeven()));
                                break;
                            case 8:
                                game.setScreen(new GameScreen(game, new LevelEight()));
                                break;
                            case 9:
                                game.setScreen(new GameScreen(game, new LevelNine()));
                                break;
                        }

                    }

                });

                stage.addActor(digitImage);
            }



            //size: 30 x 50

            if(i % 4 == 0) {
                currentY -= Gdx.graphics.getHeight()/10f;
            }

        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("LevelScreen Disposed\n");
        titleTexture.dispose();
        backTexture.dispose();
        stage.dispose();
    }
}
