package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

;import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.mygdx.game.BaseGame.setActiveScreen;


// Made by Darren
public class StoryScreen extends BaseScreen {
    Scene scene;
    Background background;
    Rose rose;
    Jackson jackson;
    Teacher teacher;
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
        ArrayList<String> wakeFromDreamText = textFileReader("wakeFromDream.txt");
        ArrayList<String> wakeFromDreamTextpt2 = textFileReader("wakeFromDreampt2.txt");
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.wakeFromDream );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        readArray(wakeFromDreamText);
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        readArray(wakeFromDreamTextpt2);
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { lateForClassHouse(); }) ));


        scene.start();
    }
    public void lateForClassHouse()
    {
        ArrayList<String> lateForClassHouseText = textFileReader("lateForClassHouse.txt");
        scene.clearSegments();
        background.setAnimation( background.lateForClassHouse );
        dialogBox.setText("");
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(0) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        readArray(lateForClassHouseText);
        scene.addSegment( new SceneSegment( buttonTable, Actions.show() ));
// set up options
        TextButton runToSchoolButton = new TextButton("Get your stuff and leave right away!", BaseGame.textButtonStyle);
        runToSchoolButton.addListener(
                (Event e) ->
                {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(Type.touchDown) )
                        return false;
                    scene.addSegment( new SceneSegment( buttonTable, Actions.hide() ));
                    addTextSequence( "Im going to just bite the bullet and try to get there as soon as possible." );
                    addTextSequence( "I dont have time to waste. I got to be on time." );
                            scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
                    scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideLeft(0) ));
                    scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
                    scene.addSegment( new SceneSegment( background, Actions.run(() -> { lateForClassRunning(); }) ));
                    return false;
                }
        );

        TextButton getReadyQuicklyButton = new TextButton("Get ready as fast as possible", BaseGame.textButtonStyle);
        getReadyQuicklyButton.addListener(
                (Event e) ->
                {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(Type.touchDown) )
                        return false;
                    scene.addSegment( new SceneSegment( buttonTable, Actions.hide() ));
                    addTextSequence( "There is no way im leaving the house without getting ready for the day." );
                    addTextSequence( "Im going to try to get ready as quickly as possible." );
                    scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));
                    scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideRight(0) ));
                    scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
                    scene.addSegment( new SceneSegment( background, Actions.run(() -> { getReadyFast(); }) ));
                    return false;
                }
        );


        buttonTable.clearChildren();
        buttonTable.add(runToSchoolButton);
        buttonTable.row();
        buttonTable.add(getReadyQuicklyButton);

        scene.start();




    }
    public void lateForClassRunning()
    {
        ArrayList<String> lateForClassRunningText = textFileReader("lateForClassRunning.txt");
        scene.clearSegments();
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.lateForClassGetARide );
        dialogBox.setText("");
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        readArray(lateForClassRunningText);
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { arriveToSchool(); }) ));



        scene.start();



    }

    public void getReadyFast()
    {
        ArrayList<String> getReadyFastText = textFileReader("getReadyFast.txt");
        scene.clearSegments();

        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.getReadyFast );
        dialogBox.setText("");
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        readArray(getReadyFastText);
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { runToTheKitchen(); }) ));



        scene.start();
    }

    public void runToTheKitchen()
    {
        ArrayList<String> runToTheKitchenText = textFileReader("runToTheKitchen.txt");
        ArrayList<String> runToKitchenTextPt2 = textFileReader("runToTheKitchenpt2.txt");
        scene.clearSegments();
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.runToTheKitchen );
        dialogBox.setText("");
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        readArray(runToTheKitchenText);
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToScreenCenter(1)));
        readArray(runToKitchenTextPt2);
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideRight(1)));
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { lateForClassGetARide(); }) ));



        scene.start();
    }

    public void lateForClassGetARide()
    {
        ArrayList<String> lateForClasssGetARide = textFileReader("lateForClassGetARide.txt");
        scene.clearSegments();
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.lateForClassGetARide );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToScreenCenter(1)));
        readArray(lateForClasssGetARide);
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideRight(1)));
        rose.setSize(480,600);
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { arriveToSchool(); }) ));


        scene.start();


    }
    public void arriveToSchool()
    {
        ArrayList<String> arriveToSchoolText = textFileReader("arriveToSchool.txt");
        scene.clearSegments();
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.arriveToSchool );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        readArray(arriveToSchoolText);
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { goToClass(); }) ));

        scene.start();

    }
    public void goToClass()
    {
        scene.clearSegments();
        background.setAnimation( background.goToClass );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(0) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence("Right as I get into class I realize that nobody was here except the professor");
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { theEnd(); }) ));

        scene.start();

    }

    public void theEnd(){

        scene.clearSegments();
        background.setAnimation( background.theEnd );
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence("Thank you for playing.");
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> {setActiveScreen(new MenuScreen()); }) ));

        scene.start();

    }

    public void update(float dt)
    { }
    public boolean keyDown(int keyCode)
    {
        if (keyCode == Keys.C)
            scene.loadNextSegment();

        return false;
    }

    public ArrayList<String> textFileReader (String fileName){
        ArrayList<String> sentences  = new ArrayList<>();
        try {


            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null){

                sentences.add(line);
            }
            br.close();
        }catch (IOException ioe){

            System.out.println("File not found");
        }
        return sentences;

    }

    public void  readArray(ArrayList<String> text){
        for (String s : text){
            addTextSequence(s);
        }
    }
}
