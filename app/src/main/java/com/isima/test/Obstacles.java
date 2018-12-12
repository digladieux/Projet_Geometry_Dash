package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

enum TYPE_OBSTACLE {
    GROUND,
    AERIAN,
}

public class Obstacles implements GameObject {

    protected Rect area_obstacle;
    private TYPE_OBSTACLE type_obstacle;
    private Animation animation;

    Obstacles(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int are_bottom, TYPE_OBSTACLE type_obstacle) {
        this.type_obstacle = type_obstacle;
        area_obstacle = new Rect(area_left, area_top, area_right, are_bottom);

        /* On va decoder l'image bitmap, en la recuperant et la mettant dans une image bitmap */

        animation = new Animation(new Bitmap[]{movement_left, movement_right}, 0.25f);
        animation.play();
    }

    Rect getRectangle() {
        return area_obstacle;
    }

    public TYPE_OBSTACLE getTypeObstacle() {
        return type_obstacle;
    }

    /* Pour decaler les obstacles vers le bas */
    void incrementX(float number_pixel_decrement) {
        area_obstacle.right -= number_pixel_decrement;
        area_obstacle.left -= number_pixel_decrement;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(area_obstacle, paint);
        //animation.draw(canvas, area_obstacle);


    }

    @Override
    public void update() {
        animation.update();
    }
}
