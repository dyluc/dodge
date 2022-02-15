package com.dylanwalsh.dodge.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dylanwalsh.dodge.GameClass;
import com.dylanwalsh.dodge.config.LevelConfig;
import com.dylanwalsh.dodge.config.LevelNumbers;

public class GameOverScreen implements Screen { //game over screen for both levels and survival

    public Stage stage;

    private GameClass game;
    private boolean isSurvival;
    private int mode;

    private Texture gameOvertexture;
    private Texture timeTexture;
    private Texture highscoreTexture;

    private Texture newTexture;

    private Texture levelsTexture;
    private Texture replayTexture;
    private Texture homeTexture;
    private Texture colonTexture;

    public GameOverScreen(GameClass game, boolean isSurvival, int mode) { //value doesnt matter if isSurvival is false
        this.game = game;
        this.isSurvival = isSurvival;
        if(isSurvival)
            this.mode = mode;
        stage = new Stage();

        gameOvertexture = new Texture("ui/game_over.png");
        timeTexture = new Texture("ui/time.png");
        colonTexture = new Texture("ui/colon.png");
        if(isSurvival)
            highscoreTexture = new Texture("ui/highscore.png");
        levelsTexture = new Texture("ui/levels.png");
        replayTexture = new Texture("ui/replay.png");
        homeTexture = new Texture("ui/home.png");



    }

    public void createTable(int timeScore, final LevelConfig levelConfig) {//score
        final Table table = new Table();
        table.top();
        table.setFillParent(true);
        //table.setDebug(true);

        final Table timeTable = new Table();
        final Table highscoreTable = new Table();

        //============================================
        Image gameOver = new Image(gameOvertexture);
        Image time = new Image(timeTexture);
        Image levels = new Image(levelsTexture);
        levels.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                timeTable.clearChildren();
                highscoreTable.clearChildren();
                table.clearChildren();

                LevelScreen newLvlScreen = new LevelScreen(game);
                Gdx.input.setInputProcessor(newLvlScreen.stage);
                game.setScreen(newLvlScreen);
            }
        });
        //============================================

        //============================================
        String number = Integer.toString(timeScore);
        while(number.length() != 5) { //keep adding 0 to start of number
            number = "0"+number;
        }

        TextureRegion[] digitImages = LevelNumbers.getTextureRegions(number, 1);

        Image colon = new Image(colonTexture);
        Image colonTwo = new Image(colonTexture);
        Image digitOne = new Image(digitImages[0]);
        Image digitTwo = new Image(digitImages[1]);
        Image digitThree = new Image(digitImages[2]);
        Image digitFour = new Image(digitImages[3]);
        Image digitFive = new Image(digitImages[4]);
        //============================================

        //============================================

        int highscoreTime = 0;
        if(isSurvival) {
            switch(mode) {
                case 0:
                    int easy = game.survivalHighscores.getInteger("easy", 0);
                    if(timeScore > easy) {
                        game.survivalHighscores.putInteger("easy", timeScore);

                        newTexture = new Texture("ui/new.png");
                    }
                    game.survivalHighscores.flush();
                    highscoreTime = game.survivalHighscores.getInteger("easy");
                    break;
                case 1:
                    int fair = game.survivalHighscores.getInteger("fair", 0);
                    if(timeScore > fair) {
                        game.survivalHighscores.putInteger("fair", timeScore);

                        newTexture = new Texture("ui/new.png");
                    }
                    game.survivalHighscores.flush();
                    highscoreTime = game.survivalHighscores.getInteger("fair");
                    break;
                case 2:
                    int hard = game.survivalHighscores.getInteger("hard", 0);
                    if(timeScore > hard) {
                        game.survivalHighscores.putInteger("hard", timeScore);

                        newTexture = new Texture("ui/new.png");
                    }
                    game.survivalHighscores.flush();
                    highscoreTime = game.survivalHighscores.getInteger("hard");
                    break;
            }
        }


        String highscoreNumber = Integer.toString(highscoreTime);
        while(highscoreNumber.length() != 5) { //keep adding 0 to start of number
            highscoreNumber = "0"+highscoreNumber;
        }

        TextureRegion[] highscoreDigitImages = LevelNumbers.getTextureRegions(highscoreNumber, 1);

        Image highscoreColon = new Image(colonTexture);
        Image highscoreColonTwo = new Image(colonTexture);
        Image highscoreDigitOne = new Image(highscoreDigitImages[0]);
        Image highscoreDigitTwo = new Image(highscoreDigitImages[1]);
        Image highscoreDigitThree = new Image(highscoreDigitImages[2]);
        Image highscoreDigitFour = new Image(highscoreDigitImages[3]);
        Image highscoreDigitFive = new Image(highscoreDigitImages[4]);
        //============================================


        table.add(gameOver)//============================================
                .width(Gdx.graphics.getWidth()/1.25f)
                .height(gameOver.getHeight()*((Gdx.graphics.getWidth()/1.25f)/gameOver.getWidth()))
                .pad(Gdx.graphics.getHeight()/15f, 0, Gdx.graphics.getHeight()/8f, 0);
        table.row();//============================================

        table.add(time)//============================================
                .width(Gdx.graphics.getWidth()/2.75f)
                .height(time.getHeight()*((Gdx.graphics.getWidth()/2.75f)/time.getWidth()))
                .pad(0, 0, Gdx.graphics.getHeight()/30f, 0);
        table.row();//============================================

        if(isSurvival) {
            timeTable.add(digitOne)//============================================
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(digitImages[0].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/digitImages[0].getRegionWidth()))
                    .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
            timeTable.add(colon)
                    .width(Gdx.graphics.getWidth()/50f)
                    .height(colon.getHeight()*((Gdx.graphics.getWidth()/50f)/colon.getWidth()))
                    .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
            timeTable.add(digitTwo)
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(digitImages[1].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/digitImages[1].getRegionWidth()))
                    .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
        }
        timeTable.add(digitThree)
            .width(Gdx.graphics.getWidth()/20f)
            .height(digitImages[2].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/digitImages[2].getRegionWidth()))
            .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
        timeTable.add(colonTwo)
                .width(Gdx.graphics.getWidth()/50f)
                .height(colonTwo.getHeight()*((Gdx.graphics.getWidth()/50f)/colonTwo.getWidth()))
                .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
        timeTable.add(digitFour)
                .width(Gdx.graphics.getWidth()/20f)
                .height(digitImages[3].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/digitImages[3].getRegionWidth()))
                .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
        timeTable.add(digitFive)
                .width(Gdx.graphics.getWidth()/20f)
                .height(digitImages[4].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/digitImages[4].getRegionWidth()));
        table.add(timeTable)
            .pad(0, 0, isSurvival?Gdx.graphics.getHeight()/30f:Gdx.graphics.getHeight()/20f, 0);
        table.row();//============================================

        if(isSurvival) {//============================================
            Image highscore = new Image(highscoreTexture);
            table.add(highscore)
                    .width(Gdx.graphics.getWidth()/1.75f)
                    .height(highscore.getHeight()*((Gdx.graphics.getWidth()/1.75f)/highscore.getWidth()))
                    .pad(0, 0, Gdx.graphics.getHeight()/30f, 0);

            table.row();


            highscoreTable.add(highscoreDigitOne)//============================================
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(highscoreDigitImages[0].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/highscoreDigitImages[0].getRegionWidth()))
                    .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
            highscoreTable.add(highscoreColon)
                    .width(Gdx.graphics.getWidth()/50f)
                    .height(highscoreColon.getHeight()*((Gdx.graphics.getWidth()/50f)/highscoreColon.getWidth()))
                    .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
            highscoreTable.add(highscoreDigitTwo)
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(highscoreDigitImages[1].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/highscoreDigitImages[1].getRegionWidth()))
                    .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
            highscoreTable.add(highscoreDigitThree)
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(highscoreDigitImages[2].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/highscoreDigitImages[2].getRegionWidth()))
                    .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
            highscoreTable.add(highscoreColonTwo)
                    .width(Gdx.graphics.getWidth()/50f)
                    .height(highscoreColonTwo.getHeight()*((Gdx.graphics.getWidth()/50f)/highscoreColonTwo.getWidth()))
                    .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
            highscoreTable.add(highscoreDigitFour)
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(highscoreDigitImages[3].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/highscoreDigitImages[3].getRegionWidth()))
                    .pad(0, 0, 0, Gdx.graphics.getWidth()/100f);
            highscoreTable.add(highscoreDigitFive)
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(highscoreDigitImages[4].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/highscoreDigitImages[4].getRegionWidth()));

            table.add(highscoreTable)
                    .pad(0, 0, Gdx.graphics.getHeight()/30f, 0);
            table.row();//============================================

            if(newTexture != null) { //new highscore

                Image newHighscore = new Image(newTexture);
                newHighscore.setRotation(45);
                table.add(newHighscore)
                        .width(Gdx.graphics.getWidth()/9f)
                        .height(newHighscore.getHeight()*((Gdx.graphics.getWidth()/9f)/newHighscore.getWidth()))
                        .pad(-Gdx.graphics.getHeight()/25f, Gdx.graphics.getWidth()/2.2f, 0, 0);

                table.row();
            }

        }//============================================

        table.add(levels)//============================================
                .width(Gdx.graphics.getWidth()/2.25f)
                .height(levels.getHeight()*((Gdx.graphics.getWidth()/2.25f)/levels.getWidth()))
                .pad(0, 0, 0, 0);
        table.row();//============================================

        Image replay = new Image(replayTexture);//============================================
        replay.setSize(
                Gdx.graphics.getWidth()/8f,
                replay.getHeight()*((Gdx.graphics.getWidth()/8f)/replay.getWidth()));
        replay.setPosition(
                ((Gdx.graphics.getWidth()/4f)) - ((replay.getWidth())/2f),
                Gdx.graphics.getHeight()/15f);
        replay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                timeTable.clearChildren();
                highscoreTable.clearChildren();
                table.clearChildren();

                if(isSurvival) {//survival
                    game.setScreen(new GameScreen(game, mode));
                }else { //levels
                    game.setScreen(new GameScreen(game, levelConfig));
                }
            }
        });//==================================

        Image home = new Image(homeTexture);//==============================
        home.setSize(
                Gdx.graphics.getWidth()/8f,
                home.getHeight()*((Gdx.graphics.getWidth()/8f)/home.getWidth()));
        home.setPosition(
                ((Gdx.graphics.getWidth()/4f)*3f) - ((home.getWidth())/2f),
                Gdx.graphics.getHeight()/15f);
        home.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                timeTable.clearChildren();
                highscoreTable.clearChildren();
                table.clearChildren();
                game.setScreen(game.mainMenu);
                Gdx.input.setInputProcessor(game.mainMenu.stage);
            }
        });

        //============================================

        stage.addActor(replay);
        stage.addActor(home);
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
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {dispose();}

    @Override
    public void dispose() {
        System.out.println("GameOverScreen Disposed\n");

        gameOvertexture.dispose();
        timeTexture.dispose();
        colonTexture.dispose();
        if(highscoreTexture != null) {
            highscoreTexture.dispose();
        }
        if(newTexture != null) {
            newTexture.dispose();
        }
        levelsTexture.dispose();
        replayTexture.dispose();
        homeTexture.dispose();

        stage.dispose();
    }
}
