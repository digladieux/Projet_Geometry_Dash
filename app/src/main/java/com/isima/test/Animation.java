package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Animation {

    private Bitmap[] frames ;
    private int frameIndex ;
    private float frameTime; /* temps entre les frame */
    private long lastFrame;
    private boolean isPLaying = false ;


    Animation(Bitmap[] frames, float animTime) {
        this.frames = frames ;
        frameIndex = 0 ;
        /* animTime temps total de animation et on les egalise */
        frameTime = animTime/frames.length ;

        lastFrame = System.currentTimeMillis();
    }

    void play() {
        frameIndex = 0;
        isPLaying = true;
        lastFrame = System.currentTimeMillis() ;
    }

    public void draw(Canvas canvas, Rect destination)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(destination, paint);
        canvas.drawBitmap(frames[frameIndex] , null , destination , new Paint());
    }


    public void update()
    {
        if (!isPLaying)
        {
            return ;
        }
        /* Si l'anim a depasse son temps d'etre a l'ecran on change */
        if (System.currentTimeMillis() - lastFrame > frameTime*1000)
        {
            frameIndex ++ ;
            frameIndex = frameIndex >= frames.length ? 0 : frameIndex ;
            lastFrame = System.currentTimeMillis() ;
        }
    }
}
