package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

abstract public class Obstacles implements GameObject {

    Rect area_obstacle;
    private AnimationManager animationManager;

    Obstacles(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int are_bottom) {
        area_obstacle = new Rect(area_left, area_top, area_right, are_bottom);
        Animation animation = new Animation(new Bitmap[]{movement_left, movement_right}, 0.25f);
        animationManager = new AnimationManager(new Animation[]{animation});
        animationManager.playAnim(0);
    }


    public boolean playerCollide(RectPlayer player) {
        return area_obstacle.intersect(player.getRectangle());
    }

    void incrementX(float number_pixel_decrement) {
        area_obstacle.right -= number_pixel_decrement;
        area_obstacle.left -= number_pixel_decrement;
    }

    Rect getRectangle() {
        return area_obstacle;
    }

    @Override
    public void draw(Canvas canvas) {
        animationManager.draw(canvas, area_obstacle);
    }

    @Override
    public void update() {
        animationManager.update();
    }
}
