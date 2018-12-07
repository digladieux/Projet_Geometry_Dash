package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Animation {

    private Bitmap[] frames ;
    private int frameIndex ;

    private boolean isPLaying = false ;

    public boolean isPLaying()
    {
        return isPLaying;
    }

    public void play()
    {
        frameIndex = 0 ;
        isPLaying = true ;
        lastFrame = System.currentTimeMillis() ;
    }

    public void stop()
    {
        isPLaying = false ;
    }

    private float frameTime ; /* temps entre les frame */

    private long lastFrame ;

    public Animation(Bitmap[]  frames, float animTime)
    {
        this.frames = frames ;
        frameIndex = 0 ;
        /* animTime temps total de animation et on les egalise */
        frameTime = animTime/frames.length ;

        lastFrame = System.currentTimeMillis() ;
    }

    public void draw(Canvas canvas, Rect destination)
    {
        /* drawBitmat prend une bitmap, la proportion de l'image que l'on veut prendre (rogner ou pas ?), le rectangle où on va afficher la bitmap et le dessin */
        if (!isPLaying)
        {
            return ;
        }
        /* Une image s'adapte parfaitement a sa destination. Pour eviter d'etirer l'image, on la met a l'echelle pour qu'elle conserve sa taille */
        scaleRect(destination) ;

        canvas.drawBitmap(frames[frameIndex] , null , destination , new Paint());
    }

    private void scaleRect(Rect rect)
    {
        float whRatio = (float)(frames[frameIndex].getWidth())/frames[frameIndex].getHeight();
        if(rect.width() > rect.height())
        {
            rect.left = rect.right - (int)(rect.height() * whRatio);
        }
        else
        {
            rect.top = rect.bottom - (int)(rect.width() * (1/whRatio));
        }
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
