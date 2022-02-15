package com.dylanwalsh.dodge.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.dylanwalsh.dodge.config.LevelNumbers;

public class Hud implements Disposable {

    public boolean isSurvival;

    //both levels and survival ---------------------
    private Stage stage;
    private Texture colonTexture;
    //timer - use same timer for both but count up for survival
    private Timer timer;
    private Timer.Task timerTask;



    //levels ---------------------
    private Texture levelTexture;
    private int countDown;

    //survival ---------------------
    private Texture easyTexture;
    private Texture fairTexture;
    private Texture hardTexture;
    private int score;
    private int mode;





    public Hud(int levelNumber, int levelTime, int levelTimeDelay) { //levels

        isSurvival = false;

        stage = new Stage();

        final Table table = new Table();
        table.top();
        table.setFillParent(true);
        //table.setDebug(true);

        levelTexture = new Texture("ui/level.png");
        Image level = new Image(levelTexture);
        table.add(level).pad(Gdx.graphics.getHeight()/30f, 0, 0, Gdx.graphics.getWidth()/15f)
                .width(Gdx.graphics.getWidth()/4f)
                .height(levelTexture.getHeight()*((Gdx.graphics.getWidth()/4f)/levelTexture.getWidth()));

        String levelNumberString = Integer.toString(levelNumber);

        TextureRegion[] levelDigits = LevelNumbers.getTextureRegions(levelNumberString, 0);

        if(levelNumberString.length() == 2) {
            Image levelDigitOne = new Image(levelDigits[0]);
            Image levelDigitTwo = new Image(levelDigits[1]);

            table.add(levelDigitOne).pad(Gdx.graphics.getHeight()/30f, 0, 0, Gdx.graphics.getWidth()/120f)
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(levelDigits[0].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/levelDigits[0].getRegionWidth()));
            table.add(levelDigitTwo).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, 0)
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(levelDigits[1].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/levelDigits[1].getRegionWidth()));
        } else {
            Image levelDigitOne = new Image(levelDigits[0]);
            table.add(levelDigitOne).pad(Gdx.graphics.getHeight()/30f, 0, 0, 0)
                    .width(Gdx.graphics.getWidth()/20f)
                    .height(levelDigits[0].getRegionHeight()*((Gdx.graphics.getWidth()/20f)/levelDigits[0].getRegionWidth()));
        }

        //timer - 1:00 - []:[][]
        colonTexture = new Texture("ui/colon.png");
        countDown = levelTime+1; //add one so that initial value for levelTime can be displayed aswell
        timer = new Timer();
        timerTask = new Timer.Task(){
            Cell cellOne = table.add();
            Cell cellTwo = table.add();
            Cell cellThree = table.add();
            Cell cellFour = table.add();
            @Override
            public void run() {
                if(countDown > 0) {
                    if(countDown%100==0) {
                        countDown -= 41; //get to 59
                    } else {
                        countDown -= 1;
                    }
                }//exits at 0

                String number = Integer.toString(countDown);
                while(number.length() != 3) { //keep adding 0 to start of number

                    number = "0"+number;

                }

                TextureRegion[] digitImages = LevelNumbers.getTextureRegions( number, 1 );

                Image colon = new Image(colonTexture);
                Image digitOne = new Image(digitImages[0]);
                Image digitTwo = new Image(digitImages[1]);
                Image digitThree = new Image(digitImages[2]);

                cellOne.setActor(digitOne).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/30f, 0, Gdx.graphics.getWidth()/120f)
                        .width(Gdx.graphics.getWidth()/25f)
                        .height(digitImages[0].getRegionHeight()*((Gdx.graphics.getWidth()/25f)/digitImages[0].getRegionWidth()));
                cellTwo.setActor(colon).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, Gdx.graphics.getWidth()/120f)
                        .width(Gdx.graphics.getWidth()/50f)
                        .height(colonTexture.getHeight()*((Gdx.graphics.getWidth()/50f)/colonTexture.getWidth()));
                cellThree.setActor(digitTwo).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, Gdx.graphics.getWidth()/120f)
                        .width(Gdx.graphics.getWidth()/25f)
                        .height(digitImages[1].getRegionHeight()*((Gdx.graphics.getWidth()/25f)/digitImages[1].getRegionWidth()));
                cellFour.setActor(digitThree).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, 0)
                        .width(Gdx.graphics.getWidth()/25f)
                        .height(digitImages[2].getRegionHeight()*((Gdx.graphics.getWidth()/25f)/digitImages[2].getRegionWidth()));


            }


        };
        timer.scheduleTask(timerTask, levelTimeDelay, 1);


        stage.addActor(table);

    }

    public Hud(int gameDelay, int mode) { //survival
        isSurvival = true;
        stage = new Stage();

        final Table table = new Table();
        table.top();
        table.setFillParent(true);

        this.mode = mode;
        switch(mode) {
            case 0: //easy
                easyTexture = new Texture("ui/easy.png");
                Image easy = new Image(easyTexture);
                table.add(easy)
                        .width(Gdx.graphics.getWidth()/8f)
                        .height(easyTexture.getHeight()*((Gdx.graphics.getWidth()/8f)/easyTexture.getWidth()))
                        .pad(Gdx.graphics.getHeight()/30f, 0, 0, 0);
                break;
            case 1: // fair
                fairTexture = new Texture("ui/fair.png");
                Image fair = new Image(fairTexture);
                table.add(fair)
                        .width(Gdx.graphics.getWidth()/8f)
                        .height(fairTexture.getHeight()*((Gdx.graphics.getWidth()/8f)/fairTexture.getWidth()))
                        .pad(Gdx.graphics.getHeight()/30f, 0, 0, 0);
                break;
            case 2: //hard
                hardTexture = new Texture("ui/hard.png");
                Image hard = new Image(hardTexture);
                table.add(hard)
                        .width(Gdx.graphics.getWidth()/8f)
                        .height(hardTexture.getHeight()*((Gdx.graphics.getWidth()/8f)/hardTexture.getWidth()))
                        .pad(Gdx.graphics.getHeight()/30f, 0, 0, 0);
                break;
        }

        //timer
        colonTexture = new Texture("ui/colon.png");
        score = 0;
        timer = new Timer();
        timerTask = new Timer.Task() { //0:00:00
            Cell cellOne = table.add(); //0
            Cell cellTwo = table.add(); //:
            Cell cellThree = table.add(); //0
            Cell cellFour = table.add(); //0
            Cell cellFive = table.add(); //:
            Cell cellSix = table.add(); //0
            Cell cellSeven = table.add(); //0

            @Override
            public void run() {
                String scoreString = Integer.toString(score);
                while (scoreString.length() != 5) { //keep adding 0 to start of number
                    scoreString = "0" + scoreString;
                }

                String lastTwoDigits = scoreString.substring(scoreString.length() - 2);
                String middleTwoDigits = scoreString.substring(1, 3);

                if (lastTwoDigits.equals("59")) {
                    if(middleTwoDigits.equals("59")) {
                        score += 4041;
                    } else {
                        score += 41;
                    }
                } else {
                    score++;
                }

                scoreString = Integer.toString(score);
                while (scoreString.length() != 5) { //keep adding 0 to start of number
                    scoreString = "0" + scoreString;
                }


                TextureRegion[] digitImages = LevelNumbers.getTextureRegions(scoreString, 1);

                Image colon = new Image(colonTexture);
                Image colonTwo = new Image(colonTexture);
                Image digitOne = new Image(digitImages[0]);
                Image digitTwo = new Image(digitImages[1]);
                Image digitThree = new Image(digitImages[2]);
                Image digitFour = new Image(digitImages[3]);
                Image digitFive = new Image(digitImages[4]);

                //=== 0
                cellOne.setActor(digitOne).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/30f, 0, Gdx.graphics.getWidth()/120f)
                        .width(Gdx.graphics.getWidth() / 25f)
                        .height(digitImages[0].getRegionHeight() * ((Gdx.graphics.getWidth() / 25f) / digitImages[0].getRegionWidth()));
                //===

                //=== :
                cellTwo.setActor(colon).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, Gdx.graphics.getWidth()/120f)
                        .width(Gdx.graphics.getWidth() / 50f)
                        .height(colonTexture.getHeight() * ((Gdx.graphics.getWidth() / 50f) / colonTexture.getWidth()));
                //===

                //=== 0 0
                cellThree.setActor(digitTwo).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, Gdx.graphics.getWidth()/120f)
                        .width(Gdx.graphics.getWidth() / 25f)
                        .height(digitImages[1].getRegionHeight() * ((Gdx.graphics.getWidth() / 25f) / digitImages[1].getRegionWidth()));
                cellFour.setActor(digitThree).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, Gdx.graphics.getWidth()/120f)
                        .width(Gdx.graphics.getWidth() / 25f)
                        .height(digitImages[2].getRegionHeight() * ((Gdx.graphics.getWidth() / 25f) / digitImages[2].getRegionWidth()));
                //===

                //=== :
                cellFive.setActor(colonTwo).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, Gdx.graphics.getWidth()/120f)
                        .width(Gdx.graphics.getWidth() / 50f)
                        .height(colonTexture.getHeight() * ((Gdx.graphics.getWidth() / 50f) / colonTexture.getWidth()));
                //===

                //=== 0 0
                cellSix.setActor(digitFour).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, Gdx.graphics.getWidth()/120f)
                        .width(Gdx.graphics.getWidth() / 25f)
                        .height(digitImages[3].getRegionHeight() * ((Gdx.graphics.getWidth() / 25f) / digitImages[3].getRegionWidth()));
                cellSeven.setActor(digitFive).pad(Gdx.graphics.getHeight()/30f, Gdx.graphics.getWidth()/120f, 0, 0)
                        .width(Gdx.graphics.getWidth() / 25f)
                        .height(digitImages[4].getRegionHeight() * ((Gdx.graphics.getWidth() / 25f) / digitImages[4].getRegionWidth()));
                //===
            }

        };

        timer.scheduleTask(timerTask, gameDelay, 1);

        stage.addActor(table);

    }



    public int getCountDown() {
        return countDown;
    }
    public int getMode() {
        return mode;
    }

    public int getScore() {
        return score;
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {



        if(isSurvival) {//survival
            System.out.println("Hud Disposed - Survival\n");

            if(easyTexture != null) {
                easyTexture.dispose();
            }
            if(fairTexture != null) {
                fairTexture.dispose();
            }
            if(hardTexture != null) {
                hardTexture.dispose();
            }

        } else { //levels
            System.out.println("Hud Disposed - Levels\n");
            levelTexture.dispose();
        }


        //both
        colonTexture.dispose();
        timer.clear();
        stage.dispose();


    }
}
