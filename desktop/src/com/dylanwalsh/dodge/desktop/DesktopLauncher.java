package com.dylanwalsh.dodge.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dylanwalsh.dodge.GameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 10*50;
		config.height = 16*50;
		config.resizable = false;
		//config.foregroundFPS = 30;

		new LwjglApplication(new GameClass(), config);
	}
}
