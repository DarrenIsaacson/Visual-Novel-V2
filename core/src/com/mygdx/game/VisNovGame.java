package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VisNovGame extends BaseGame {
	
	@Override
	public void create () {
		super.create();
		setActiveScreen(new MenuScreen());
	}

}
