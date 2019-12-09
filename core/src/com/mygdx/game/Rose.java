package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Rose extends BaseActor{
    public Animation normal;
    public Animation grin;

    Rose(float x, float y, Stage s){
        super(x,y,s);
        normal = loadTexture("Rose normal.png");
        grin = loadTexture("Rose normal.png");

    }

}
