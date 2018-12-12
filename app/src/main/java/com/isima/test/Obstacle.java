package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject {

    private Rect rectangle2 ;
    private int color ;
    private Animation walkRight ;
    private AnimationManager animManager ;
    Rect getRectangle() {
        return rectangle2;
    }

    /* Pour decaler les obstacles vers le bas */
    void incrementY(float number_pixel_decrement)
    {
        rectangle2.right -= number_pixel_decrement ;
        rectangle2.left -= number_pixel_decrement ;
    }
    Obstacle(int rectHeight, int color, int begining_obstacle, int playerGap)
    {
        this.color = color ;

        /* On generer une porte : |[] playerGap []| */
        rectangle2 = new Rect(begining_obstacle + rectHeight, Constants.SCREEN_HEIGHT - 100 - Constants.HEIGH_GROUND, begining_obstacle, Constants.SCREEN_HEIGHT - Constants.HEIGH_GROUND) ;
        //rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight ) ;

        /* On va decoder l'image bitmap, en la recuperant et la mettant dans une image bitmap */
        Bitmap walk1 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.snake_slime) ;
        Bitmap walk2 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.snake_slime_ani) ;

        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);
        animManager = new AnimationManager(new Animation[]{walkRight}) ;
        animManager.playAnim(0);


    }

    boolean playerCollide(RectPlayer player)
    {
        /*if ((rectangle2.left - 75 < player.getRectangle().right) && (player.getRectangle().bottom > Constants.SCREEN_HEIGHT - Constants.HEIGH_GROUND -100) )
            return true ;
        else
            return false;*/
       return Rect.intersects(rectangle2, player.getRectangle()) ;
    }
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint() ;
        paint.setColor(color);
        canvas.drawRect(rectangle2,paint);
        //animManager.draw(canvas, rectangle2);


    }

    @Override
    public void update() {
        animManager.update();
    }
}
