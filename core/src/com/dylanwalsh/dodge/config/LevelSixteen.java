package com.dylanwalsh.dodge.config;


import com.badlogic.gdx.Gdx;

public class LevelSixteen extends LevelConfig {

    public LevelSixteen() {

        levelNumber = 16;

        levelTime = 145;
        levelTimeDelay = 2;

        //Level 16 change every attribute
        aliens.put("max_velocity", Gdx.graphics.getWidth()/3.8f); //change - faster
        aliens.put("min_velocity", Gdx.graphics.getWidth()/6.25f); //change - smaller range
        aliens.put("spawn_count", 3); //change - lvl 19
        aliens.put("max_start_y", (Gdx.graphics.getHeight()/9f)*8f); //change - larger spawn y
        aliens.put("min_start_y", (Gdx.graphics.getHeight()/9f)); //change - larger spawn y
        aliens.put("spawn_delay", 2);//round wait - lvl 17


        alien.put("fire_rate", 1.7f); //no more changes until lvl 18
        alien.put("bullet_speed", 1.18f); //no more changes until lvl 18
        alien.put("bullet_size", Gdx.graphics.getWidth()/34f);
        alien.put("fire_delay", 0); //change - no delay
        alien.put("fire_probability", 2); //no more changes until lvl 18
        alien.put("velocity_change_probability", 3);

        background.put("stars_per_spawn", 6);//change - more stars
        background.put("max_star_size", Gdx.graphics.getWidth()/35f);
        background.put("min_star_size", Gdx.graphics.getWidth()/80f);
        background.put("max_star_speed", Gdx.graphics.getWidth()/3.8f);
        background.put("min_star_speed", Gdx.graphics.getWidth()/5f);

        debris.put("spawn_rate", 0.5f);
        debris.put("spawn_delay", 2); //round wait
        debris.put("spawn_count", 18); //change - more debris
        debris.put("max_vel", Gdx.graphics.getWidth() / 5.8f);
        debris.put("min_vel", Gdx.graphics.getWidth() / 8.3f);

        debrisItem.put("fade_interval", 0.09f); //change - quicker fade
        debrisItem.put("fade_increment", 1/20f);
        debrisItem.put("spawn_delay", 0);

        gammaRays.put("speed", Gdx.graphics.getWidth()/6.5f); //change - faster rays
        gammaRays.put("spawn_rate", 3);
        gammaRays.put("spawn_delay", 2);//round wait
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
