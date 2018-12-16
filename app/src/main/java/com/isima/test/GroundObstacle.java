package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class GroundObstacle extends Obstacles {
    private GroundObstacle(Bitmap idle, int area_left, int area_top, int area_right, int area_bottom)
    {
        super(idle, area_left, area_top, area_right, area_bottom);
    }


    static Obstacles initialisationGroundObstacle(int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap idle = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.grass_block);
        Bitmap scaledIdle = Bitmap.createScaledBitmap(idle, ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_HEIGHT, true);
        return new GroundObstacle(scaledIdle, area_left, area_top, area_right, area_bottom);
    }

    @Override
    public boolean playerCollide(RectPlayer player) {
        return player.getRectangle().bottom > area_obstacle.top;
    }
}
