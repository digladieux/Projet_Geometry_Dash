package com.isima.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;

public class MapScene implements Scene {

    private final Bitmap scaledBackground;
    private final Bitmap[] scaledButtonMap ;
    static int mapAvailable;
    static short activeMap ;
    MediaPlayer menuMusic;


    MapScene(Context context) {

        menuMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.menusong);
        activeMap = 1 ;
        GameScene.mapNumber = 0;
        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_menu);
        Bitmap mButtonMap0 = BitmapFactory.decodeResource(context.getResources(), R.drawable.earth);
        Bitmap mButtonMap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.moon);
        Bitmap mButtonMap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.mars);
        Bitmap mButtonMap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sun);

        this.scaledButtonMap = new Bitmap[4] ;
        this.scaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);

        this.scaledButtonMap[0] = Bitmap.createScaledBitmap(mButtonMap0, Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/3, true);
        this.scaledButtonMap[1] = Bitmap.createScaledBitmap(mButtonMap1, Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/3, true);
        this.scaledButtonMap[2] = Bitmap.createScaledBitmap(mButtonMap2, Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/3, true);
        this.scaledButtonMap[3] = Bitmap.createScaledBitmap(mButtonMap3, Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/3, true);

        SharedPreferences fileMapAvailable = context.getSharedPreferences("MapAvailable", context.MODE_PRIVATE);
        mapAvailable = fileMapAvailable.getInt("map", 1);
    }

    @Override
    public void update() {
        if (!menuMusic.isPlaying())
        {
            menuMusic.start();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        Rect dest = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(scaledBackground, src, dest, null);

        if ((mapAvailable >= 1)  && (activeMap >= 1))
        {
            Rect srcButton1 = new Rect(0, 0, scaledButtonMap[0].getWidth() - 1, scaledButtonMap[0].getHeight() - 1);
            Rect destButton1 = new Rect(Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/8, Constants.SCREEN_WIDTH/8 + scaledButtonMap[0].getWidth(), Constants.SCREEN_HEIGHT/8 + scaledButtonMap[0].getHeight());
            canvas.drawBitmap(scaledButtonMap[0], srcButton1, destButton1, null);
        }

        if ((mapAvailable >= 2)  && (activeMap >= 1))
        {
            Rect srcButton2 = new Rect(0, 0, scaledButtonMap[1].getWidth() - 1, scaledButtonMap[1].getHeight() - 1);
            Rect destButton2 = new Rect(5 * Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/8, 5 * Constants.SCREEN_WIDTH/8 + scaledButtonMap[1].getWidth(), Constants.SCREEN_HEIGHT/8 + scaledButtonMap[1].getHeight());
            canvas.drawBitmap(scaledButtonMap[1], srcButton2, destButton2, null);
        }
        if ((mapAvailable >= 3)  && (activeMap >= 1))
        {
            Rect srcButton3 = new Rect(0, 0, scaledButtonMap[2].getWidth() - 1, scaledButtonMap[2].getHeight() - 1);
            Rect destButton3 = new Rect(Constants.SCREEN_WIDTH/8, 5 * Constants.SCREEN_HEIGHT/8, Constants.SCREEN_WIDTH/8 + scaledButtonMap[2].getWidth(), 5 * Constants.SCREEN_HEIGHT/8 + scaledButtonMap[2].getHeight());
            canvas.drawBitmap(scaledButtonMap[2], srcButton3, destButton3, null);
        }

        if ((mapAvailable >= 4)  && (activeMap >= 1))
        {
            Rect srcButton4 = new Rect(0, 0, scaledButtonMap[3].getWidth() - 1, scaledButtonMap[3].getHeight() - 1);
            Rect destButton4 = new Rect(5 * Constants.SCREEN_WIDTH/8, 5 * Constants.SCREEN_HEIGHT/8, 5 * Constants.SCREEN_WIDTH/8 + scaledButtonMap[3].getWidth(), 5 * Constants.SCREEN_HEIGHT/8 + scaledButtonMap[3].getHeight());
            canvas.drawBitmap(scaledButtonMap[3], srcButton4, destButton4, null);
        }

    }

    private void drawPaint(Canvas canvas, String text, int x, int y) {
        Paint paintMap = new Paint();
        paintMap.setTextSize(100);
        paintMap.setColor(Color.RED);
        paintMap.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, x, y, paintMap);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 2;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if ((event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() < Constants.SCREEN_WIDTH / 2) && (event.getRawY() < Constants.SCREEN_HEIGHT / 2) && (mapAvailable >= 1)) {
            activeMap = 1 ;
            changingScene(1);

        } else if ((event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > Constants.SCREEN_WIDTH / 2) && (event.getRawY() < Constants.SCREEN_HEIGHT / 2) && (mapAvailable >= 2)) {
            activeMap = 2 ;
            changingScene(2);

        } else if ((event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() < Constants.SCREEN_WIDTH / 2) && (event.getRawY() > Constants.SCREEN_HEIGHT / 2) && (mapAvailable >= 3)) {
            activeMap = 3 ;
            changingScene(3);

        } else if ((event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > Constants.SCREEN_WIDTH / 2) && (event.getRawY() > Constants.SCREEN_HEIGHT / 2)&& (mapAvailable >= 4)) {
            activeMap = 4 ;
            changingScene(4);
        }

    }

    private void changingScene(int mapNumber) {
        GameScene.mapNumber = mapNumber;
        menuMusic.stop();
        this.terminate();
    }
}
