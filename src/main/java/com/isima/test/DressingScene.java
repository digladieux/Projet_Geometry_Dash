package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import static com.isima.test.StaticMethod.createPicture;
import static com.isima.test.StaticMethod.drawBitmap;
import static com.isima.test.StaticMethod.drawBitmapBackground;
import static com.isima.test.StaticMethod.drawBitmapReturn;
import static com.isima.test.StaticMethod.isButtonClick;

public class DressingScene implements Scene {
    private final Bitmap scaledReturnMenu;
    private final Bitmap scaledBackground;
    private final Bitmap scaledNextDress ;
    private final Bitmap scaledPreviousDress ;
    private final Bitmap[] scaledAlienSprite ;

    DressingScene(Context context) {

        int widthDress = Constants.SCREEN_WIDTH/6 ;
        int heightDress = 3 * Constants.SCREEN_HEIGHT/4 ;

        this.scaledReturnMenu  = createPicture(context, R.drawable.return_arrow, Constants.SCREEN_WIDTH/14,Constants.SCREEN_WIDTH/14);
        this.scaledBackground  = createPicture(context, R.drawable.background, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.scaledNextDress  = createPicture(context, R.drawable.arrow_right, Constants.SCREEN_WIDTH/7, Constants.SCREEN_WIDTH/7);
        this.scaledPreviousDress  = createPicture(context, R.drawable.arrow_left, Constants.SCREEN_WIDTH/7,Constants.SCREEN_WIDTH/7);

        this.scaledAlienSprite = new Bitmap[5] ;
        this.scaledAlienSprite[0] = createPicture(context, R.drawable.alienblue, widthDress, heightDress);
        this.scaledAlienSprite[1] = createPicture(context, R.drawable.alienbeige, widthDress, heightDress);
        this.scaledAlienSprite[2] = createPicture(context, R.drawable.alienpink,  widthDress, heightDress);
        this.scaledAlienSprite[3] = createPicture(context, R.drawable.alienyellow,  widthDress, heightDress);
        this.scaledAlienSprite[4] = createPicture(context, R.drawable.aliengreen,  widthDress, heightDress);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

        drawBitmapBackground(canvas, scaledBackground);
        drawBitmapReturn(canvas,scaledReturnMenu);
        drawBitmap(canvas, scaledPreviousDress,(float)1/6,(float)1/2 );
        drawBitmap(canvas, scaledNextDress, (float)5/6, (float) 1/2);
        drawBitmap(canvas, scaledAlienSprite[AlienSprite.currentDress], (float)1/2, (float) 1/2);

    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0 ;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if (isButtonClick(event))
        {
            this.terminate();
        }

        else if (isButtonClick(event, scaledNextDress, (float) 5/6, (float) 1/2))
        {
            if (AlienSprite.currentDress == MapScene.mapAvailable - 1)
            {
                AlienSprite.currentDress = 0 ;
            }
            else
            {
                AlienSprite.currentDress ++ ;
            }
        }
        else if (isButtonClick(event, scaledPreviousDress, (float) 1/6, (float) 1/2))
        {
            if (AlienSprite.currentDress == 0)
            {
                AlienSprite.currentDress = 3 ;
            }
            else
            {
                AlienSprite.currentDress -- ;
            }
        }


    }
}
