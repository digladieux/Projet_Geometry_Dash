package com.isima.test;

import android.graphics.Bitmap;

public class GroundObstacle extends Obstacles {

    GroundObstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int area_bottom)
    {
        super(movement_right, movement_left, area_left, area_top, area_right, area_bottom);
    }

    @Override
    public boolean playerCollide(RectPlayer player) {
        if (area_obstacle.top < player.getRectangle().bottom) {
            return (area_obstacle.left - PlayerConstants.LEFT_PLAYER + PlayerConstants.GAP_RIGHT) < player.getRectangle().right;
        }
        return false;
    }
}

