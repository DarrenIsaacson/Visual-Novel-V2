package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.VisNovGame;

//Made by Darren

/***
 * This is where the game gets launched
 *
 */

public class DesktopLauncher {
	public static void main (String[] arg) {

		// Uses the LibGDX configuration to config the interfaces width, height, and name.
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bad Dream";
		config.height = 600;
		config.width = 1000;

		// prompts the VisualNovGame class with the configsBaseGame
		new LwjglApplication(new VisNovGame(), config);
	}
}
