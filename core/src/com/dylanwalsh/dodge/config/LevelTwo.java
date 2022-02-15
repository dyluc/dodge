package com.dylanwalsh.dodge.config;


import com.badlogic.gdx.Gdx;
import java.util.Random;

public class LevelTwo extends LevelConfig {

    public LevelTwo() {
        levelNumber = 2;

        levelTime = 30;
        levelTimeDelay = 3;

        aliens.put("max_velocity", Gdx.graphics.getWidth()/7f); //change - faster
        aliens.put("min_velocity", Gdx.graphics.getWidth()/9f);
        aliens.put("spawn_count", 1);
        aliens.put("max_start_y", (Gdx.graphics.getHeight()/8f)*7f);
        aliens.put("min_start_y", (Gdx.graphics.getHeight()/8f));
        aliens.put("spawn_delay", 3);//round wait

        alien.put("fire_rate", 2f);
        alien.put("bullet_speed", 1.25f); //change - faster
        alien.put("bullet_size", Gdx.graphics.getWidth()/45f);
        alien.put("fire_delay", 2);
        alien.put("fire_probability", 3);
        alien.put("velocity_change_probability", 7);

        background.put("stars_per_spawn", 2); //change - more stars
        background.put("max_star_size", Gdx.graphics.getWidth()/50f);
        background.put("min_star_size", Gdx.graphics.getWidth()/90f);
        background.put("max_star_speed", Gdx.graphics.getWidth()/5f);
        background.put("min_star_speed", Gdx.graphics.getWidth()/10f);

        debris.put("spawn_rate", 0.75f);//change - more frequent
        debris.put("spawn_delay", 3); //round wait
        debris.put("spawn_count", 5);
        debris.put("max_vel", Gdx.graphics.getWidth() / 7.5f);
        debris.put("min_vel", Gdx.graphics.getWidth() / 8.5f);

        debrisItem.put("fade_interval", 0.19f);//change - fade in quicker
        debrisItem.put("fade_increment", 1/20f);
        debrisItem.put("spawn_delay", 2);

        gammaRays.put("speed", Gdx.graphics.getWidth()/7f);//change - faster
        gammaRays.put("spawn_rate", 6);
        gammaRays.put("spawn_delay", 3);//round wait
        gammaRays.put("max_amplitude", Gdx.graphics.getWidth()/75f);
        gammaRays.put("min_amplitude", Gdx.graphics.getWidth()/90f);
        gammaRays.put("max_wavelength", (
                gammaRays.get("max_amplitude").floatValue() /
                        gammaRays.get("speed").floatValue()) /
                (Gdx.graphics.getWidth()/300f));
        gammaRays.put("min_wavelength", (
                gammaRays.get("min_amplitude").floatValue() /
                        gammaRays.get("speed").floatValue()) /
                (Gdx.graphics.getWidth()/120f));
        gammaRays.put("max_size", Gdx.graphics.getWidth()/55f);
        gammaRays.put("min_size", Gdx.graphics.getWidth()/65f);
        gammaRays.put("max_start_y", (Gdx.graphics.getHeight()/8f)*7f);
        gammaRays.put("min_start_y", (Gdx.graphics.getHeight()/8f));
        gammaRays.put("max_length", 25);
        gammaRays.put("min_length", 15);

    }

}
