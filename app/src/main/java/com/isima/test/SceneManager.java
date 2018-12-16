package com.isima.test;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

class SceneManager {

    private final ArrayList <Scene> scenes = new ArrayList <>();
    static int ACTIVE_SCENE;

    SceneManager()
    {
        ACTIVE_SCENE = 0 ;
        scenes.add(new GamePlayScene()) ;
    }

    void receiveTouch(MotionEvent event)
    {
        scenes.get(ACTIVE_SCENE).recieveTouch(event) ;

    }

    public void update()
    {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas)
    {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
