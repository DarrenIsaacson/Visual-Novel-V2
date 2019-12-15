package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

;import static com.mygdx.game.BaseGame.setActiveScreen;


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
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { lateForClassHouse(); }) ));


        scene.start();
    }
    public void lateForClassHouse()
    {
        scene.clearSegments();
        background.setAnimation( background.lateForClassHouse );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(0) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence("Ouch....");
        addTextSequence("Awe man.");
        addTextSequence("I think I may have fell out of the bed.. ");
        addTextSequence("*Scratches head* What time is it.............");
        addTextSequence("10:30! Oh no! I have class at 11!");
        addTextSequence("Crap!");
        addTextSequence("Normally it takes me 30 minutes to get ready in the morning and it takes me 30 minutes to get to school!!");
        addTextSequence("I really dont want to go to school smelling bad! What should I do!");

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



//        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
//        scene.addSegment( new SceneSegment( background, Actions.run(() -> { lateForClassRunning(); }) ));


        buttonTable.clearChildren();
        buttonTable.add(runToSchoolButton);
        buttonTable.row();
        buttonTable.add(getReadyQuicklyButton);

        scene.start();




    }
    public void lateForClassRunning()
    {
        scene.clearSegments();
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.lateForClassGetARide );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence("Man is this exhausting rushing to school.");
        addTextSequence("If I would of taken the time to get ready I wouldn't have been......");
        addTextSequence("Nevermind that, I just got to get to school and hopefully get to school on time");
        addTextSequence("I hope I dont end up there with my clothes soaking with sweat.");
        addTextSequence("That would be gross.");
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { arriveToSchool(); }) ));



        scene.start();



    }

    public void getReadyFast()
    {
        scene.clearSegments();
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.getReadyFast );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence("Getting ready as fast as possible has become pretty stressful");
        addTextSequence("So far I took a quick shower, brushed my teeth, combed my hair, and put on deodorant.");
        addTextSequence("Now I just gotta grab something to eat really quickly.");
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { runToTheKitchen(); }) ));



        scene.start();
    }

    public void runToTheKitchen()
    {
        scene.clearSegments();
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.runToTheKitchen );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence("Okay. Now I have all of my food, I gotta rush to class. This really stinks");
        addTextSequence("AHHHHHHHHHHHHHHHHHH!!!!!!!!!!!!!");
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToScreenCenter(1)));
        addTextSequence("(Rose) Sorry I didn't mean to scare you. I didn't have class today so I was just relaxing on the couch.");
        addTextSequence("(Rose) Are you okay?");
        addTextSequence("Yeah im okay? Actually I am in a rush. Do you think that you could give me a ride to work.");
        addTextSequence("(Rose)Yeah sure thing, but you owe me food later. Haha");
        addTextSequence("You got a deal.");
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideRight(1)));
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { lateForClassGetARide(); }) ));



        scene.start();
    }

    public void lateForClassGetARide()
    {
        scene.clearSegments();
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.lateForClassGetARide );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToScreenCenter(1)));
        addTextSequence("(Rose) You are totally so lucky that I didn't have school today. All of my finals happen to be finished.");
        addTextSequence("Honestly, I thought I had the alarm set for the right time.");
        addTextSequence("(Rose) Conveniently, we live 10 min away. So this will be a quick ride. ");
        addTextSequence("Exactly. Thanks again for the ride today.");
        addTextSequence("(Rose) No problem. ");
        addTextSequence("(Rose) We are here now. Ill see you later! ");
        addTextSequence("Thanks ill see you later!");
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideRight(1)));
        rose.setSize(480,600);
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { arriveToSchool(); }) ));


        scene.start();


    }
    public void arriveToSchool()
    {
        scene.clearSegments();
        rose.addAction(SceneActions.moveToOutsideLeft(0));
        background.setAnimation( background.arriveToSchool );
        dialogBox.setText("");
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));
        addTextSequence("Finally I have arrived");
        addTextSequence("Every time I get to school I always daydream about the school being so huge.");
        addTextSequence("Class is usually at 11, so i should rush to my class.");
        addTextSequence("Hopefully I wont interrupt someone else's presentation");
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
}
