package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 *  This is directly from the book Java Game Development with LibGDX written by Lee Stemkoski
 */


public abstract class BaseScreen implements Screen, InputProcessor
{
    protected Stage mainStage;
    protected Stage uiStage;
    protected Table uiTable;

    public BaseScreen()
    {
        mainStage = new Stage();
        uiStage = new Stage();

        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);

        initialize();
    }

    public abstract void initialize();

    public abstract void update(float dt);

    // Gameloop:
    // (1) process input (discrete handled by listener; continuous in update)
    // (2) update game logic
    // (3) render the graphics
    public void render(float dt) 
    {
        // act methods
        uiStage.act(dt);
        mainStage.act(dt);

        // defined by user
        update(dt);

        // clear the screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw the graphics
        mainStage.draw();
        uiStage.draw();
    }

    // methods required by Screen interface
    public void resize(int width, int height) {  }

    public void pause()   {  }

    public void resume()  {  }

    public void dispose() {  }

    /**
     *  Called when this becomes the active screen in a Game.
     *  Set up InputMultiplexer here, in case screen is reactivated at a later time.
     */
    public void show()    
    {  
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(uiStage);
        im.addProcessor(mainStage);
    }

    /**
     *  Called when this is no longer the active screen in a Game.
     *  Screen class and Stages no longer process input.
     *  Other InputProcessors must be removed manually.
     */
    public void hide()    
    {  
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(uiStage);
        im.removeProcessor(mainStage);
    }

    // methods required by InputProcessor interface
    public boolean keyDown(int keycode)
    {  return false;  }

    public boolean keyUp(int keycode)
    {  return false;  }

    public boolean keyTyped(char c) 
    {  return false;  }

    public boolean mouseMoved(int screenX, int screenY)
    {  return false;  }

    public boolean scrolled(int amount) 
    {  return false;  }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) 
    {  return false;  }

    public boolean touchDragged(int screenX, int screenY, int pointer) 
    {  return false;  }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) 
    {  return false;  }
}