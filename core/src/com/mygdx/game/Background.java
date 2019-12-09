package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;



public class Background extends BaseActor {
    public Animation wakeFromDream;
    public Animation lateForClassHouse;
    public Animation lateForClassRunning;
    public Animation lateForClassGetARide;
    public Animation arriveToSchool;
    public Animation goToClass;

    public Background(float x,  float y, Stage s){
        super(x,y,s);

        wakeFromDream = loadTexture("op_snowywoods.jpg");
        lateForClassHouse = loadTexture("school_dormlilly.jpg");
        lateForClassRunning = loadTexture("shizu_park.jpg");
        lateForClassGetARide = loadTexture("suburb_roadcenter.jpg");
        arriveToSchool = loadTexture("school_courtyard_ss.jpg");
        goToClass = loadTexture("school_council.jpg");
        setSize(1000,600);

    }

}
