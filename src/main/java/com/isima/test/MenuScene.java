package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class MenuScene implements Scene {

    private final Bitmap mScaledBackground;
    private final Bitmap mScaledStart;

    MenuScene(Context context)
    {
        Bitmap mStart = BitmapFactory.decodeResource(context.getResources(), R.drawable.start);
        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_menu);
        this.mScaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);
        this.mScaledStart =Bitmap.createScaledBitmap(mStart, 3 * Constants.SCREEN_WIDTH/7, Constants.SCREEN_HEIGHT/5, true);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, mScaledBackground.getWidth() - 1, mScaledBackground.getHeight() - 1);
        Rect dest = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(mScaledBackground, src, dest, null);

        Rect srcStart = new Rect(0, 0, mScaledStart.getWidth() - 1, mScaledStart.getHeight() - 1);
        Rect destStart = new Rect(2 * Constants.SCREEN_WIDTH/7, 2 * Constants.SCREEN_HEIGHT/5,5 * Constants.SCREEN_WIDTH/7, 3 * Constants.SCREEN_HEIGHT/5);
        canvas.drawBitmap(mScaledStart, srcStart, destStart, null);

    }



    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 1;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if ((event.getAction() == MotionEvent.ACTION_UP)
                && (event.getRawX() >= 2 * Constants.SCREEN_WIDTH /7)
                && (event.getRawX() <= 5 * Constants.SCREEN_WIDTH / 7)
                && (event.getRawY() >= 2 * Constants.SCREEN_HEIGHT / 5)
                && (event.getRawY() <= 3 * Constants.SCREEN_HEIGHT /5))
        {
            this.terminate();
        }
    }

}
