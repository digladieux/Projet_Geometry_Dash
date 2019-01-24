package com.isima.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import java.io.IOException;

import static com.isima.test.StaticMethod.createPicture;
import static com.isima.test.StaticMethod.drawBitmap;
import static com.isima.test.StaticMethod.drawBitmapBackground;
import static com.isima.test.StaticMethod.drawBitmapReturn;
import static com.isima.test.StaticMethod.isButtonClick;

public class MapScene implements Scene {

    private final Bitmap scaledReturnMenu;

    private final Bitmap scaledBackground;
    private final Bitmap[] scaledButtonMap ;
    static int mapAvailable;
    static int activeMap ;
    private final MediaPlayer menuMusic;
    private boolean isMusic ;
    private boolean returnMenu;

    MapScene(Context context) {

        returnMenu = false ;
        isMusic = false ;
        menuMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.menusong);

        activeMap = 0 ;

        this.scaledButtonMap = new Bitmap[4] ;
        this.scaledReturnMenu  = createPicture(context, R.drawable.return_arrow, Constants.SCREEN_WIDTH/14,Constants.SCREEN_WIDTH/14);
        this.scaledBackground  = createPicture(context, R.drawable.background, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.scaledButtonMap[0]  = createPicture(context, R.drawable.earth, 3 * Constants.SCREEN_WIDTH/9, Constants.SCREEN_HEIGHT/5);
        this.scaledButtonMap[1]  = createPicture(context, R.drawable.moon, 3 * Constants.SCREEN_WIDTH/9, Constants.SCREEN_HEIGHT/5);
        this.scaledButtonMap[2]  = createPicture(context, R.drawable.mars, 3 * Constants.SCREEN_WIDTH/9, Constants.SCREEN_HEIGHT/5);
        this.scaledButtonMap[3]  = createPicture(context, R.drawable.sun, 3 * Constants.SCREEN_WIDTH/9, Constants.SCREEN_HEIGHT/5);

        SharedPreferences fileMapAvailable = context.getSharedPreferences("MapAvailable", Context.MODE_PRIVATE);
        mapAvailable = fileMapAvailable.getInt("map", 0);
    }

    @Override
    public void update() {

        if ( (!isMusic) && (SceneManager.ACTIVE_SCENE == 1))
        {
            menuMusic.start();
            isMusic = true ;
        }
    }

    @Override
    public void draw(Canvas canvas) {


        drawBitmapBackground(canvas, scaledBackground);
        drawBitmapReturn(canvas, scaledReturnMenu);

        if (mapAvailable >= 0)
        {
            drawBitmap(canvas, scaledButtonMap[0], (float)2.5/9,(float) 1.5/5) ;
        }
        if (mapAvailable >= 1)
        {
            drawBitmap(canvas, scaledButtonMap[1], (float)6.5/9, (float) 1.5/5) ;
        }
        if (mapAvailable >= 2)
        {
            drawBitmap(canvas, scaledButtonMap[2], (float)2.5/9,(float) 3.5/5) ;
        }

        if (mapAvailable >= 3)
        {
            drawBitmap(canvas, scaledButtonMap[3], (float)6.5/9,(float) 3.5/5) ;
        }

    }

    private void terminate()
    {
        if (returnMenu) {
            returnMenu = false;
            SceneManager.ACTIVE_SCENE = 0;
        }
        else
        {
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

    }

    @Override
    public void recieveTouch(MotionEvent event) {

        if (isButtonClick(event))
        {
            returnMenu = true ;
            this.terminate();
        }

        else if (isButtonClick(event, scaledButtonMap[0], (float) 2.5/9, (float) 1.5/5, 1))
        {
            changingScene(0);

        } else if (isButtonClick(event, scaledButtonMap[1], (float) 6.5/9, (float) 1.5/5, 2))
        {
            changingScene(1);

        } else if (isButtonClick(event, scaledButtonMap[2], (float) 2.5/9, (float) 3.5/5, 3))
        {
            changingScene(2);

        } else if (isButtonClick(event, scaledButtonMap[3], (float) 6.5/9, (float) 3.5/5, 4))
        {
            changingScene(3);
        }
    }

    private void changingScene(int mapNumber) {
        activeMap = mapNumber ;
        GameScene.mapNumber = mapNumber;
        this.terminate();
    }
}


