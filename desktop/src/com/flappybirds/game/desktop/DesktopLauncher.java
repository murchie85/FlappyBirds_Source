package com.flappybirds.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flappybirds.game.FlappyDemo;

public class DesktopLauncher {
	// KEY GOAL TO CHANGE THE DESKTOP SCREEN TO LOOK LIKE A PHONE
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyDemo.WIDTH;			// config is pulling our global variable
		config.height = FlappyDemo.HEIGHT;			// from flappy demo
		config.title = FlappyDemo.TITLE;

		new LwjglApplication(new FlappyDemo(), config);
	}
}
