package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Made by Darren


/***
 * This is what initialized the game to open the menu screen
 */

/**
 * Because I am using an extension of basegame, I can use properties that are used inside of baseGame here.
 */
public class VisNovGame extends BaseGame {

	// This is overiding the story screen class from prompting. This will always come first when the program starts
	@Override
	public void create () {
		super.create();
		setActiveScreen(new MenuScreen());
	}

}
