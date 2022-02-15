package com.dylanwalsh.dodge.config;


import com.badlogic.gdx.Gdx;

public class LevelSeventeen extends LevelConfig{

    public LevelSeventeen() {
        levelNumber = 17;

        levelTime = 200;
        levelTimeDelay = 1; //change - reduced round wait


        aliens.put("max_velocity", Gdx.graphics.getWidth()/3.8f);
        aliens.put("min_velocity", Gdx.graphics.getWidth()/6.25f);
        aliens.put("spawn_count", 3); //change - lvl 19
        aliens.put("max_start_y", (Gdx.graphics.getHeight()/9f)*8f);
        aliens.put("min_start_y", (Gdx.graphics.getHeight()/9f));
        aliens.put("spawn_delay", 1);//round wait - change


        alien.put("fire_rate", 1.7f); //no more changes until lvl 18
        alien.put("bullet_speed", 1.18f); //no more changes until lvl 18
        alien.put("bullet_size", Gdx.graphics.getWidth()/34f);
        alien.put("fire_delay", 0);
        alien.put("fire_probability", 2); //no more changes until lvl 18
        alien.put("velocity_change_probability", 2); //change - increased probability

        background.put("stars_per_spawn", 6);
        background.put("max_star_size", Gdx.graphics.getWidth()/35f);
        background.put("min_star_size", Gdx.graphics.getWidth()/80f);
        background.put("max_star_speed", Gdx.graphics.getWidth()/3.5f); //change - increased speed
        background.put("min_star_speed", Gdx.graphics.getWidth()/5f);

        debris.put("spawn_rate", 0.5f);
        debris.put("spawn_delay", 1); //round wait - change
        debris.put("spawn_count", 19); //change - more debris
        debris.put("max_vel", Gdx.graphics.getWidth() / 5.8f);
        debris.put("min_vel", Gdx.graphics.getWidth() / 8.3f);

        debrisItem.put("fade_interval", 0.08f); //change - quicker fade
        debrisItem.put("fade_increment", 1/20f);
        debrisItem.put("spawn_delay", 0);

        gammaRays.put("speed", Gdx.graphics.getWidth()/6.5f);
        gammaRays.put("spawn_rate", 3);
        gammaRays.put("spawn_delay", 1);//round wait - change
        gammaRays.put("max_amplitude", Gdx.graphics.getWidth()/65f);
        gammaRays.put("min_amplitude", Gdx.graphics.getWidth()/85f);
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
        gammaRays.put("max_length", 37);
        gammaRays.put("min_length", 25);
    }

}
