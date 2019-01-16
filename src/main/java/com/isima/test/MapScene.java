package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class MapScene implements Scene {

    private final Bitmap scaledBackground;

    MapScene(Context context)
    {
        GameScene.mapNumber = 0 ;
        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_menu);
        this.scaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        Rect dest = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(scaledBackground, src, dest, null);
        drawPaint(canvas, "Easy",Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT/4 );
        drawPaint(canvas, "Medium",3 * Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT/4 );
        drawPaint(canvas, "Hard",Constants.SCREEN_WIDTH/4, 3 * Constants.SCREEN_HEIGHT/4 );
        drawPaint(canvas, "Impossible",3 * Constants.SCREEN_WIDTH/4, 3 * Constants.SCREEN_HEIGHT/4 );

    }

    private void drawPaint(Canvas canvas, String text, int x, int y)
    {
        Paint paintMap = new Paint();
        paintMap.setTextSize(100);
        paintMap.setColor(Color.RED);
        paintMap.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, x, y, paintMap);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 2 ;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if ( (event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() < Constants.SCREEN_WIDTH/2) && (event.getRawY() < Constants.SCREEN_HEIGHT/2))
        {
            changingScene(1);

        }
        else if ( (event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > Constants.SCREEN_WIDTH/2) && (event.getRawY() < Constants.SCREEN_HEIGHT/2))
        {
            changingScene(2);

        }
        else if ( (event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() < Constants.SCREEN_WIDTH/2) && (event.getRawY() > Constants.SCREEN_HEIGHT/2))
        {
            changingScene(3);

        }
        else if ( (event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > Constants.SCREEN_WIDTH/2) && (event.getRawY() > Constants.SCREEN_HEIGHT/2))
        {
            changingScene(4);
        }

    }

    private void changingScene(int mapNumber)
    {
        GameScene.mapNumber = mapNumber ;
        this.terminate();
    }
}
