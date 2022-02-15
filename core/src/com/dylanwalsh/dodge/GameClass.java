package com.dylanwalsh.dodge;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dylanwalsh.dodge.config.LevelConfig;
import com.dylanwalsh.dodge.config.LevelEight;
import com.dylanwalsh.dodge.config.LevelEleven;
import com.dylanwalsh.dodge.config.LevelFifteen;
import com.dylanwalsh.dodge.config.LevelFive;
import com.dylanwalsh.dodge.config.LevelFour;
import com.dylanwalsh.dodge.config.LevelFourteen;
import com.dylanwalsh.dodge.config.LevelNine;
import com.dylanwalsh.dodge.config.LevelNumbers;
import com.dylanwalsh.dodge.config.LevelOne;
import com.dylanwalsh.dodge.config.LevelSeven;
import com.dylanwalsh.dodge.config.LevelSix;
import com.dylanwalsh.dodge.config.LevelTen;
import com.dylanwalsh.dodge.config.LevelThirteen;
import com.dylanwalsh.dodge.config.LevelThree;
import com.dylanwalsh.dodge.config.LevelTwelve;
import com.dylanwalsh.dodge.config.LevelTwenty;
import com.dylanwalsh.dodge.config.LevelTwo;
import com.dylanwalsh.dodge.entities.Background;
import com.dylanwalsh.dodge.screens.GameOverScreen;
import com.dylanwalsh.dodge.screens.GameScreen;
import com.dylanwalsh.dodge.screens.LevelScreen;
import com.dylanwalsh.dodge.screens.MainMenu;
import com.dylanwalsh.dodge.screens.SurvivalScreen;

import java.util.ArrayList;

public class GameClass extends Game {

    //game hud
    //level number
    //level info - aliens number, debris, how long to last
    //timer counting down

    //game will have levels that count down how long you have to last. Once that timer reaches 0,
    //you unlock next level
    //can also have time trial; 'how long can you last' mode. This timer will count up until
    //you die and then save the time as your high score. E.G. 'You lasted 10:34. Congratulations, you
    //beat your high score!

    private Background background;
    private SpriteBatch backgroundBatch;

    public MainMenu mainMenu;
    public SurvivalScreen survivalScreen;

    public static SpriteBatch gameBatch; //check why static

    public Preferences completedLevels;
    public Preferences survivalHighscores;

    @Override
    public void create() {

        //load saved data
        completedLevels = Gdx.app.getPreferences("completed_levels");
        survivalHighscores = Gdx.app.getPreferences("survival_highscores");
        //completedLevels.putBoolean("1", true);
        //completedLevels.putBoolean("1", false);
        //completedLevels.putBoolean("2", false);
        //completedLevels.putBoolean("3", false); ...

        backgroundBatch = new SpriteBatch();
        gameBatch = new SpriteBatch();

        background = new Background();
        background.setSb(backgroundBatch);
        background.starsPerSpawn = 4;
        background.maxSize = Gdx.graphics.getWidth()/50f;
        background.minSize = Gdx.graphics.getWidth()/120f;
        background.maxSpeed = Gdx.graphics.getWidth()/10f;
        background.minSpeed =  Gdx.graphics.getWidth()/45f;


        LevelNumbers.load();
        mainMenu = new MainMenu(this);
        survivalScreen = new SurvivalScreen(this);

        //setScreen(gameOverScreen);
        setScreen(mainMenu);
        //setScreen(new GameScreen(this, new LevelOne()));

    }

    @Override public void resize(int width, int height) {  }

    @Override
    public void render() {
        if(getScreen().equals(mainMenu) || getScreen() instanceof LevelScreen || getScreen() instanceof GameOverScreen|| getScreen().equals(survivalScreen)) {
            if (background.isCreateStars() == false) background.setCreateStars(true);
            background.update(Gdx.graphics.getDeltaTime());
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            background.draw();
        } else{
            if (background.isCreateStars() == true) background.setCreateStars(false);
        }

        super.render();
    }

    @Override public void pause() {}

    @Override public void resume() {}

    @Override
    public void dispose() {
        LevelNumbers.numbersTexture.dispose();
        LevelNumbers.numbersTimerTexture.dispose();
        LevelNumbers.numbersRedTexture.dispose();
        background.dispose();

        //dispose isnt called in hide method of mainmenu or survival
        // to avoid calling it twice here and at super.dispose
        mainMenu.dispose();
        survivalScreen.dispose();
        //had to set input processor to null only on survival screen as every
        //screen that creates new GameScreens is disposed when the screen is changed and so cannot
        //continue being the games input processor

        gameBatch.dispose();
        super.dispose();
    }
}
