package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import static com.isima.test.StaticMethod.createPicture;
import static com.isima.test.StaticMethod.drawBitmap;
import static com.isima.test.StaticMethod.drawBitmapBackground;
import static com.isima.test.StaticMethod.drawBitmapReturn;
import static com.isima.test.StaticMethod.isButtonClick;

public class RewardsScene implements Scene {
    private final Bitmap scaledReturnMenu;
    private final Bitmap scaledBackground;
    private final Bitmap scaledTextBackgroundBadge ;
    private final Bitmap[] scaledBadgeAlienSprite ;
    private int currentBadgeAlienSprite = 5;
    private boolean isRewardDisplayed = false ;

    RewardsScene(Context context) {

        int widthBadge = Constants.SCREEN_WIDTH/8;
        int heightBadge = Constants.SCREEN_HEIGHT/5;

        this.scaledBadgeAlienSprite = new Bitmap[5] ;

        this.scaledBackground  = createPicture(context, R.drawable.background, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.scaledTextBackgroundBadge  = createPicture(context, R.drawable.empty, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/3);
        this.scaledReturnMenu  = createPicture(context, R.drawable.return_arrow, Constants.SCREEN_WIDTH/14,Constants.SCREEN_WIDTH/14);
        this.scaledBadgeAlienSprite[0]  = createPicture(context, R.drawable.alienblue_badge2, widthBadge, heightBadge);
        this.scaledBadgeAlienSprite[1]  = createPicture(context, R.drawable.alienbeige_badge2,widthBadge, heightBadge);
        this.scaledBadgeAlienSprite[2]  = createPicture(context, R.drawable.alienpink_badge2, widthBadge, heightBadge);
        this.scaledBadgeAlienSprite[3]  = createPicture(context, R.drawable.alienyellow_badge2, widthBadge, heightBadge);
        this.scaledBadgeAlienSprite[4]  = createPicture(context, R.drawable.aliengreen_badge2, widthBadge, heightBadge);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

        drawBitmapBackground(canvas, scaledBackground);
        drawBitmapReturn(canvas, scaledReturnMenu);

        if (MapScene.mapAvailable >= 1)
        {
            drawBitmap(canvas, scaledBadgeAlienSprite[0], (float)1.5/8, (float) 1.5/5);
        }
        if (MapScene.mapAvailable >= 2)
        {
            drawBitmap(canvas, scaledBadgeAlienSprite[1], (float)1.5/8,(float) 3.5/5) ;
        }
        if (MapScene.mapAvailable >= 3)
        {
            drawBitmap(canvas, scaledBadgeAlienSprite[2], (float)4/8, (float) 1.5/5) ;
        }
        if (MapScene.mapAvailable >= 4)
        {
            drawBitmap(canvas, scaledBadgeAlienSprite[3], (float)4/8, (float) 3.5/5) ;
        }
        if (MapScene.mapAvailable >= 5)
        {
            drawBitmap(canvas, scaledBadgeAlienSprite[4], (float)6.5/8, (float) 1/2) ;
        }

        String text = textRewards() ;

        if (isRewardDisplayed)
        {
            Paint paintRewards = new Paint();
            paintRewards.setTextSize(Constants.SCREEN_WIDTH/14);
            paintRewards.setColor(Color.MAGENTA);
            paintRewards.setTextAlign(Paint.Align.CENTER);
            drawBitmap(canvas, scaledTextBackgroundBadge,(float)1/2,(float)1/2 );
            canvas.drawText(text, Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, paintRewards);
        }
    }


    private void terminate() {
        currentBadgeAlienSprite = 5 ;
        isRewardDisplayed = false ;
        SceneManager.ACTIVE_SCENE = 0 ;

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        isRewardDisplayed = true ;
        if (isButtonClick(event))
        {
            this.terminate();
        }
        else if (isButtonClick(event, scaledBadgeAlienSprite[0], (float) 1.5/8, (float) 1.5/5,1))
        {
            currentBadgeAlienSprite = 0 ;

        }
        else if (isButtonClick(event, scaledBadgeAlienSprite[1], (float) 1.5/8, (float) 3.5/5,2))
        {
            currentBadgeAlienSprite = 1 ;

        }
        else if (isButtonClick(event, scaledBadgeAlienSprite[2], (float) 4/8, (float) 1.5/5,3))
        {
            currentBadgeAlienSprite = 2 ;

        }
        else if (isButtonClick(event, scaledBadgeAlienSprite[3], (float) 4/8, (float) 3.5/5,4))
        {
            currentBadgeAlienSprite = 3 ;
        }
        else if (isButtonClick(event, scaledBadgeAlienSprite[4], (float) 6.5/8, (float) 2.5/5,5))
        {
            currentBadgeAlienSprite = 4 ;
        }
        else if (!(isButtonClick(event, scaledTextBackgroundBadge, (float) 1/2, (float) 1/2)) && (isRewardDisplayed))
        {
            isRewardDisplayed = false ;
        }

    }

    private String textRewards()
    {
        String text = "";
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
        return text ;
    }
}
