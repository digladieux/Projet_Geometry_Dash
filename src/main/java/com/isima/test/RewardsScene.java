package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class RewardsScene implements Scene {
    private final Bitmap scaledReturnMenu;
    private final Bitmap scaledBackground;
    private final Bitmap scaledTextBackgroundBadge ;
    private final Bitmap[] scaledBadgeAlienSprite ;
    private int currentBadgeAlienSprite ;

    RewardsScene(Context context) {

        this.currentBadgeAlienSprite = 5 ;
        this.scaledBadgeAlienSprite = new Bitmap[5] ;

        Bitmap mReturnMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.return_arrow);
        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_game);
        Bitmap mTextBackgroundBadge = BitmapFactory.decodeResource(context.getResources(), R.drawable.attemp);

        this.scaledTextBackgroundBadge = Bitmap.createScaledBitmap(mTextBackgroundBadge, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/3, true);
        this.scaledReturnMenu = Bitmap.createScaledBitmap(mReturnMenu, 100, 100, true);
        this.scaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);

        Bitmap badgeBlue = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue_badge2);
        Bitmap scaledBadgeBlue = Bitmap.createScaledBitmap(badgeBlue, Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/5, true);
        this.scaledBadgeAlienSprite[0] = scaledBadgeBlue ;

        Bitmap badgeBeige = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienbeige_badge2);
        Bitmap scaledBadgeBeige = Bitmap.createScaledBitmap(badgeBeige, Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/5, true);
        this.scaledBadgeAlienSprite[1] = scaledBadgeBeige ;

        Bitmap badgePink = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienpink_badge2);
        Bitmap scaledBadgePink = Bitmap.createScaledBitmap(badgePink, Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/5, true);
        this.scaledBadgeAlienSprite[2] = scaledBadgePink ;

        Bitmap badgeYellow = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienyellow_badge2);
        Bitmap scaledBadgeYellow = Bitmap.createScaledBitmap(badgeYellow,Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/5, true);
        this.scaledBadgeAlienSprite[3] = scaledBadgeYellow ;

        Bitmap badgeGreen = BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen_badge2);
        Bitmap scaledBadgeGreen = Bitmap.createScaledBitmap(badgeGreen, Constants.SCREEN_WIDTH/8, Constants.SCREEN_HEIGHT/5, true);
        this.scaledBadgeAlienSprite[4] = scaledBadgeGreen ;
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

        drawBadge(canvas, scaledBadgeAlienSprite[0], (float)1.5/8, (float) 1.5/5) ;
        drawBadge(canvas, scaledBadgeAlienSprite[1], (float)1.5/8,(float) 3.5/5) ;
        drawBadge(canvas, scaledBadgeAlienSprite[2], (float)4/8, (float) 1.5/5) ;
        drawBadge(canvas, scaledBadgeAlienSprite[3], (float)4/8, (float) 3.5/5) ;
        drawBadge(canvas, scaledBadgeAlienSprite[4], (float)6.5/8, (float) 1/2) ;

        String text ="" ;
        switch (currentBadgeAlienSprite)
        {
            case 0:
                text = "Rewards 1 " ;
                break ;
            case 1:
                text = "Rewards 2" ;
                break;
            case 2:
                text = "Rewards 3" ;
                break;
            case 3:
                text = "Rewards 4" ;
                break;
            case 4:
                text = "Rewards 5" ;
                break;
            case 5:
                break;

                default:
                    throw new IllegalArgumentException();
        }
        if (currentBadgeAlienSprite != 5)
        {
            Paint paintRewards = new Paint();
            paintRewards.setTextSize(100);
            paintRewards.setColor(Color.MAGENTA);
            paintRewards.setTextAlign(Paint.Align.CENTER);
            Rect srcRewards= new Rect(0, 0, scaledTextBackgroundBadge.getWidth() - 1, scaledTextBackgroundBadge.getHeight() - 1);
            Rect destRewards = new Rect(Constants.SCREEN_WIDTH/2  -  (int)(paintRewards.measureText(text)/2), Constants.SCREEN_HEIGHT/2 - (int)(paintRewards.getTextSize()) , Constants.SCREEN_WIDTH/2 + (int)( paintRewards.measureText(text)/2), Constants.SCREEN_HEIGHT/2 + (int)paintRewards.descent() ) ;
            canvas.drawBitmap(scaledTextBackgroundBadge, srcRewards, destRewards, null);
            canvas.drawText(text, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, paintRewards);
        }
    }
    private void drawBadge(Canvas canvas, Bitmap scaledButton, float x, float y)
    {
        Rect srcButton = new Rect(0, 0, scaledButton.getWidth() - 1, scaledButton.getHeight() - 1);
        Rect destButton = new Rect((int) (x * Constants.SCREEN_WIDTH - 1) - scaledButton.getWidth()/2, (int) (y * Constants.SCREEN_HEIGHT - 1) - scaledButton.getHeight()/2, (int) (x * Constants.SCREEN_WIDTH - 1) + scaledButton.getWidth()/2, (int) (y * Constants.SCREEN_HEIGHT - 1) + scaledButton.getHeight()/2);
        canvas.drawBitmap(scaledButton, srcButton, destButton, null);
    }
    @Override
    public void terminate() {
        currentBadgeAlienSprite = 5 ;
        SceneManager.ACTIVE_SCENE = 1 ;

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if ((event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > Constants.SCREEN_WIDTH - 100) && (event.getRawY() < 100))
        {
            this.terminate();
        }
        else if (isMapDisplay(event, (float)1/8, (float)2/8, (float)1/5, (float)2/5))
        {
            currentBadgeAlienSprite = 0 ;

        } else if (isMapDisplay(event, (float)1/8, (float)2/8, (float)3/5, (float)4/5))
        {
            currentBadgeAlienSprite = 1 ;

        } else if (isMapDisplay(event, (float)3.5/8, (float)4.5/8, (float)1/5, (float)2/5))
        {
            currentBadgeAlienSprite = 2 ;

        } else if (isMapDisplay(event, (float)3.5/8, (float)4.5/8, (float)3/5, (float)4/5))
        {
            currentBadgeAlienSprite = 3 ;
        }

        else if (isMapDisplay(event, (float)6/8, (float)7/8, (float)1/2 - 1/5, (float)1/2 - 1/5))
        {
            currentBadgeAlienSprite = 4 ;
        }

    }

    private boolean isMapDisplay(MotionEvent event, float areaLeft, float areaRight, float areaTop, float areaBottom)
    {
        return ((event.getAction() == MotionEvent.ACTION_UP)
                && (event.getRawX() >= areaLeft * Constants.SCREEN_WIDTH)
                && (event.getRawX() <= areaRight * Constants.SCREEN_WIDTH)
                && (event.getRawY() >= areaTop * Constants.SCREEN_HEIGHT)
                && (event.getRawY() <= areaBottom * Constants.SCREEN_HEIGHT));
    }
}
