package com.isima.test;

import android.graphics.Bitmap;

public class AerianObstacle extends Obstacles {

    AerianObstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int area_bottom) {
        super(movement_right, movement_left, ObstaclesAerianConstants.OBSTACLE_LEFT, ObstaclesAerianConstants.OBSTACLE_TOP, ObstaclesAerianConstants.OBSTACLE_RIGHT, ObstaclesAerianConstants.OBSTACLE_BOTTOM);
    }

    @Override
    public boolean playerCollide(RectPlayer player) {
        if (area_obstacle.bottom > player.getRectangle().top) {
            return (area_obstacle.left - PlayerConstants.LEFT_PLAYER + PlayerConstants.GAP_RIGHT - 10) < player.getRectangle().right;
        }
        return false;
    }
}


