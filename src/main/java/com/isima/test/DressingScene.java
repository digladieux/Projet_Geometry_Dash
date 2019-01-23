package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class DressingScene implements Scene {
    private final Bitmap scaledReturnMenu;
    private final Bitmap scaledBackground;
    private final Bitmap scaledNextDress ;
    private final Bitmap scaledPreviousDress ;
    private final Bitmap[] scaledAlienSprite ;
    private int currentAlienSprite ;

    DressingScene(Context context) {

        this.currentAlienSprite = 0 ;
        this.scaledAlienSprite = new Bitmap[5] ;

        Bitmap mReturnMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.return_arrow);
        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_game);
        Bitmap mNextDress = BitmapFactory.decodeResource(context.getResources(), R.drawable.attemp);
        Bitmap mPreviousDress = BitmapFactory.decodeResource(context.getResources(), R.drawable.attemp);

        this.scaledReturnMenu = Bitmap.createScaledBitmap(mReturnMenu, 100, 100, true);
        this.scaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);
        this.scaledNextDress = Bitmap.createScaledBitmap(mNextDress, 100, 100, true);
        this.scaledPreviousDress = Bitmap.createScaledBitmap(mPreviousDress, 100, 100, true);

        Bitmap idleBlue = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue);
        Bitmap scaledIdleBlue = Bitmap.createScaledBitmap(idleBlue, Constants.SCREEN_WIDTH/6, 3 * Constants.SCREEN_HEIGHT/4, true);
        this.scaledAlienSprite[0] = scaledIdleBlue ;

        Bitmap idleBeige = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienbeige);
        Bitmap scaledIdleBeige = Bitmap.createScaledBitmap(idleBeige, Constants.SCREEN_WIDTH/6, 3 * Constants.SCREEN_HEIGHT/4, true);
        this.scaledAlienSprite[1] = scaledIdleBeige ;

        Bitmap idlePink = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienpink);
        Bitmap scaledIdlePink = Bitmap.createScaledBitmap(idlePink, Constants.SCREEN_WIDTH/6, 3 * Constants.SCREEN_HEIGHT/4, true);
        this.scaledAlienSprite[2] = scaledIdlePink ;

        Bitmap idleYellow = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienyellow);
        Bitmap scaledIdleYellow = Bitmap.createScaledBitmap(idleYellow,Constants.SCREEN_WIDTH/6, 3 * Constants.SCREEN_HEIGHT/4, true);
        this.scaledAlienSprite[3] = scaledIdleYellow ;

        Bitmap idleGreen = BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen);
        Bitmap scaledIdleGreen = Bitmap.createScaledBitmap(idleGreen, Constants.SCREEN_WIDTH/6, 3 * Constants.SCREEN_HEIGHT/4, true);
        this.scaledAlienSprite[4] = scaledIdleGreen ;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

        Rect srcBackground = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        Rect destBackground = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(scaledBackground, srcBackground, destBackground, null);

        Rect srcReturnMenu = new Rect(0, 0, scaledReturnMenu.getWidth() - 1, scaledReturnMenu.getHeight() - 1);
        Rect destReturnMenu = new Rect(Constants.SCREEN_WIDTH - 101, 0, Constants.SCREEN_WIDTH - 1, 100);
        canvas.drawBitmap(scaledReturnMenu, srcReturnMenu, destReturnMenu, null);

        Rect srcNextDress = new Rect(0, 0, scaledNextDress.getWidth() - 1, scaledNextDress.getHeight() - 1);
        Rect destNextDress = new Rect(5 * Constants.SCREEN_WIDTH/6, Constants.SCREEN_HEIGHT/2 - scaledNextDress.getHeight()/2, 5 * Constants.SCREEN_WIDTH/6 + scaledNextDress.getWidth(), Constants.SCREEN_HEIGHT/2 + scaledNextDress.getHeight()/2);
        canvas.drawBitmap(scaledNextDress, srcNextDress, destNextDress, null);


        Rect srcPreviousDress = new Rect(0, 0, scaledPreviousDress.getWidth() - 1, scaledPreviousDress.getHeight() - 1);
        Rect destPreviousDress = new Rect(Constants.SCREEN_WIDTH/6 - scaledPreviousDress.getWidth()/2, Constants.SCREEN_HEIGHT/2 - scaledPreviousDress.getHeight()/2,
                Constants.SCREEN_WIDTH/6 + scaledPreviousDress.getWidth()/2, Constants.SCREEN_HEIGHT/2 + scaledPreviousDress.getHeight()/2);
        canvas.drawBitmap(scaledPreviousDress, srcPreviousDress, destPreviousDress, null);

        Rect srcAlien = new Rect(0, 0, scaledAlienSprite[currentAlienSprite].getWidth() - 1, scaledAlienSprite[currentAlienSprite].getHeight() - 1);
        Rect destAlien = new Rect(Constants.SCREEN_WIDTH/2 - scaledAlienSprite[currentAlienSprite].getWidth()/2 , Constants.SCREEN_HEIGHT/2 - scaledAlienSprite[currentAlienSprite].getHeight()/2, Constants.SCREEN_WIDTH/2 + scaledAlienSprite[currentAlienSprite].getWidth()/2, Constants.SCREEN_HEIGHT/2 + scaledAlienSprite[currentAlienSprite].getHeight()/2);
        canvas.drawBitmap(scaledAlienSprite[currentAlienSprite], srcAlien, destAlien, null);

    }

    @Override
    public void terminate() {
        currentAlienSprite = 0 ;
        SceneManager.ACTIVE_SCENE = 1 ;

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if ((event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > Constants.SCREEN_WIDTH - 100) && (event.getRawY() < 100))
        {
            this.terminate();
        }

        else if ((event.getAction() == MotionEvent.ACTION_UP)
                && (event.getRawX() >= 5 * Constants.SCREEN_WIDTH/6 - scaledNextDress.getWidth()/2)
                && (event.getRawX() <= 5 * Constants.SCREEN_WIDTH/6 + scaledNextDress.getWidth()/2)
                && (event.getRawY() >= Constants.SCREEN_HEIGHT/2 - scaledNextDress.getHeight()/2)
                && (event.getRawY() <= Constants.SCREEN_HEIGHT/2 + scaledNextDress.getHeight()/2))
        {
            if (currentAlienSprite == 4)
            {
                currentAlienSprite = 0 ;
            }
            else
            {
                currentAlienSprite ++ ;
            }
        }
        else if ((event.getAction() == MotionEvent.ACTION_UP)
                && (event.getRawX() >= Constants.SCREEN_WIDTH/6 - scaledPreviousDress.getWidth()/2)
                && (event.getRawX() <= Constants.SCREEN_WIDTH/6 + scaledPreviousDress.getWidth()/2)
                && (event.getRawY() >= Constants.SCREEN_HEIGHT/2 - scaledPreviousDress.getHeight()/2)
                && (event.getRawY() <= Constants.SCREEN_HEIGHT/2 + scaledPreviousDress.getHeight()/2))
        {
            if (currentAlienSprite == 0)
            {
                currentAlienSprite = 4 ;
            }
            else
            {
                currentAlienSprite -- ;
            }
        }


    }
}
