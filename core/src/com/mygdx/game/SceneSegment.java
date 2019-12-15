package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *  This is directly from the book Java Game Development with LibGDX written by Lee Stemkoski
 */


public class  SceneSegment
{
    private Actor actor;
    private Action action;
    
    public SceneSegment(Actor a1, Action a2)
    {
        actor = a1;
        action = a2;
    }
    
    public void start()
    {
        actor.clearActions();
        actor.addAction(action);
    }
    
    public boolean isFinished()
    {
        return (actor.getActions().size == 0);
    }
    
    /** 
     *  End this segment early
     */
    public void finish()
    {
        // simulate 100000 seconds elapsed time to complete in-progress action
        if ( actor.hasActions() )
            actor.getActions().first().act(100000);
        
        actor.clearActions();
    }
}