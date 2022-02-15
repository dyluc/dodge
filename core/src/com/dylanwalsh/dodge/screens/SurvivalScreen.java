package com.dylanwalsh.dodge.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dylanwalsh.dodge.GameClass;
import com.dylanwalsh.dodge.config.LevelEighteen;
import com.dylanwalsh.dodge.config.LevelFive;
import com.dylanwalsh.dodge.config.LevelTen;
import com.dylanwalsh.dodge.config.LevelTwelve;

public class SurvivalScreen implements Screen {

    public Stage stage;

    private Texture easyTexture;
    private Texture fairTexture;
    private Texture hardTexture;
    private Texture backTexture;
    private Texture titleTexture;

    public SurvivalScreen(final GameClass game) {
        stage = new Stage();

        easyTexture = new Texture("ui/easy.png");
        fairTexture = new Texture("ui/fair.png");
        hardTexture = new Texture("ui/hard.png");
        backTexture = new Texture("ui/arrow.png");
        titleTexture = new Texture("ui/game_title.png");

        Image title = new Image(titleTexture);
        title.setSize(
                Gdx.graphics.getWidth()/1.25f,
                titleTexture.getHeight()*((Gdx.graphics.getWidth()/1.25f)/titleTexture.getWidth()));
        title.setPosition(Gdx.graphics.getWidth()/2f-(title.getWidth()/2f), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4f);

        stage.addActor(title);

        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);

        Image easy = new Image(easyTexture);
        Image fair = new Image(fairTexture);
        Image hard = new Image(hardTexture);


        easy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, 0));
                Gdx.input.setInputProcessor(null);
            }
        });

        fair.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, 1));
                Gdx.input.setInputProcessor(null);
            }
        });

        hard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, 2));
                Gdx.input.setInputProcessor(null);
            }
        });


        table.add(easy)
                .width(Gdx.graphics.getWidth()/3.5f)
                .height(easyTexture.getHeight()*((Gdx.graphics.getWidth()/3.5f)/easyTexture.getWidth()))
                .pad(Gdx.graphics.getHeight()/20f, 0, 0, Gdx.graphics.getWidth()/20f);
        table.add(fair)
                .width(Gdx.graphics.getWidth()/3.5f)
                .height(fairTexture.getHeight()*((Gdx.graphics.getWidth()/3.5f)/fairTexture.getWidth()))
                .pad(Gdx.graphics.getHeight()/20f, 0, 0, Gdx.graphics.getWidth()/20f);
        table.add(hard)
                .width(Gdx.graphics.getWidth()/3.5f)
                .height(hardTexture.getHeight()*((Gdx.graphics.getWidth()/3.5f)/hardTexture.getWidth()))
                .pad(Gdx.graphics.getHeight()/20f, 0, 0, 0);

        stage.addActor(table);

        Image back = new Image(backTexture);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.mainMenu);
                Gdx.input.setInputProcessor(game.mainMenu.stage);
            }
        });
        back.setWidth(Gdx.graphics.getWidth()/9f);
        back.setHeight(backTexture.getHeight()*((Gdx.graphics.getWidth()/9f)/backTexture.getWidth()));
        back.setPosition(Gdx.graphics.getWidth()/2f - (back.getWidth()/2f), Gdx.graphics.getHeight()/20f);

        stage.addActor(back);
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

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        System.out.println("SurvivalScreen disposed");

        easyTexture.dispose();
        fairTexture.dispose();
        hardTexture.dispose();
        backTexture.dispose();
        titleTexture.dispose();

        stage.dispose();

    }
}
