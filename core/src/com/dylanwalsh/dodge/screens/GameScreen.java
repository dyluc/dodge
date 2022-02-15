package com.dylanwalsh.dodge.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.dylanwalsh.dodge.GameClass;
import com.dylanwalsh.dodge.config.LevelConfig;
import com.dylanwalsh.dodge.config.LevelEighteen;
import com.dylanwalsh.dodge.config.LevelFifteen;
import com.dylanwalsh.dodge.config.LevelFive;
import com.dylanwalsh.dodge.config.LevelFourteen;
import com.dylanwalsh.dodge.config.LevelNine;
import com.dylanwalsh.dodge.config.LevelNineteen;
import com.dylanwalsh.dodge.config.LevelOne;
import com.dylanwalsh.dodge.config.LevelTen;
import com.dylanwalsh.dodge.config.LevelTwelve;
import com.dylanwalsh.dodge.entities.Alien;
import com.dylanwalsh.dodge.entities.Aliens;
import com.dylanwalsh.dodge.entities.Background;
import com.dylanwalsh.dodge.entities.Bullet;
import com.dylanwalsh.dodge.entities.Debris;
import com.dylanwalsh.dodge.entities.DebrisCircle;
import com.dylanwalsh.dodge.entities.DebrisItem;
import com.dylanwalsh.dodge.entities.DebrisRectangle;
import com.dylanwalsh.dodge.entities.GammaRay;
import com.dylanwalsh.dodge.entities.GammaRays;
import com.dylanwalsh.dodge.entities.Snake;

public class GameScreen implements Screen{


    GameClass game;
    private boolean isDisposed;
    private Hud hud;
    private LevelConfig levelConfig; //pass to gameoverscreen for replay button
    //

    private boolean changedScreen;

    private Snake snake;
    private Debris debris;
    private Background background;
    private Aliens aliens;
    private GammaRays gammaRays;

    public GameScreen(GameClass game, LevelConfig levelConfig) {
        this.game = game;
        isDisposed = false;
        changedScreen = false;
        this.levelConfig = levelConfig;

        //set properties in a static way before objects created

        Aliens.maxVel = levelConfig.aliens.get("max_velocity").floatValue();
        Aliens.minVel = levelConfig.aliens.get("min_velocity").floatValue();
        Aliens.spawnAlienCount = levelConfig.aliens.get("spawn_count").intValue();
        Aliens.maxStartY = levelConfig.aliens.get("max_start_y").floatValue();
        Aliens.minStartY = levelConfig.aliens.get("min_start_y").floatValue();
        Aliens.spawnDelay = levelConfig.aliens.get("spawn_delay").intValue();

        Alien.fireRate = levelConfig.alien.get("fire_rate").floatValue();
        Alien.bulletSpeed = levelConfig.alien.get("bullet_speed").floatValue();
        Alien.bulletSize = levelConfig.alien.get("bullet_size").floatValue();
        Alien.fireDelay = levelConfig.alien.get("fire_delay").floatValue();
        Alien.fireProbability = levelConfig.alien.get("fire_probability").intValue();
        Alien.velocityChangeProbability = levelConfig.alien.get("velocity_change_probability").intValue();

        Background.starsPerSpawn = levelConfig.background.get("stars_per_spawn").intValue();
        Background.maxSize = levelConfig.background.get("max_star_size").floatValue();
        Background.minSize = levelConfig.background.get("min_star_size").floatValue();
        Background.maxSpeed = levelConfig.background.get("max_star_speed").floatValue();
        Background.minSpeed = levelConfig.background.get("min_star_speed").floatValue();

        Debris.spawnRate = levelConfig.debris.get("spawn_rate").floatValue();
        Debris.spawnRoundDelay = levelConfig.debris.get("spawn_delay").intValue();
        Debris.spawnDebrisCount = levelConfig.debris.get("spawn_count").intValue();
        Debris.maxVel = levelConfig.debris.get("max_vel").floatValue();
        Debris.minVel = levelConfig.debris.get("min_vel").floatValue();

        DebrisItem.fadeInterval = levelConfig.debrisItem.get("fade_interval").floatValue();
        DebrisItem.fadeIncrement = levelConfig.debrisItem.get("fade_increment").floatValue();
        DebrisItem.spawnDelay = levelConfig.debrisItem.get("spawn_delay").floatValue();

        GammaRays.gammaSpeed = levelConfig.gammaRays.get("speed").floatValue();
        GammaRays.spawnRate = levelConfig.gammaRays.get("spawn_rate").intValue();
        GammaRays.spawnDelay = levelConfig.gammaRays.get("spawn_delay").intValue();
        GammaRays.maxAmplitude = levelConfig.gammaRays.get("max_amplitude").floatValue();
        GammaRays.minAmplitude = levelConfig.gammaRays.get("min_amplitude").floatValue();
        GammaRays.maxWaveLength = levelConfig.gammaRays.get("max_wavelength").floatValue();
        GammaRays.minWaveLength = levelConfig.gammaRays.get("min_wavelength").floatValue();
        GammaRays.maxSize = levelConfig.gammaRays.get("max_size").floatValue();
        GammaRays.minSize = levelConfig.gammaRays.get("min_size").floatValue();
        GammaRays.maxStartY = levelConfig.gammaRays.get("max_start_y").floatValue();
        GammaRays.minStartY = levelConfig.gammaRays.get("min_start_y").floatValue();
        GammaRays.maxGammaLength = levelConfig.gammaRays.get("max_length").intValue();
        GammaRays.minGammaLength = levelConfig.gammaRays.get("min_length").intValue();


        snake = new Snake(GameClass.gameBatch);
        debris = new Debris(GameClass.gameBatch);
        background = new Background();
        background.setSb(GameClass.gameBatch);
        aliens = new Aliens(GameClass.gameBatch);
        gammaRays = new GammaRays(GameClass.gameBatch);


        //create hud
        hud = new Hud(levelConfig.levelNumber, levelConfig.levelTime, levelConfig.levelTimeDelay);

    }

    public GameScreen(GameClass game, int mode) {
        this.game = game;
        isDisposed = false;
        changedScreen = false;

        //set properties in a static way before objects created

        switch(mode) {
            case 0: //easy
                levelConfig = new LevelFive();
                hud = new Hud(3, mode);
                break;
            case 1: // fair
                levelConfig = new LevelTwelve();
                hud = new Hud(2, mode);
                break;
            case 2: //hard
                levelConfig = new LevelNineteen();
                hud = new Hud(1, mode);
                break;
        }

        Aliens.maxVel = levelConfig.aliens.get("max_velocity").floatValue();
        Aliens.minVel = levelConfig.aliens.get("min_velocity").floatValue();
        Aliens.spawnAlienCount = levelConfig.aliens.get("spawn_count").intValue();
        Aliens.maxStartY = levelConfig.aliens.get("max_start_y").floatValue();
        Aliens.minStartY = levelConfig.aliens.get("min_start_y").floatValue();
        Aliens.spawnDelay = levelConfig.aliens.get("spawn_delay").intValue();

        Alien.fireRate = levelConfig.alien.get("fire_rate").floatValue();
        Alien.bulletSpeed = levelConfig.alien.get("bullet_speed").floatValue();
        Alien.bulletSize = levelConfig.alien.get("bullet_size").floatValue();
        Alien.fireDelay = levelConfig.alien.get("fire_delay").floatValue();
        Alien.fireProbability = levelConfig.alien.get("fire_probability").intValue();
        Alien.velocityChangeProbability = levelConfig.alien.get("velocity_change_probability").intValue();

        Background.starsPerSpawn = levelConfig.background.get("stars_per_spawn").intValue();
        Background.maxSize = levelConfig.background.get("max_star_size").floatValue();
        Background.minSize = levelConfig.background.get("min_star_size").floatValue();
        Background.maxSpeed = levelConfig.background.get("max_star_speed").floatValue();
        Background.minSpeed = levelConfig.background.get("min_star_speed").floatValue();

        Debris.spawnRate = levelConfig.debris.get("spawn_rate").floatValue();
        Debris.spawnRoundDelay = levelConfig.debris.get("spawn_delay").intValue();
        Debris.spawnDebrisCount = levelConfig.debris.get("spawn_count").intValue();
        Debris.maxVel = levelConfig.debris.get("max_vel").floatValue();
        Debris.minVel = levelConfig.debris.get("min_vel").floatValue();

        DebrisItem.fadeInterval = levelConfig.debrisItem.get("fade_interval").floatValue();
        DebrisItem.fadeIncrement = levelConfig.debrisItem.get("fade_increment").floatValue();
        DebrisItem.spawnDelay = levelConfig.debrisItem.get("spawn_delay").floatValue();

        GammaRays.gammaSpeed = levelConfig.gammaRays.get("speed").floatValue();
        GammaRays.spawnRate = levelConfig.gammaRays.get("spawn_rate").intValue();
        GammaRays.spawnDelay = levelConfig.gammaRays.get("spawn_delay").intValue();
        GammaRays.maxAmplitude = levelConfig.gammaRays.get("max_amplitude").floatValue();
        GammaRays.minAmplitude = levelConfig.gammaRays.get("min_amplitude").floatValue();
        GammaRays.maxWaveLength = levelConfig.gammaRays.get("max_wavelength").floatValue();
        GammaRays.minWaveLength = levelConfig.gammaRays.get("min_wavelength").floatValue();
        GammaRays.maxSize = levelConfig.gammaRays.get("max_size").floatValue();
        GammaRays.minSize = levelConfig.gammaRays.get("min_size").floatValue();
        GammaRays.maxStartY = levelConfig.gammaRays.get("max_start_y").floatValue();
        GammaRays.minStartY = levelConfig.gammaRays.get("min_start_y").floatValue();
        GammaRays.maxGammaLength = levelConfig.gammaRays.get("max_length").intValue();
        GammaRays.minGammaLength = levelConfig.gammaRays.get("min_length").intValue();


        snake = new Snake(GameClass.gameBatch);
        debris = new Debris(GameClass.gameBatch);
        background = new Background();
        background.setSb(GameClass.gameBatch);
        aliens = new Aliens(GameClass.gameBatch);
        gammaRays = new GammaRays(GameClass.gameBatch);


    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

        //check hud count down
        if(!hud.isSurvival){
            if(hud.getCountDown() == 0) {

                game.completedLevels.putBoolean(Integer.toString(levelConfig.levelNumber), true); //level complete
                game.completedLevels.flush();
                //change preferences before change screen to make shore proper colour level digit is renderered

                LevelScreen newLvlScreen = new LevelScreen(game);
                Gdx.input.setInputProcessor(newLvlScreen.stage);
                game.setScreen(newLvlScreen);
            }
        }


        background.update(delta);
        debris.update(delta);
        gammaRays.update(delta);
        snake.update(delta);
        aliens.update(delta, snake.getCircles()[0].x, snake.getCircles()[0].y);


        //check collisions here
        for(DebrisRectangle r : debris.getDebrisRectangles()) {
            if(r.getOpacity() == 1f) {
                Circle[] circles = snake.getCircles();
                for (int i = 0; i < circles.length; i++) {
                    if (circleRectColliding(
                            circles[i].x, //circleX
                            circles[i].y, //circleY
                            circles[i].radius, //circleRadius
                            r.getRectangle().getX(), //rectX
                            r.getRectangle().getY(), //rectY
                            r.getRectangle().getWidth(), //rectWidth
                            r.getRectangle().getHeight() //rectHeight
                    )) {
                        if(!changedScreen){
                            hide(); //had to make a isDisposed boolean to avoid disposing twice.
                            if(hud.isSurvival) { //survival
                                System.out.println("Died in survival");
                                GameOverScreen newGameOverScreen = new GameOverScreen(game, true, hud.getMode());
                                Gdx.input.setInputProcessor(newGameOverScreen.stage);
                                newGameOverScreen.createTable(hud.getScore(), levelConfig);
                                game.setScreen(newGameOverScreen);
                            } else { //levels
                                System.out.println("Died in levels");
                                GameOverScreen newGameOverScreen = new GameOverScreen(game, false, 0);
                                Gdx.input.setInputProcessor(newGameOverScreen.stage);
                                newGameOverScreen.createTable(hud.getCountDown(), levelConfig);
                                game.setScreen(newGameOverScreen);
                            }
                            changedScreen = true;
                        }
                    }
                }
            }
        }
        for(DebrisCircle c : debris.getDebrisCircles()) {
            if(c.getOpacity() == 1f) {
                Circle[] circles = snake.getCircles();
                for (int i = 0; i < circles.length; i++) {
                    if (circleCircleColliding(
                            circles[i].x,
                            circles[i].y,
                            circles[i].radius,
                            c.getCircle().x,
                            c.getCircle().y,
                            c.getCircle().radius
                    )) {
                        if(!changedScreen){
                            hide(); //had to make a isDisposed boolean to avoid disposing twice.
                            if(hud.isSurvival) { //survival
                                System.out.println("Died in survival");
                                GameOverScreen newGameOverScreen = new GameOverScreen(game, true, hud.getMode());
                                Gdx.input.setInputProcessor(newGameOverScreen.stage);
                                newGameOverScreen.createTable(hud.getScore(), levelConfig);
                                game.setScreen(newGameOverScreen);
                            } else { //levels
                                System.out.println("Died in levels");
                                GameOverScreen newGameOverScreen = new GameOverScreen(game, false, 0);
                                Gdx.input.setInputProcessor(newGameOverScreen.stage);
                                newGameOverScreen.createTable(hud.getCountDown(), levelConfig);
                                game.setScreen(newGameOverScreen);
                            }
                            changedScreen = true;
                        }
                    }
                }
            }
        }
        //bullet with player
        for(Alien alien : aliens.getAliens()) {
            for(Bullet bullet : alien.getBullets()) {
                Circle[] circles = snake.getCircles();
                for (int i = 0; i < circles.length; i++) {
                    if (circleRectColliding(
                            circles[i].x,
                            circles[i].y,
                            circles[i].radius,
                            bullet.getX(),
                            bullet.getY(),
                            bullet.getWidth(),
                            bullet.getHeight()
                    )) {
                        if(!changedScreen){
                            hide(); //had to make a isDisposed boolean to avoid disposing twice.
                            if(hud.isSurvival) { //survival
                                System.out.println("Died in survival");
                                GameOverScreen newGameOverScreen = new GameOverScreen(game, true, hud.getMode());
                                Gdx.input.setInputProcessor(newGameOverScreen.stage);
                                newGameOverScreen.createTable(hud.getScore(), levelConfig);
                                game.setScreen(newGameOverScreen);
                            } else { //levels
                                System.out.println("Died in levels");
                                GameOverScreen newGameOverScreen = new GameOverScreen(game, false, 0);
                                Gdx.input.setInputProcessor(newGameOverScreen.stage);
                                newGameOverScreen.createTable(hud.getCountDown(), levelConfig);
                                game.setScreen(newGameOverScreen);
                            }
                            changedScreen = true;
                        }
                    }
                }
            }
        }
        //rays with player
        for(GammaRay gammaRay : gammaRays.getGammaRays()) {
            for(Vector2 positions : gammaRay.getPositions()) {
                Circle[] circles = snake.getCircles();
                for (int i = 0; i < circles.length; i++) {
                    if (circleRectColliding(
                            circles[i].x, //circleX
                            circles[i].y, //circleY
                            circles[i].radius, //circleRadius
                            positions.x, //rectX
                            positions.y, //rectY
                            gammaRay.getSize(), //rectWidth
                            gammaRay.getSize() //rectHeight
                    )) {
                        if(!changedScreen){
                            hide(); //had to make a isDisposed boolean to avoid disposing twice.
                            if(hud.isSurvival) { //survival
                                System.out.println("Died in survival");
                                GameOverScreen newGameOverScreen = new GameOverScreen(game, true, hud.getMode());
                                Gdx.input.setInputProcessor(newGameOverScreen.stage);
                                newGameOverScreen.createTable(hud.getScore(), levelConfig);
                                game.setScreen(newGameOverScreen);
                            } else { //levels
                                System.out.println("Died in levels");
                                GameOverScreen newGameOverScreen = new GameOverScreen(game, false, 0);
                                Gdx.input.setInputProcessor(newGameOverScreen.stage);
                                newGameOverScreen.createTable(hud.getCountDown(), levelConfig);
                                game.setScreen(newGameOverScreen);
                            }
                            changedScreen = true;
                        }
                    }
                }
            }
        }


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        background.draw();
        aliens.draw();
        gammaRays.draw();
        snake.draw();
        debris.draw();
        hud.render(delta);
    }

    @Override public void resize(int width, int height) {
    }

    @Override public void pause() {}

    @Override public void resume() {}

    public boolean circleRectColliding(float circleX, float circleY, float circleRadius,
                                       float rectX, float rectY, float rectWidth, float rectHeight) {
        float deltaX = circleX - Math.max(rectX, Math.min(circleX, rectX + rectWidth));
        float deltaY = circleY - Math.max(rectY, Math.min(circleY, rectY + rectHeight));
        return (deltaX * deltaX + deltaY * deltaY) < (circleRadius * circleRadius);
    }
    public boolean circleCircleColliding(float circleAX, float circleAY, float circleARadius,
                                       float circleBX, float circleBY, float circleBRadius) {
        return ( Math.pow( Math.pow(circleBX - circleAX, 2) + Math.pow(circleBY - circleAY, 2), 1/2f ) ) <= (circleARadius + circleBRadius);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        if(!isDisposed) {
            System.out.println("GameScreen Disposed\n");


            hud.dispose();

            gammaRays.dispose();
            snake.dispose();
            debris.dispose();
            aliens.dispose();


            background.dispose();

            isDisposed = true;
        }

    }
}
