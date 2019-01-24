package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

class Animation {

    private final Bitmap[] pictures;
    private int pictureIndex = 0;

    private boolean isPLaying = false;
    private final float pictureTime; /* temps entre les frame */
    private long currentTimePicture;

    Animation(Bitmap[] pictures, float animTime) {
        this.pictures = pictures;
        pictureTime = animTime/ pictures.length ;
        currentTimePicture = System.currentTimeMillis();
    }

    boolean isPLaying() {
        return isPLaying;
    }

    void play() {
        pictureIndex = 0;
        isPLaying = true;
        currentTimePicture = System.currentTimeMillis();
    }

    void stop() {
        isPLaying = false;
    }

    public void draw(Canvas canvas, Rect destination)
    {
        if (isPLaying) {
            canvas.drawBitmap(pictures[pictureIndex], null, destination, new Paint());
        }
    }

    public void update()
    {
        if ((isPLaying) && (System.currentTimeMillis() - currentTimePicture > pictureTime *1000))
        {
                pictureIndex++ ;
                pictureIndex = pictureIndex >= pictures.length ? 0 : pictureIndex;
                currentTimePicture = System.currentTimeMillis() ;
        }
    }
}
