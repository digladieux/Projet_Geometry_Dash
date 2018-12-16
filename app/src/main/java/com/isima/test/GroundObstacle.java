package com.isima.test;

import android.graphics.Bitmap;

public class GroundObstacle extends Obstacles {

    GroundObstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int area_bottom)
    {
        super(movement_right, movement_left, area_left, area_top, area_right, area_bottom);
    }

    public boolean playerCollide(RectPlayer player) {
        return area_obstacle.intersect(player.getRectangle().left - ObstaclesGroundConstants.OBSTACLE_WIDTH / 2, player.getRectangle().top - ObstaclesGroundConstants.OBSTACLE_HEIGHT / 2, player.getRectangle().right + ObstaclesGroundConstants.OBSTACLE_WIDTH / 2, player.getRectangle().bottom);
    }
}

