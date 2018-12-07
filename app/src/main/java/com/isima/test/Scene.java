package com.isima.test;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Scene {
    public void update() ;
    public void  draw(Canvas canvas) ;
    public void terminate() ; /* Changer de scene */

    public void recieveTouch(MotionEvent event) ;
}
