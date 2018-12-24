package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

abstract class Obstacles {

    final Rect area_obstacle;
    private final AnimationManager animationManager;

    Obstacles(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int are_bottom) {
        area_obstacle = new Rect(area_left, area_top, area_right, are_bottom);
        Animation animation = new Animation(new Bitmap[]{movement_left, movement_right}, 0.25f);
        animationManager = new AnimationManager(new Animation[]{animation});
        animationManager.playAnim(0);
    }

    Obstacles(Bitmap idle, int area_left, int area_top, int area_right, int are_bottom) {
        area_obstacle = new Rect(area_left, area_top, area_right, are_bottom);
        Animation idleGround = new Animation(new Bitmap[]{idle}, 2);
        animationManager = new AnimationManager(new Animation[]{idleGround});
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


    public void draw(Canvas canvas) {
        animationManager.draw(canvas, area_obstacle);
    }

    public void update() {
        animationManager.update();
    }
}
