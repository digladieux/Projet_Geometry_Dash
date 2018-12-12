package com.isima.test;

import android.graphics.Bitmap;

public class AerianObstacle extends Obstacles {

    AerianObstacle(Bitmap movement_right, Bitmap movement_left) {
        super(movement_right, movement_left, ObstaclesAerianConstants.OBSTACLE_LEFT, ObstaclesAerianConstants.OBSTACLE_TOP, ObstaclesAerianConstants.OBSTACLE_RIGHT, ObstaclesAerianConstants.OBSTACLE_BOTTOM, TYPE_OBSTACLE.AERIAN);
    }

    public boolean playerCollide(RectPlayer player) {
        if (area_obstacle.bottom > player.getRectangle().top) {
            return (area_obstacle.left - PlayerConstants.LEFT_PLAYER + PlayerConstants.GAP_RIGHT) < player.getRectangle().right;
        }
        return false;
    }
}


