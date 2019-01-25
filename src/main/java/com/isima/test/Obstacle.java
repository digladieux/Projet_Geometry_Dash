package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

abstract class Obstacle {

    final Rect areaObstacle;
    private final AnimationManager animationManager;

    Obstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int are_bottom) {
        areaObstacle = new Rect(area_left, area_top, area_right, are_bottom);
        Animation animation = new Animation(new Bitmap[]{movement_left, movement_right}, 0.25f);
        animationManager = new AnimationManager(new Animation[]{animation});
        animationManager.playAnim(0);
    }

    Obstacle(Bitmap idle, int area_left, int area_right, int areaTop, int areaBottom) {
        areaObstacle = new Rect(area_left, areaTop, area_right, areaBottom);
        Animation idleGround = new Animation(new Bitmap[]{idle}, 2);
        animationManager = new AnimationManager(new Animation[]{idleGround});
        animationManager.playAnim(0);
    }

    public int playerCollide(AlienSprite player) {
        boolean collide = areaObstacle.intersect(player.getRectangle());
        int codeCollision = 0 ;
        if (this instanceof FlagArrival && collide)
        {
            codeCollision = -1 ;
        }
        else if (collide)
        {
            codeCollision = 1 ;
        }

        return codeCollision ;

    }

    void incrementX(float number_pixel_decrement) {
        areaObstacle.right -= number_pixel_decrement;
        areaObstacle.left -= number_pixel_decrement;
    }

    Rect getRectangle() {
        return areaObstacle;
    }


    public void draw(Canvas canvas) {
        animationManager.draw(canvas, areaObstacle);
    }

    public void update() {
        animationManager.update();
    }
}
