package com.dylanwalsh.dodge.config;


import java.util.HashMap;
import java.util.Map;

public class LevelConfig {

    public int levelNumber;
    public int levelTime;
    public int levelTimeDelay;

    public Map<String, Number> aliens = new HashMap<String, Number>();
    public Map<String, Number> alien = new HashMap<String, Number>();
    public Map<String, Number> background = new HashMap<String, Number>();
    public Map<String, Number> debris = new HashMap<String, Number>();
    public Map<String, Number> debrisItem = new HashMap<String, Number>();
    public Map<String, Number> gammaRays = new HashMap<String, Number>();



}
