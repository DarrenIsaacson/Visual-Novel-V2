package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

/***
 * This is the Menu screen when you run the program, this is to have a nice introduction to the program.
 *
 *
 */

public class MenuScreen extends BaseScreen{

    //
    public void initialize()
    {
        // Creating a background
        BaseActor background = new BaseActor(0,0, mainStage); // Initializing the background image actor;
        background.loadTexture( "misc_sky.jpg" ); // setting the image
        background.setSize(1000,600);  // Adjusted the size of background
        BaseActor title = new BaseActor(0,0, mainStage);// Initializing the title image actor.
        title.loadTexture( "a-bad-dream.png" ); // setting the image

        // This generates a button from the LibGDX library.
        TextButton startButton = new TextButton( "Start", BaseGame.textButtonStyle );

        // An event listener for the generated button to start the game.
        startButton.addListener(
                e -> {
                    if (!(e instanceof InputEvent) ||
                            !((InputEvent) e).getType().equals(Type.touchDown))
                        return false;
                    BaseGame.setActiveScreen(new StoryScreen()); // Opens the StoryScreen class
                    return false;
                }
        );
        // Creates a quit button from the LibGdx text button library
        TextButton quitButton = new TextButton( "Quit", BaseGame.textButtonStyle );

        // An event listener for the generated button to close the application.
        quitButton.addListener(
                e -> {
                    if (!(e instanceof InputEvent) ||
                            !((InputEvent) e).getType().equals(Type.touchDown))
                        return false;
                    Gdx.app.exit(); // closes the app
                    return false;
                }
        );

        // Organizes the buttons with the UiTable grid.
        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(startButton);
        uiTable.add(quitButton);
    }
    public void update(float dt) // Changes the game world and entities
    { }
    public boolean keyDown(int keyCode) // Method to check if the button was clicked.
    {
        if (Gdx.input.isKeyPressed(Keys.ENTER))
            BaseGame.setActiveScreen( new StoryScreen() );
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit();
        return false;
    }

}
