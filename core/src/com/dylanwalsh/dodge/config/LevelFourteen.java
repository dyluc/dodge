package com.dylanwalsh.dodge.config;


import com.badlogic.gdx.Gdx;

public class LevelFourteen extends LevelConfig {

    public LevelFourteen() {
        levelNumber = 14;

        levelTime = 145;
        levelTimeDelay = 2;

        aliens.put("max_velocity", Gdx.graphics.getWidth()/4f); //change - faster
        aliens.put("min_velocity", Gdx.graphics.getWidth()/6.8f);
        aliens.put("spawn_count", 3);
        aliens.put("max_start_y", (Gdx.graphics.getHeight()/8f)*7f);
        aliens.put("min_start_y", (Gdx.graphics.getHeight()/8f));
        aliens.put("spawn_delay", 2);//round wait


        alien.put("fire_rate", 1.7f); //no more changes until lvl 18
        alien.put("bullet_speed", 1.18f); //no more changes until lvl 18
        alien.put("bullet_size", Gdx.graphics.getWidth()/36f); //change - larger bullets
        alien.put("fire_delay", 1);
        alien.put("fire_probability", 2); //no more changes until lvl 18
        alien.put("velocity_change_probability", 3);

        background.put("stars_per_spawn", 5);
        background.put("max_star_size", Gdx.graphics.getWidth()/35f);
        background.put("min_star_size", Gdx.graphics.getWidth()/80f);
        background.put("max_star_speed", Gdx.graphics.getWidth()/3.8f); //change - faster stars
        background.put("min_star_speed", Gdx.graphics.getWidth()/6f);

        //2 new changes, above lvl 10
        debris.put("spawn_rate", 0.5f);//change - quicker spawns
        debris.put("spawn_delay", 2); //round wait
        debris.put("spawn_count", 16); //change - more debris
        debris.put("max_vel", Gdx.graphics.getWidth() / 5.8f);
        debris.put("min_vel", Gdx.graphics.getWidth() / 8.3f);

        debrisItem.put("fade_interval", 0.09f);//change - quicker fade
        debrisItem.put("fade_increment", 1/20f);
        debrisItem.put("spawn_delay", 1);

        gammaRays.put("speed", Gdx.graphics.getWidth()/6.75f);
        gammaRays.put("spawn_rate", 3); //change - faster spawns
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
        gammaRays.put("min_length", 23);
    }

}
