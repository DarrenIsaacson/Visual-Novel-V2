package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

;

public class StoryScreen extends BaseScreen {
    Scene scene;
    Background background;
    Rose rose;
    DialogBox dialogBox;
    BaseActor continueKey;
    Table buttonTable;
    BaseActor theEnd;

    public void initialize()
    {
        background = new Background(0,0, mainStage);
        background.setOpacity(0);
        BaseActor.setWorldBounds(background);

        rose = new Rose(0,0, mainStage);
        rose.setSize(480,600);

        dialogBox = new DialogBox(0,0, uiStage);
        dialogBox.setDialogSize(600, 150);
        dialogBox.setBackgroundColor( new Color(0.2f, 0.2f, 0.2f, 1) );
        dialogBox.setVisible(false);

        continueKey = new BaseActor(0,0,uiStage);
        continueKey.loadTexture("key-C.png");
        continueKey.setSize(32,32);
        continueKey.setVisible(false);

        dialogBox.addActor(continueKey);
        continueKey.setPosition( dialogBox.getWidth() - continueKey.getWidth(), 0 );

        buttonTable = new Table();
        buttonTable.setVisible(false);

        uiTable.add().expandY();
        uiTable.row();
        uiTable.add(buttonTable);
        uiTable.row();
        uiTable.add(dialogBox);

        theEnd = new BaseActor(0,0,mainStage);
        theEnd.loadTexture("the-end.png");
        theEnd.centerAtActor(background);
        theEnd.setScale(2);
        theEnd.setOpacity(0);

        scene = new Scene();
        mainStage.addActor(scene);
        wakeFromDream();


    }
    public void addTextSequence(String s)
    {

        scene.addSegment( new SceneSegment( dialogBox, SceneActions.typewriter(s) ));
        scene.addSegment( new SceneSegment( continueKey, Actions.show() ));
        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( continueKey, Actions.hide() ));
    }

    public void wakeFromDream()
    {
        background.setAnimation( background.wakeFromDream );
        dialogBox.setText("");
        rose.addAction( SceneActions.moveToOutsideLeft(0) );
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToScreenCenter(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence( "My name is Kelsoe Kismet. I am a student at Aureus Ludus Academy." );
        addTextSequence( "I can be a little forgetful sometimes. Right now, I'm looking for my homework." );
                scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideRight(1) ));
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(new Runnable() {
            @Override
            public void run() {
                StoryScreen.this.lateForClassHouse();
            }
        }) ));
        scene.start();
    }
    public void lateForClassHouse()
    { }
    public void lateForClassRunning()
    { }
    public void lateForClassGetARide()
    { }
    public void arriveToSchool()
    { }
    public void goToClass()
    { }
    public void update(float dt)
    { }
    public boolean keyDown(int keyCode)
    {
        if (keyCode == Keys.C)
            scene.loadNextSegment();

        return false;
    }
}
