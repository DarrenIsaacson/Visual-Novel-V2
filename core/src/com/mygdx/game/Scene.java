package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.ArrayList;

/**
 *  This is directly from the book Java Game Development with LibGDX written by Lee Stemkoski
 */

/***
 *  This manages a collection of objects.
 *
 *  This extends the Actor class because you can write and act method that will automatically
 *  advance through scene segments when corresponding actions have been completed.
 */

public class Scene extends Actor {
    private ArrayList<SceneSegment> segmentList;
    private int index;

    public Scene()
    {
        super();
        segmentList = new ArrayList<SceneSegment>();
        index = -1;
    }
    public void addSegment(SceneSegment segment)
    {
        segmentList.add(segment);
    }
    public void clearSegments()
    {
        segmentList.clear();
    }
    public void start()
    {
        index = 0;
        segmentList.get(index).start();
    }
    public void act(float dt)
    {
        if ( isSegmentFinished() && !isLastSegment() )
            loadNextSegment();
    }
    public boolean isSegmentFinished()
    {
        return segmentList.get(index).isFinished();
    }
    public boolean isLastSegment()
    {
        return (index >= segmentList.size() - 1);
    }
    public void loadNextSegment() {
        if (isLastSegment())
            return;
        segmentList.get(index).finish();
        index++;
        segmentList.get(index).start();
    }
    public boolean isSceneFinished()
    {
        return ( isLastSegment() && isSegmentFinished() );
    }


}
