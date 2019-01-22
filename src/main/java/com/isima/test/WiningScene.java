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

import java.io.IOException;

public class WiningScene implements Scene {

    private final Bitmap scaledBackground;
    private long winningTime;
    private final Context context ;
    private final MediaPlayer winningMusic;
    WiningScene(Context context)
    {
        this.context = context ;
        winningMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.winningsong);
        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_winning);
        this.scaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);
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
        Rect src = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        Rect dest = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(scaledBackground, src, dest, null);
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

    }
}
