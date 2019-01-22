package com.isima.test;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.io.IOException;
import java.util.ArrayList;

class SceneManager {

    private final ArrayList <Scene> scenes = new ArrayList <>();
    static int ACTIVE_SCENE;

    SceneManager(Context context)
    {
        ACTIVE_SCENE = 0 ;
        scenes.add(new MenuScene(context)) ;
        scenes.add(new MapScene(context)) ;
        scenes.add(new GameScene(context)) ;
        scenes.add(new WiningScene(context)) ;

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
