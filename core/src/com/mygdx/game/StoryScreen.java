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

/***
 * This is where the main body of the story creation will take place.
 *
 */

public class StoryScreen extends BaseScreen {

    // Each parameter is needed to make the visual novel come to life. Each parameter is initializing a new part that will bring the visual novel to life.

    Scene scene;
    Background background;
    Rose rose;
    Jackson jackson;
    Teacher teacher;
    DialogBox dialogBox;
    BaseActor continueKey;
    Table buttonTable;

    public void initialize()
    {
        background = new Background(0,0, mainStage); // Sets a new background first
        background.setOpacity(0);
        BaseActor.setWorldBounds(background);

        rose = new Rose(0,0, mainStage); // Sets the character rose on screen
        rose.setSize(480,600);



        dialogBox = new DialogBox(0,0, uiStage); // Sets the dialog box on top of rose
        dialogBox.setDialogSize(600, 150);
        dialogBox.setBackgroundColor( new Color(0.2f, 0.2f, 0.2f, 1) );
        dialogBox.setVisible(false);

        continueKey = new BaseActor(0,0,uiStage); // sets the continue button on top of the dialog box
        continueKey.loadTexture("key-C.png");
        continueKey.setSize(32,32);
        continueKey.setVisible(false);

        dialogBox.addActor(continueKey); // uses the dialog box add actor parameter inside of the constructor to declare the button image.
        continueKey.setPosition( dialogBox.getWidth() - continueKey.getWidth(), 0 ); // sets the position of the button image

        buttonTable = new Table(); // creates a button table for the options when it comes down to the time of selecting a option in the store.
        buttonTable.setVisible(false);

        // Ui table adjust the columns and rows used in the row
        uiTable.add().expandY();
        uiTable.row();
        uiTable.add(buttonTable);
        uiTable.row();
        uiTable.add(dialogBox);


        // initializes a new scene so that they array for scene and the scene segments are clear
        scene = new Scene();

        // sets the mainStage Actor as scene so that when a scene is declared its shown on the interface
        mainStage.addActor(scene);

        // calls the first method that holds the introduction of the first part of the story
        wakeFromDream();


    }


    // This method helps display the text, and makes the continue key visible, then pauses the text, then makes the key invisible.
    public void addTextSequence(String s)
    {

        scene.addSegment( new SceneSegment( dialogBox, SceneActions.typewriter(s) ));
        scene.addSegment( new SceneSegment( continueKey, Actions.show() ));
        scene.addSegment( new SceneSegment( background, SceneActions.pause() ));
        scene.addSegment( new SceneSegment( continueKey, Actions.hide() ));
    }

    // This is the first part of the story

    /**
     * Within each method of the story you will find a collection of sequential scenes that are set up strategically
     * to show up on screen in a specific order. These scenes are added to a array found in scenes
     * class and will generate each scene onto the interface one by one till the array is finished.
     * Each scene can dictate a specific background fading in and out of the interface, a dialog box fading in and out
     * of the interface, what text will show up on the interface, and more
     */

    public void wakeFromDream()
    {
        // Array list that will store each sentence found inside of the textFileReader Method.
        ArrayList<String> wakeFromDreamText = textFileReader("wakeFromDream.txt");
        ArrayList<String> wakeFromDreamTextpt2 = textFileReader("wakeFromDreampt2.txt");

        // stores the character model outside of the screen
        rose.addAction(SceneActions.moveToOutsideLeft(0));

        // sets the background animation
        background.setAnimation( background.wakeFromDream );

        // cleares the dialog box text
        dialogBox.setText("");

        // shows the dialog box
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

        // This is a method that will simply read each line and add it within the scene array to generate text onto the dialog box
        readArray(wakeFromDreamText);

        // Hides the dialog box
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));

        // The background image fades in
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));


        // Clears the dialog box text again
        dialogBox.setText("");

        // Shows the dialog box
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

        // Read each element out
        readArray(wakeFromDreamTextpt2);

        // Hides the dialog box
        scene.addSegment( new SceneSegment( dialogBox, Actions.hide() ));

        // Background fades out
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        // next scene is prompt
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { lateForClassHouse(); }) ));


        // initializes to start the array loop for each scene
        scene.start();
    }

    // This is the second part of the story (This part specifically gives the player an option)
    public void lateForClassHouse()
    {
        // Collect resources from the textfile
        ArrayList<String> lateForClassHouseText = textFileReader("lateForClassHouse.txt");

        // clear the array from the old scenes
        scene.clearSegments();

        // sets the background
        background.setAnimation( background.lateForClassHouse );

        // clears the dialog box
        dialogBox.setText("");

        // moves rose out of the interface
        rose.addAction(SceneActions.moveToOutsideLeft(0));

        // background fades in
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(0) ));

        // Dialog box shows
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() ));

        // reads the array sentences
        readArray(lateForClassHouseText);


        // Displays button table
        scene.addSegment( new SceneSegment( buttonTable, Actions.show() ));

        // Generates a button for running to school
        TextButton runToSchoolButton = new TextButton("Get your stuff and leave right away!", BaseGame.textButtonStyle);

        // creates a eventlistener for the button
        runToSchoolButton.addListener(
                (Event e) ->
                {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(Type.touchDown) )
                        return false;
                    // hides the button options
                    scene.addSegment( new SceneSegment( buttonTable, Actions.hide() ));

                    // Message for picking the running to school option
                    addTextSequence( "Im going to just bite the bullet and try to get there as soon as possible." );
                    addTextSequence( "I dont have time to waste. I got to be on time." );
                            scene.addSegment( new SceneSegment( dialogBox, Actions.hide() )); // Hides dialog box
                    scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideLeft(0) ));

                    // Background fades out
                    scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

                    // next scene is prompt
                    scene.addSegment( new SceneSegment( background, Actions.run(() -> { lateForClassRunning(); }) ));
                    return false;
                }
        );

        // Generates a button for getting ready for school
        TextButton getReadyQuicklyButton = new TextButton("Get ready as fast as possible", BaseGame.textButtonStyle);
        getReadyQuicklyButton.addListener(
                (Event e) ->
                {
                    if ( !(e instanceof InputEvent) ||
                            !((InputEvent)e).getType().equals(Type.touchDown) )
                        return false;
                    // hides the button options
                    scene.addSegment( new SceneSegment( buttonTable, Actions.hide() ));

                    // Message for picking the getting ready option
                    addTextSequence( "There is no way im leaving the house without getting ready for the day." );
                    addTextSequence( "Im going to try to get ready as quickly as possible." );
                    scene.addSegment( new SceneSegment( dialogBox, Actions.hide() )); // Hides dialog box
                    scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideRight(0) )); // moves rose to the outside right side
                    scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) )); // background fades out
                    scene.addSegment( new SceneSegment( background, Actions.run(() -> { getReadyFast(); }) )); // next method initiates
                    return false;
                }
        );

        // clears previous buttons that may have been up
        buttonTable.clearChildren();

        // set buttons up by row
        buttonTable.add(runToSchoolButton);
        buttonTable.row();
        buttonTable.add(getReadyQuicklyButton);

        // starts the array looping processes
        scene.start();




    }

    // This is the scene that will generate if the player chooses to leave right away
    public void lateForClassRunning()
    {
        // gathers the String resources from the text file
        ArrayList<String> lateForClassRunningText = textFileReader("lateForClassRunning.txt");

        // clears the scene array
        scene.clearSegments();

        // moves rose out of frame
        rose.addAction(SceneActions.moveToOutsideLeft(0));

        // sets the background
        background.setAnimation( background.lateForClassGetARide );

        // Clears dialog box
        dialogBox.setText("");

        // stores the character model outside of the screen
        rose.addAction(SceneActions.moveToOutsideLeft(0));

        // background fades in
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));

        // Dialog box shows
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() )); // shows the dialog box

        // Read each element out
        readArray(lateForClassRunningText);

        // Background fades out
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        // next scene is prompt
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { arriveToSchool(); }) ));



        // initializes to start the array loop for each scene
        scene.start();



    }

    // This is the scene that will generate if the player chooses to get ready fast
    public void getReadyFast()
    {
        ArrayList<String> getReadyFastText = textFileReader("getReadyFast.txt");

        // clear the array from the old scenes
        scene.clearSegments();

        // stores the character model outside of the screen
        rose.addAction(SceneActions.moveToOutsideLeft(0));

        // sets the background
        background.setAnimation( background.getReadyFast );
        // Clears dialog box
        dialogBox.setText("");

        // background fades in
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));

        // Dialog box shows
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() )); // shows the dialog box

        // Read each element out
        readArray(getReadyFastText);

        // Background fades out
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        // next scene is prompt
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { runToTheKitchen(); }) ));



        // initializes to start the array loop for each scene
        scene.start();
    }

    // This is a scene corresponding after the getreadfast scene. This also introduces Rose
    public void runToTheKitchen()
    {
        ArrayList<String> runToTheKitchenText = textFileReader("runToTheKitchen.txt");
        ArrayList<String> runToKitchenTextPt2 = textFileReader("runToTheKitchenpt2.txt");

        // clear the array from the old scenes
        scene.clearSegments();

        // sets the background
        background.setAnimation( background.runToTheKitchen );
        // Clears dialog box
        dialogBox.setText("");

        // stores the character model outside of the screen
        rose.addAction(SceneActions.moveToOutsideLeft(0));

        // background fades in
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));

        // Dialog box shows
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() )); // shows the dialog box

        // Read each element out
        readArray(runToTheKitchenText);

        // Moves rose To center screen
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToScreenCenter(1)));

        // Read each element out
        readArray(runToKitchenTextPt2);

        // Moves rose To outside right screen
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideRight(1)));

        // Background fades out
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        // next scene is prompt
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { lateForClassGetARide(); }) ));



        // initializes to start the array loop for each scene
        scene.start();
    }

    // This scene is next after you meet rose. Rose gives the player a ride to school
    public void lateForClassGetARide()
    {
        ArrayList<String> lateForClasssGetARide = textFileReader("lateForClassGetARide.txt");

        // clear the array from the old scenes
        scene.clearSegments();

        // stores the character model outside of the screen
        rose.addAction(SceneActions.moveToOutsideLeft(0));

        // sets the background
        background.setAnimation( background.lateForClassGetARide );

        // Clears dialog box
        dialogBox.setText("");

        // background fades in
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));

        // Dialog box shows
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() )); // shows the dialog box
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToScreenCenter(1)));

        // Read each element out
        readArray(lateForClasssGetARide);

        // Moves rose To outside right screen
        scene.addSegment( new SceneSegment( rose, SceneActions.moveToOutsideRight(1)));

        // Background fades out
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        // next scene is prompt
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { arriveToSchool(); }) ));


        // initializes to start the array loop for each scene
        scene.start();


    }

    // This scene is meeting ground scene. Both options from the lateForClassHouse method meet here.
    public void arriveToSchool()
    {
        ArrayList<String> arriveToSchoolText = textFileReader("arriveToSchool.txt");

        // clear the array from the old scenes
        scene.clearSegments();

        // stores the character model outside of the screen
        rose.addAction(SceneActions.moveToOutsideLeft(0));

        // sets the background
        background.setAnimation( background.arriveToSchool );

        // Clears dialog box
        dialogBox.setText("");

        // background fades in
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(1) ));

        // Dialog box shows
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() )); // shows the dialog box

        // Read each element out
        readArray(arriveToSchoolText);

        // Background fades out
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        // next scene is prompt
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { goToClass(); }) ));

        // initializes to start the array loop for each scene
        scene.start();

    }

    // This is the scene that goes to the class inside of the school
    public void goToClass()
    {

        // clear the array from the old scenes
        scene.clearSegments();

        // sets the background
        background.setAnimation( background.goToClass );

        // Clears dialog box
        dialogBox.setText("");

        // background fades in
        scene.addSegment( new SceneSegment( background, Actions.fadeIn(0) ));
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() )); // shows the dialog box

        // Message for arriving to class
        addTextSequence("Right as I get into class I realize that nobody was here except the professor");

        // Background fades out
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        // next scene is prompt
        scene.addSegment( new SceneSegment( background, Actions.run(() -> { theEnd(); }) ));

        // initializes to start the array loop for each scene
        scene.start();

    }

    // This is the ending scene. This will send them back to menuScreen
    public void theEnd(){

        // clear the array from the old scenes
        scene.clearSegments();

        // sets the background
        background.setAnimation( background.theEnd );
        scene.addSegment( new SceneSegment( dialogBox, Actions.show() )); // shows the dialog box

        // Thanking the player
        addTextSequence("Thank you for playing.");

        // Background fades out
        scene.addSegment( new SceneSegment( background, Actions.fadeOut(1) ));

        // next scene is prompt
        scene.addSegment( new SceneSegment( background, Actions.run(() -> {setActiveScreen(new MenuScreen()); }) ));

        // initializes to start the array loop for each scene
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

    // This is the method that will convert each buffered line into a string element inside of a array
    public ArrayList<String> textFileReader (String fileName){
        ArrayList<String> sentences  = new ArrayList<>();

        String pathExtension = "";

        // Gets project path.
        String userDir = System.getProperty("user.dir");

        // This if-Else loop will check if the path directory has a forward slash or a back slash to help avoid the method crashing.
        if(userDir.contains("\\")){
            pathExtension = "Story_Text_Files\\";
        }else if (userDir.contains("/")){
            pathExtension = "Story_Text_Files/";
        }

        // Try catch statement to catch any errors with reading the text files
        try {


            // initializing the path name and textfile
            FileReader fr = new FileReader(pathExtension + fileName);

            // initializes and assigns the the file reader to the file buffer
            BufferedReader br = new BufferedReader(fr);

            // creates a string variable that will store the readline that bufferline finds
            String line;

            // A while loop that will keep looping until there is no more lines to assign to the line variable
            while ((line = br.readLine()) != null){

                // adds the lines to the sentence array
                sentences.add(line);
            }
            // closes the buffered reader
            br.close();

            // Catches any errors with finding the files
        }catch (IOException ioe){

            System.out.println("File not found");
        }
        // returns the sentence variable.
        return sentences;

    }

    // This method will collect each array and send each element to the addtextsequence method
    public void  readArray(ArrayList<String> text){
        for (String s : text){
            addTextSequence(s);
        }
    }
}
