package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

abstract public class Obstacles implements GameObject {

    protected Rect area_obstacle;
    private Animation animation;

    Obstacles(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int are_bottom) {
        area_obstacle = new Rect(area_left, area_top, area_right, are_bottom);
        animation = new Animation(new Bitmap[]{movement_left, movement_right}, 0.25f);
        animation.play();
    }

    abstract public boolean playerCollide(RectPlayer player);

    void incrementX(float number_pixel_decrement) {
        area_obstacle.right -= number_pixel_decrement;
        area_obstacle.left -= number_pixel_decrement;
    }

    Rect getRectangle() {
        return area_obstacle;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(area_obstacle, paint);
        animation.draw(canvas, area_obstacle);
    }

    @Override
    public void update() {
        animation.update();
    }
}
