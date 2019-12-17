package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.graphics.g2d.Animation;

// Made by Darren


/***
 * This class creates the character Rose. She is used in the story when you choose the get ready option.
 */

// Extends the baseActor class to initialize the actor to be apart of the group of images that are used in the gamm.
public class Rose extends BaseActor{

    // Each parameter will be a different expression that rose has.
    public Animation normal;
    public Animation grin;

    // A constructor to load rose into the mainStage screen.
    Rose(float x, float y, Stage s){

        // Sharing the elements of baseActor to this constructor
        super(x,y,s);

        // Setting each parameter with a texture
        normal = loadTexture("Rose normal.png");
        grin = loadTexture("Rose grin.png");



    }

}
