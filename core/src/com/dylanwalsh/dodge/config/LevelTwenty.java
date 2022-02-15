package com.dylanwalsh.dodge.config;


import com.badlogic.gdx.Gdx;

public class LevelTwenty extends LevelConfig{

    public LevelTwenty() {
        levelNumber = 20;

        levelTime = 220;
        levelTimeDelay = 1;


        aliens.put("max_velocity", Gdx.graphics.getWidth()/3.7f);//change - faster aliens
        aliens.put("min_velocity", Gdx.graphics.getWidth()/6.15f);
        aliens.put("spawn_count", 3);
        aliens.put("max_start_y", (Gdx.graphics.getHeight()/9f)*8f);
        aliens.put("min_start_y", (Gdx.graphics.getHeight()/9f));
        aliens.put("spawn_delay", 1);//round wait


        alien.put("fire_rate", 1.6f);
        alien.put("bullet_speed", 1.15f);
        alien.put("bullet_size", Gdx.graphics.getWidth()/34f);
        alien.put("fire_delay", 0);
        alien.put("fire_probability", 1); //change - 1/1 probability
        alien.put("velocity_change_probability", 2);

        background.put("stars_per_spawn", 8); //change - more stars
        background.put("max_star_size", Gdx.graphics.getWidth()/35f);
        background.put("min_star_size", Gdx.graphics.getWidth()/75f);
        background.put("max_star_speed", Gdx.graphics.getWidth()/3.5f);
        background.put("min_star_speed", Gdx.graphics.getWidth()/5f);

        debris.put("spawn_rate", 0.4f);
        debris.put("spawn_delay", 1); //round wait
        debris.put("spawn_count", 21); //change - more debris
        debris.put("max_vel", Gdx.graphics.getWidth() / 5.8f);
        debris.put("min_vel", Gdx.graphics.getWidth() / 8.3f);

        debrisItem.put("fade_interval", 0.05f); //change - quicker fade
        debrisItem.put("fade_increment", 1/20f);
        debrisItem.put("spawn_delay", 0);

        gammaRays.put("speed", Gdx.graphics.getWidth()/6.5f);
        gammaRays.put("spawn_rate", 3);
        gammaRays.put("spawn_delay", 1);//round wait
        gammaRays.put("max_amplitude", Gdx.graphics.getWidth()/62f);
        gammaRays.put("min_amplitude", Gdx.graphics.getWidth()/82f);
        gammaRays.put("max_wavelength", (
                gammaRays.get("max_amplitude").floatValue() /
                        gammaRays.get("speed").floatValue()) /
                (Gdx.graphics.getWidth()/350f));
        gammaRays.put("min_wavelength", (
                gammaRays.get("min_amplitude").floatValue() /
                        gammaRays.get("speed").floatValue()) /
                (Gdx.graphics.getWidth()/150f));
        gammaRays.put("max_size", Gdx.graphics.getWidth()/53f);
        gammaRays.put("min_size", Gdx.graphics.getWidth()/62f);
        gammaRays.put("max_start_y", (Gdx.graphics.getHeight()/8f)*7f);
        gammaRays.put("min_start_y", (Gdx.graphics.getHeight()/8f));
        gammaRays.put("max_length", 39); //change - larger rays
        gammaRays.put("min_length", 25);
    }

}
