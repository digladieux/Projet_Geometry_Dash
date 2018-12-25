package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class WiningScene implements Scene {

    private final Bitmap mScaledBackground;

    WiningScene(Context context)
    {
        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_menu);
        this.mScaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, mScaledBackground.getWidth() - 1, mScaledBackground.getHeight() - 1);
        Rect dest = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(mScaledBackground, src, dest, null);
        Paint paintMenu = new Paint();
        paintMenu.setTextSize(100);
        paintMenu.setColor(Color.GREEN);
        paintMenu.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Gagné!", Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, paintMenu);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 1 ;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            this.terminate();
        }

    }
}
