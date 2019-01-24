package com.isima.test;

import android.graphics.Canvas;
import android.view.MotionEvent;

interface Scene {

    void update();

    void draw(Canvas canvas);

    void recieveTouch(MotionEvent event);
}
