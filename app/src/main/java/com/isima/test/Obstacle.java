package com.isima.test;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject {

    private Rect rectangle2 ;
    private int color ;

    public Rect getRectangle() {
        return rectangle2;
    }

    /* Pour decaler les obstacles vers le bas */
    public void incrementY(float y)
    {
        rectangle2.right -= y ;
        rectangle2.left -= y ;
    }
    public Obstacle(int rectHeight, int color, int startY, int playerGap)
    {
        this.color = color ;

        /* On generer une porte : |[] playerGap []| */
        rectangle2 = new Rect(startY + rectHeight, Constants.SCREEN_HEIGHT - 100, startY, Constants.SCREEN_HEIGHT) ;
        //rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight ) ;
    }

    public boolean playerCollide(RectPlayer player)
    {
        return Rect.intersects(rectangle2, player.getRectangle()) ;
        /*if(( rectangle.contains(player.getRectangle().left, player.getRectangle().top))
                || (rectangle.contains(player.getRectangle().right, player.getRectangle().top))
                || (rectangle.contains(player.getRectangle().left, player.getRectangle().bottom))
                || (rectangle.contains(player.getRectangle().right, player.getRectangle().bottom)) )
        {
            return true ;
        }

        return false ;*/

    }
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint() ;
        paint.setColor(color);
        canvas.drawRect(rectangle2,paint);

    }

    @Override
    public void update() {

    }
}
