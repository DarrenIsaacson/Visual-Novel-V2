package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Animation;

/***
 * This was made by me.
 *
 *
 * Currently its not being used inside of the project due to the time required for this to be done, but this is additionally a character that I was anticipating to have in the visual novel.
 * Very similar to the Background class.
 */

public class Jackson extends BaseActor {

    // Parameters that have different moods for the character.
    public Animation Curious;
    public Animation Laugh;
    public Animation Mad;
    public Animation Normal;
    public Animation Shocked;

    // Constructor for this character model
    Jackson(float x, float y, Stage s){

        // Extending the properties and methiods from baseActor to this constructor
        super(x, y, s);

        // Sets each picture filename to each emotion
        Curious = loadTexture("Jackson Curious.png");
        Laugh = loadTexture("Jackson Laugh.png");
        Mad = loadTexture("Jackson Mad.png");
        Normal = loadTexture("Jackson Normal.png");
        Shocked = loadTexture("Jackson Shocked.png");

    }
}
