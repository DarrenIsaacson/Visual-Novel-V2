package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Teacher extends BaseActor{

    public Animation TeacherLookingLeft;
    public Animation TeacherLookingRight;

    Teacher(float x, float y, Stage s){

        super(x,y,s);
        TeacherLookingLeft = loadTexture("Teacher Looking Left.png");
        TeacherLookingRight = loadTexture("Teacher Looking Right.png");

    }
}
