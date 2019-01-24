package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import static com.isima.test.StaticMethod.createPicture;
import static com.isima.test.StaticMethod.drawBitmap;
import static com.isima.test.StaticMethod.drawBitmap;
import static com.isima.test.StaticMethod.drawBitmapBackground;
import static com.isima.test.StaticMethod.isButtonClick;

public class MenuScene implements Scene {

    private Bitmap scaledBackground;
    private final Bitmap scaledStart;
    private final Bitmap scaledDressing;
    private final Bitmap scaledRewards;

    private boolean goingDressingScene ;
    private boolean goingRewards;

    MenuScene(Context context)
    {
        goingDressingScene = false ;
        goingRewards = false ;
        this.scaledBackground  = createPicture(context, R.drawable.background_menu, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.scaledStart  = createPicture(context, R.drawable.start, 3 * Constants.SCREEN_WIDTH/9, Constants.SCREEN_HEIGHT/5);
        this.scaledDressing  = createPicture(context, R.drawable.dress, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/10);
        this.scaledRewards  = createPicture(context, R.drawable.rewards, Constants.SCREEN_WIDTH/10, Constants.SCREEN_WIDTH/10);

    }

    @Override
    public void update() {

    }


    @Override
    public void draw(Canvas canvas) {
        /*int color ;
        int A, R, G, B ;

        for (int i = 0 ; i < Constants.SCREEN_WIDTH ; i++)
        {
            for (int j = 0 ; j < Constants.SCREEN_HEIGHT ; j++)
            {
                color = scaledBackground.getPixel(i,j) ;
                A = (color >> 24) & 0xff; // or color >>> 24
                R = (color >> 16) & 0xff;
                G = (color >>  8) & 0xff;
                B = (color      ) & 0xff;
                A = 1;
                color = (A & 0xff) << 24 | (R & 0xff) << 16 | (G & 0xff) << 8 | (B & 0xff);

                color = 0x00ffff00;
                scaledBackground.setPixel(i,j,color);
            }
        }
        canvas.drawColor(Color.BLACK);
        */
        drawBitmapBackground(canvas, scaledBackground);
        drawBitmap(canvas, scaledStart,(float)1/2,(float)1/2 );
        drawBitmap(canvas, scaledDressing, (float)1/3, (float) 4/5);
        drawBitmap(canvas, scaledRewards, (float)2/3, (float) 4/5);
    }



    @Override
    public void terminate() {

        if (goingDressingScene) {
            SceneManager.ACTIVE_SCENE = 4;
            goingDressingScene = false;
        } else if (goingRewards) {
            SceneManager.ACTIVE_SCENE = 5;
            goingRewards = false;
        } else {

            SceneManager.ACTIVE_SCENE = 1;
        }
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if (isButtonClick(event, scaledDressing, (float)1/3, (float)4/5))
        {
           goingDressingScene = true ;
           this.terminate();
        }
        else if (isButtonClick(event, scaledRewards, (float)2/3, (float)4/5))
        {
            goingRewards = true ;
            this.terminate();
        }
        else if (isButtonClick(event, scaledStart, (float) 3.5/7, (float) 2.5/5))
        {
            this.terminate();
        }
    }

}
