package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

// Made by Darren


/**
 * This class is designed to hold multiple different backgrounds for the visual novel.
 *
 *
 * */

public class Background extends BaseActor {



    /** The Animation declaration is a imported library from the libGDX framework that stores a list of objects
     * representing a animated sequence. Sort of like a Key frame. This is going to simplify the transition between each
     * each background for the visual novel.
     * */

    // Each parameter is used for a different background inside of the visual novel.
    public Animation wakeFromDream;
    public Animation lateForClassHouse;
    public Animation lateForClassRunning;
    public Animation getReadyFast;
    public Animation runToTheKitchen;
    public Animation lateForClassGetARide;
    public Animation arriveToSchool;
    public Animation goToClass;
    public Animation theEnd;

    // This is a background constructor that the statement to inherit the constructor from the superclass BaseActor.
    public Background(float x,  float y, Stage s){
        super(x,y,s);

        // These variables are being stored into a string list in the loadTexture method inside of the BaseActor
        // class that we are extending from.
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
