package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Jackson extends BaseActor {

    public Animation Curious;
    public Animation Laugh;
    public Animation Mad;
    public Animation Normal;
    public Animation Shocked;

    Jackson(float x, float y, Stage s){

        super(x, y, s);
        Curious = loadTexture("Jackson Curious.png");
        Laugh = loadTexture("Jackson Laugh.png");
        Mad = loadTexture("Jackson Mad.png");
        Normal = loadTexture("Jackson Normal.png");
        Shocked = loadTexture("Jackson Shocked.png");

    }
}
