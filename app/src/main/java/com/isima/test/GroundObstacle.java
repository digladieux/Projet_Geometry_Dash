package com.isima.test;

import android.graphics.Bitmap;

public class GroundObstacle extends Obstacles {

    GroundObstacle(Bitmap movement_right, Bitmap movement_left)
    {
        super(movement_right, movement_left, ObstaclesGroundConstants.OBSTACLE_LEFT, ObstaclesGroundConstants.OBSTACLE_TOP, ObstaclesGroundConstants.OBSTACLE_RIGHT, ObstaclesGroundConstants.OBSTACLE_BOTTOM, TYPE_OBSTACLE.GROUND);
    }

    public boolean playerCollide(RectPlayer player) {
        if (area_obstacle.top < player.getRectangle().bottom) {
            return (area_obstacle.left - PlayerConstants.LEFT_PLAYER + PlayerConstants.GAP_RIGHT) < player.getRectangle().right;
        }
        return false;
    }
}

