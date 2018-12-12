package com.isima.test;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Scene {
    void update();

    void draw(Canvas canvas);

    void terminate(); /* Changer de scene */

    void recieveTouch(MotionEvent event);
}
