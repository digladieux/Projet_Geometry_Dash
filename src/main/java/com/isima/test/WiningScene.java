package com.isima.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import java.io.IOException;

import static com.isima.test.StaticMethod.createPicture;
import static com.isima.test.StaticMethod.drawBitmap;
import static com.isima.test.StaticMethod.drawBitmapBackground;

public class WiningScene implements Scene {

    private final Bitmap scaledBackground;
    private long winningTime;
    private final Context context ;
    private final MediaPlayer winningMusic;
    WiningScene(Context context)
    {
        this.context = context ;
        winningMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.winningsong);

        this.scaledBackground  = createPicture(context, R.drawable.background_victory, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
    @Override
    public void update() {
        if (System.currentTimeMillis() - winningTime > 3000)
        {
            if (MapScene.mapAvailable == MapScene.activeMap)
            {
                SharedPreferences.Editor edit = context.getSharedPreferences("MapAvailable", Context.MODE_PRIVATE).edit();
                MapScene.mapAvailable ++ ;
                edit.putInt("map", MapScene.mapAvailable) ;
                edit.apply();
            }
            winningMusic.start();
            winningTime = System.currentTimeMillis();
        }
        else if (System.currentTimeMillis() - winningTime > 2000)
        {
            winningMusic.stop();

            try {
                winningMusic.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            winningMusic.seekTo(0);

            this.terminate();
        }
    }

    @Override
    public void draw(Canvas canvas) {

        drawBitmapBackground(canvas, scaledBackground);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 1 ;
    }

    @Override
    public void recieveTouch(MotionEvent event) {

    }
}
