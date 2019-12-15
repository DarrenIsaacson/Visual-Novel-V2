package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

// Made by Darren

public class Background extends BaseActor {
    public Animation wakeFromDream;
    public Animation lateForClassHouse;
    public Animation lateForClassRunning;
    public Animation getReadyFast;
    public Animation runToTheKitchen;
    public Animation lateForClassGetARide;
    public Animation arriveToSchool;
    public Animation goToClass;
    public Animation theEnd;

    public Background(float x,  float y, Stage s){
        super(x,y,s);

        wakeFromDream = loadTexture("op_snowywoods.jpg");
        lateForClassHouse = loadTexture("school_dormlilly.jpg");
        lateForClassRunning = loadTexture("shizu_park.jpg");
        getReadyFast = loadTexture("hok_bath.jpg");
        runToTheKitchen = loadTexture("hok_kitchen.jpg");
        lateForClassGetARide = loadTexture("suburb_roadcenter.jpg");
        arriveToSchool = loadTexture("school_courtyard_ss.jpg");
        goToClass = loadTexture("school_council.jpg");
        theEnd = loadTexture("the-end.png");
        setSize(1000,600);

    }

}
