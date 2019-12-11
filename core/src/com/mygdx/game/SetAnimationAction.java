package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 *  This is directly from the book Java Game Development with LibGDX written by Lee Stemkoski
 */

public class SetAnimationAction extends Action{

    protected Animation animationToDisplay;
    public SetAnimationAction(Animation a)
    {
        animationToDisplay = a;
    }
    public boolean act(float dt)
    {
        BaseActor ba = (BaseActor)target;
        ba.setAnimation( animationToDisplay );
        return true;
    }

}
