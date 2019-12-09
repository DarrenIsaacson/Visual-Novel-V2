package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public class MenuScreen extends BaseScreen{

    public void initialize()
    {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.loadTexture( "misc_sky.jpg" );
        background.setSize(1000,600);
        BaseActor title = new BaseActor(0,0, mainStage);
        title.loadTexture( "a-bad-dream.png" );
        TextButton startButton = new TextButton( "Start", BaseGame.textButtonStyle );

        startButton.addListener(
                new EventListener() {
                    @Override
                    public boolean handle(Event e) {
                        if (!(e instanceof InputEvent) ||
                                !((InputEvent) e).getType().equals(Type.touchDown))
                            return false;
                        BaseGame.setActiveScreen(new StoryScreen());
                        return false;
                    }
                }
        );

        TextButton quitButton = new TextButton( "Quit", BaseGame.textButtonStyle );
        quitButton.addListener(
                new EventListener() {
                    @Override
                    public boolean handle(Event e) {
                        if (!(e instanceof InputEvent) ||
                                !((InputEvent) e).getType().equals(Type.touchDown))
                            return false;
                        Gdx.app.exit();
                        return false;
                    }
                }
        );
        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(startButton);
        uiTable.add(quitButton);
    }
    public void update(float dt)
    { }
    public boolean keyDown(int keyCode)
    {
        if (Gdx.input.isKeyPressed(Keys.ENTER))
            BaseGame.setActiveScreen( new StoryScreen() );
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit();
        return false;
    }

}
