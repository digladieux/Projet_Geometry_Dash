package com.isima.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import java.io.IOException;

public class MapScene implements Scene {

    private final Bitmap scaledBackground;
    private final Bitmap scaledDressing;
    private final Bitmap[] scaledButtonMap ;
    static int mapAvailable;
    static int activeMap ;
    private final MediaPlayer menuMusic;
    private boolean isMusic ;

    MapScene(Context context) {
        isMusic = false ;
        menuMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.menusong);

        activeMap = 1 ;
        GameScene.mapNumber = 0;

        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_menu);
        this.scaledButtonMap = new Bitmap[4] ;
        Bitmap mButtonMap[] = new Bitmap[4] ;
        mButtonMap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.earth);
        mButtonMap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.moon);
        mButtonMap[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mars);
        mButtonMap[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sun);
        Bitmap mDressing = BitmapFactory.decodeResource(context.getResources(), R.drawable.dress);
        this.scaledDressing = Bitmap.createScaledBitmap(mDressing, 100, 100, true);
        this.scaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);

        for (int i = 0 ; i < this.scaledButtonMap.length ; i ++)
        {
            this.scaledButtonMap[i] = Bitmap.createScaledBitmap(mButtonMap[i], 2 * Constants.SCREEN_WIDTH/7, Constants.SCREEN_HEIGHT/5, true);
        }

        SharedPreferences fileMapAvailable = context.getSharedPreferences("MapAvailable", Context.MODE_PRIVATE);
        mapAvailable = fileMapAvailable.getInt("map", 1);
    }

    @Override
    public void update() {

        if ( (!isMusic) && (SceneManager.ACTIVE_SCENE == 1))
        {
            menuMusic.start();
            isMusic = true ;
        }
    }

/*TODO : sous fonction beaucoup de répétition de code */
    @Override
    public void draw(Canvas canvas) {



        Rect src = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        Rect dest = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(scaledBackground, src, dest, null);

        Rect srcDressing = new Rect(0, 0, scaledDressing.getWidth() - 1, scaledDressing.getHeight() - 1);
        Rect destDressing= new Rect(Constants.SCREEN_WIDTH - 101, 0, Constants.SCREEN_WIDTH - 1, 100);
        canvas.drawBitmap(scaledDressing, srcDressing, destDressing, null);

        if (mapAvailable >= 1)
        {
            displayButton(canvas, scaledButtonMap[0], (float)1/7, (float)3/7, (float) 1/5, (float) 2/5) ;
        }

        if (mapAvailable >= 2)
        {
            displayButton(canvas, scaledButtonMap[1], (float)4/7, (float)6/7, (float) 1/5, (float) 2/5) ;
        }
        if (mapAvailable >= 3)
        {
            displayButton(canvas, scaledButtonMap[2], (float)1/7, (float)3/7, (float) 3/5, (float) 4/5) ;
        }

        if (mapAvailable >= 4)
        {
            displayButton(canvas, scaledButtonMap[3], (float)4/7, (float)6/7, (float) 3/5, (float) 4/5) ;
        }

    }
    private void displayButton(Canvas canvas, Bitmap scaledButton, float areaLeft, float areaRight, float areaTop, float areaBottom)
    {
        Rect srcButton = new Rect(0, 0, scaledButton.getWidth() - 1, scaledButton.getHeight() - 1);
        Rect destButton = new Rect((int) (areaLeft * Constants.SCREEN_WIDTH - 1), (int) (areaTop * Constants.SCREEN_HEIGHT - 1), (int) (areaRight * Constants.SCREEN_WIDTH - 1), (int) (areaBottom * Constants.SCREEN_HEIGHT - 1));
        canvas.drawBitmap(scaledButton, srcButton, destButton, null);
    }
    @Override
    public void terminate() {
        menuMusic.stop();
        try {
            menuMusic.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuMusic.seekTo(0);
        isMusic = false ;
        SceneManager.ACTIVE_SCENE = 2;
    }

    @Override
    public void recieveTouch(MotionEvent event) {

        if (isMapDisplay(event, (float)1/7, (float)3/7, (float)1/5, (float)2/5, 1))
        {
            changingScene(1);

        } else if (isMapDisplay(event, (float)4/7, (float)6/7, (float)1/5, (float)2/5, 2))
        {
            changingScene(2);

        } else if (isMapDisplay(event, (float)1/7, (float)3/7, (float)3/5, (float)4/5, 3))
        {
            changingScene(3);

        } else if (isMapDisplay(event, (float)4/7, (float)6/7, (float)3/5, (float)4/5, 4))
        {
            changingScene(4);
        }
        if ((event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > Constants.SCREEN_WIDTH - 100) && (event.getRawY() < 100))
        {
//            this.terminate();
        }
    }

    private void changingScene(int mapNumber) {
        activeMap = mapNumber ;
        GameScene.mapNumber = mapNumber;
        this.terminate();
    }

    private boolean isMapDisplay(MotionEvent event, float areaLeft, float areaRight, float areaTop, float areaBottom, int mapAvailable)
    {
        return (event.getAction() == MotionEvent.ACTION_UP)
                && (event.getRawX() >= areaLeft * Constants.SCREEN_WIDTH)
                && (event.getRawX() <= areaRight * Constants.SCREEN_WIDTH)
                && (event.getRawY() >= areaTop * Constants.SCREEN_HEIGHT)
                && (event.getRawY() <= areaBottom * Constants.SCREEN_HEIGHT)
                && (MapScene.mapAvailable >= mapAvailable);
    }
}


