package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;

import static com.isima.test.StaticMethod.createPicture;

class BatObstacle extends Obstacle {

    /**
     *
     * @param movement_right
     * @param movement_left
     * @param area_left
     * @param area_top
     * @param area_right
     * @param area_bottom
     */
    private BatObstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int area_bottom) {
        super(movement_right, movement_left, area_left, area_top, area_right, area_bottom);
    }

    /**
     *
     * @param context
     * @param area_left
     * @param area_top
     * @param area_right
     * @param area_bottom
     * @return
     */
    static Obstacle initialisationBatObstacle(Context context, int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.bat, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        Bitmap scaledMovementRight = createPicture(context, R.drawable.bat_fly, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        return new BatObstacle(scaledMovementLeft, scaledMovementRight, area_left, area_top, area_right, area_bottom);
    }
}


