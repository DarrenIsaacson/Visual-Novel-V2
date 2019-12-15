package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 *  This is directly from the book Java Game Development with LibGDX written by Lee Stemkoski
 */



/**
 *  Designed for use in concert with 
 *      the DialogBox, SceneSegment, and Scene classes.
 */
public class SetTextAction extends Action
{
    protected String textToDisplay;
    
    public SetTextAction(String s)
    {  
        textToDisplay = s;
    }
    
    public boolean act(float dt)
    {
        DialogBox db = (DialogBox)target;
        db.setText( textToDisplay );
        return true; // action completed
    }
}