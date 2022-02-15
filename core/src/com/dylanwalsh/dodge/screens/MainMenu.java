package com.dylanwalsh.dodge.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dylanwalsh.dodge.GameClass;
import com.dylanwalsh.dodge.config.LevelOne;
import com.dylanwalsh.dodge.config.LevelTwo;
import com.dylanwalsh.dodge.entities.Background;


public class MainMenu implements Screen {

    private GameClass game;

    public Stage stage;

    private Texture titleTexture;
    private Texture levelsTexture;
    private Texture survivalTexture;


    public MainMenu(final GameClass game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        titleTexture = new Texture("ui/game_title.png");
        levelsTexture = new Texture("ui/levels.png");
        survivalTexture = new Texture("ui/survival.png");

        Image title = new Image(titleTexture);
        title.setSize(
                Gdx.graphics.getWidth()/1.25f,
                titleTexture.getHeight()*((Gdx.graphics.getWidth()/1.25f)/titleTexture.getWidth()));
        title.setPosition(Gdx.graphics.getWidth()/2f-(title.getWidth()/2f), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4f);
        Image levels = new Image(levelsTexture);
        Image survival = new Image(survivalTexture);

        stage.addActor(title);


        levels.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                LevelScreen newLvlScreen = new LevelScreen(game);
                Gdx.input.setInputProcessor(newLvlScreen.stage);
                game.setScreen(newLvlScreen);

            }
        });

        survival.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Gdx.input.setInputProcessor(game.survivalScreen.stage);
                game.setScreen(game.survivalScreen);

            }
        });

        Table table = new Table();
        table.center().padTop(Gdx.graphics.getHeight()/5f);
        table.setFillParent(true);

        table.add(levels).expandX()
                .width(Gdx.graphics.getWidth()/2f)
                .height(levelsTexture.getHeight()*((Gdx.graphics.getWidth()/2f)/levelsTexture.getWidth()))
                .padBottom(Gdx.graphics.getHeight()/15f);
        table.row();
        table.add(survival).expandX()
                .width(Gdx.graphics.getWidth()/1.5f)
                .height(survivalTexture.getHeight()*((Gdx.graphics.getWidth()/1.5f)/survivalTexture.getWidth()));

        stage.addActor(table);

    }

    @Override
    public void show() {}

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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        System.out.println("MainMenu Disposed\n");
        //textures
        titleTexture.dispose();
        levelsTexture.dispose();
        survivalTexture.dispose();

        stage.dispose();
    }
}
