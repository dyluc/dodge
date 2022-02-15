package com.dylanwalsh.dodge.config;


import com.badlogic.gdx.Gdx;

public class LevelEleven extends LevelConfig {

    public LevelEleven() {
        levelNumber = 11;

        levelTime = 130;
        levelTimeDelay = 2;

        aliens.put("max_velocity", Gdx.graphics.getWidth()/4.5f);
        aliens.put("min_velocity", Gdx.graphics.getWidth()/7.1f);
        aliens.put("spawn_count", 3); //change - another alien
        aliens.put("max_start_y", (Gdx.graphics.getHeight()/8f)*7f);
        aliens.put("min_start_y", (Gdx.graphics.getHeight()/8f));
        aliens.put("spawn_delay", 2);//round wait

        //no change, alien added
        alien.put("fire_rate", 1.7f);
        alien.put("bullet_speed", 1.18f);
        alien.put("bullet_size", Gdx.graphics.getWidth()/40f);
        alien.put("fire_delay", 1);
        alien.put("fire_probability", 2);
        alien.put("velocity_change_probability", 4);

        background.put("stars_per_spawn", 4);
        background.put("max_star_size", Gdx.graphics.getWidth()/35f);
        background.put("min_star_size", Gdx.graphics.getWidth()/90f);
        background.put("max_star_speed", Gdx.graphics.getWidth()/4f);
        background.put("min_star_speed", Gdx.graphics.getWidth()/6f); //change - reduce range

        //2 new changes, above lvl 10
        debris.put("spawn_rate", 0.7f);
        debris.put("spawn_delay", 2); //round wait
        debris.put("spawn_count", 14); //change - more debris
        debris.put("max_vel", Gdx.graphics.getWidth() / 6.25f); //change - faster velocity
        debris.put("min_vel", Gdx.graphics.getWidth() / 8.5f);

        debrisItem.put("fade_interval", 0.11f);//change - quicker fade
        debrisItem.put("fade_increment", 1/20f);
        debrisItem.put("spawn_delay", 1);

        gammaRays.put("speed", Gdx.graphics.getWidth()/6.75f);
        gammaRays.put("spawn_rate", 4);
        gammaRays.put("spawn_delay", 2);//round wait
        gammaRays.put("max_amplitude", Gdx.graphics.getWidth()/70f);
        gammaRays.put("min_amplitude", Gdx.graphics.getWidth()/85f);
        gammaRays.put("max_wavelength", (
                gammaRays.get("max_amplitude").floatValue() /
                        gammaRays.get("speed").floatValue()) /
                (Gdx.graphics.getWidth()/320f));
        gammaRays.put("min_wavelength", (
                gammaRays.get("min_amplitude").floatValue() /
                        gammaRays.get("speed").floatValue()) /
                (Gdx.graphics.getWidth()/150f));
        gammaRays.put("max_size", Gdx.graphics.getWidth()/53f);
        gammaRays.put("min_size", Gdx.graphics.getWidth()/62f);
        gammaRays.put("max_start_y", (Gdx.graphics.getHeight()/8f)*7f);
        gammaRays.put("min_start_y", (Gdx.graphics.getHeight()/8f));
        gammaRays.put("max_length", 37);
        gammaRays.put("min_length", 23); //change - reduced range
    }

}
