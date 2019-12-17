package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

/***
 * This was made by me.
 *
 *
 * Currently its not being used inside of the project due to the time required for this to be done, but this is additionally a character that I was anticipating to have in the visual novel.
 * Very similar to the Background class.
 */


public class Teacher extends BaseActor{

    // Parameters that have different moods for the character.
    public Animation TeacherLookingLeft;
    public Animation TeacherLookingRight;

    // Constructor for this character model
    Teacher(float x, float y, Stage s){

        // Extending the properties and methiods from baseActor to this constructor
        super(x,y,s);

        // Sets each picture filename to each emotion
        TeacherLookingLeft = loadTexture("Teacher Looking Left.png");
        TeacherLookingRight = loadTexture("Teacher Looking Right.png");

    }
}
