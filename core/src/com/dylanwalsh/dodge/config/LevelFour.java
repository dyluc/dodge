package com.dylanwalsh.dodge.config;

import com.badlogic.gdx.Gdx;


public class LevelFour extends LevelConfig {

    public LevelFour() {
        levelNumber = 4;

        levelTime = 30;
        levelTimeDelay = 2; //change

        aliens.put("max_velocity", Gdx.graphics.getWidth()/7f);
        aliens.put("min_velocity", Gdx.graphics.getWidth()/8f); //change - smaller range
        aliens.put("spawn_count", 2);
        aliens.put("max_start_y", (Gdx.graphics.getHeight()/8f)*7f);
        aliens.put("min_start_y", (Gdx.graphics.getHeight()/8f));
        aliens.put("spawn_delay", 2);//round wait - change

        alien.put("fire_rate", 2f);
        alien.put("bullet_speed", 1.25f);
        alien.put("bullet_size", Gdx.graphics.getWidth()/45f);
        alien.put("fire_delay", 2);
        alien.put("fire_probability", 2); //change - reduced probability
        alien.put("velocity_change_probability", 7);

        background.put("stars_per_spawn", 2);
        background.put("max_star_size", Gdx.graphics.getWidth()/40f); //change - larger stars
        background.put("min_star_size", Gdx.graphics.getWidth()/90f);
        background.put("max_star_speed", Gdx.graphics.getWidth()/5f);
        background.put("min_star_speed", Gdx.graphics.getWidth()/8f);

        debris.put("spawn_rate", 0.75f);
        debris.put("spawn_delay", 2); //round wait - change
        debris.put("spawn_count", 10); //change - more debris
        debris.put("max_vel", Gdx.graphics.getWidth() / 7.5f);
        debris.put("min_vel", Gdx.graphics.getWidth() / 8.5f);

        debrisItem.put("fade_interval", 0.18f); //change - quicker fade
        debrisItem.put("fade_increment", 1/20f);
        debrisItem.put("spawn_delay", 1);

        //Level 4 change every attribute
        gammaRays.put("speed", Gdx.graphics.getWidth()/6.75f); //change - faster
        gammaRays.put("spawn_rate", 4); //change - spawn faster
        gammaRays.put("spawn_delay", 2);//round wait - change
        gammaRays.put("max_amplitude", Gdx.graphics.getWidth()/70f); //change - larger amplitude
        gammaRays.put("min_amplitude", Gdx.graphics.getWidth()/85f); //change - smaller range
        gammaRays.put("max_wavelength", (
                gammaRays.get("max_amplitude").floatValue() /
                        gammaRays.get("speed").floatValue()) /
                (Gdx.graphics.getWidth()/320f)); //change - larger wavelength
        gammaRays.put("min_wavelength", (
                gammaRays.get("min_amplitude").floatValue() /
                        gammaRays.get("speed").floatValue()) /
                (Gdx.graphics.getWidth()/150f)); //change - smaller range
        gammaRays.put("max_size", Gdx.graphics.getWidth()/53f); //change - larger size
        gammaRays.put("min_size", Gdx.graphics.getWidth()/62f); //change - larger range
        gammaRays.put("max_start_y", (Gdx.graphics.getHeight()/8f)*7f);
        gammaRays.put("min_start_y", (Gdx.graphics.getHeight()/8f));
        gammaRays.put("max_length", 28); //change - larger ray
        gammaRays.put("min_length", 18); //change - larger range
    }
}
