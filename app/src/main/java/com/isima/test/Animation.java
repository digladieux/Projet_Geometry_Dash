package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

class Animation {

    private Bitmap[] frames ;
    private int frameIndex ;

    private boolean isPLaying = false;
    private float frameTime; /* temps entre les frame */
    private long lastFrame;

    Animation(Bitmap[] frames, float animTime) {
        this.frames = frames ;
        frameIndex = 0 ;
        /* animTime temps total de animation et on les egalise */
        frameTime = animTime/frames.length ;

        lastFrame = System.currentTimeMillis();
    }

    boolean isPLaying() {
        return isPLaying;
    }

    void play() {
        frameIndex = 0;
        isPLaying = true;
        lastFrame = System.currentTimeMillis();
    }

    void stop() {
        isPLaying = false;
    }

    public void draw(Canvas canvas, Rect destination)
    {
        /* drawBitmat prend une bitmap, la proportion de l'image que l'on veut prendre (rogner ou pas ?), le rectangle où on va afficher la bitmap et le dessin */
        if (!isPLaying) {
            return;
        }
        /* Une image s'adapte parfaitement a sa destination. Pour eviter d'etirer l'image, on la met a l'echelle pour qu'elle conserve sa taille */
        canvas.drawBitmap(frames[frameIndex], null, destination, new Paint());
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
