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
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.wakeFromDream );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence( "Finally...." );
        addTextSequence( "After spending countless hours grinding away at my software development project." );
        addTextSequence( "I feel that I may have eased some of the stressed that is drilling within my being." );
        addTextSequence( "Currently....... I spent the last 3 days trying my best to take all the information from the " );
        addTextSequence( "book \"Java Game Development with LibGDX\" written by Lee Stemkoski, and man do I feel like I wasted a lot of time. " );
        addTextSequence( "The difficulty and and complexity of the book makes my head hurt......................................" );
        addTextSequence( "But even then, I feel more ambitious to try my best, even if I loose out on obtaining the grade I want." );
        addTextSequence( "Who know's.......... Personally, I think this can be a really big eye opener............. " );
        addTextSequence( "It may not be necessarily the greatest project but I feel that it would also interest some people who are " );
        addTextSequence("looking to also get into some form of game development .....");
        addTextSequence(".....");
        addTextSequence("Hopefully I can prove that it was worth all of the hassle.");
        addTextSequence("I mean I have a decent idea on how I want to do it but it will be a lot of learning that I am not totally prepared for...");
        addTextSequence("Right now all I want to do is imagine.");
        addTextSequence("Imagine the snow falling in the middle of the woods.");
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));

        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence("Imagining every single snowflake, gliding in the sky. Soaring in the wind.................");
        addTextSequence("And as the snowflake hits my face, I come to the realization that the snow isn't cold. Its... Warm");
        addTextSequence( ".................." );
        addTextSequence("I just want to stay here");
        addTextSequence( ".................." );
        addTextSequence( "I am comforted by the snow. " );
        addTextSequence( ".................." );
        addTextSequence( "Just... Keep me here............." );
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));


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
    {
        background.setAnimation( background.lateForClassHouse );
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(0) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence("BANG!!!!!!!!!!!!!!!!!!!!!");
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));

    }
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
