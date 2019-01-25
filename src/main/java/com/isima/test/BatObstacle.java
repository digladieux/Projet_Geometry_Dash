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
     */
    private BatObstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top) {
        super(movement_right, movement_left, area_left, area_top, area_left + ConstantsBatObstacle.OBSTACLE_WIDTH, area_top + ConstantsBatObstacle.OBSTACLE_HEIGHT);
    }

    /**
     *
     * @param context
     * @param area_left
     * @param area_top
     * @return
     */
    static Obstacle initialisationBatObstacle(Context context, int area_left, int area_top) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.bat, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        Bitmap scaledMovementRight = createPicture(context, R.drawable.bat_fly, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        return new BatObstacle(scaledMovementLeft, scaledMovementRight, area_left, area_top);
    }

    static Obstacle initialisationBatObstacle(Context context, double area_left) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.bat, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        Bitmap scaledMovementRight = createPicture(context, R.drawable.bat_fly, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        return new BatObstacle(scaledMovementLeft, scaledMovementRight, (int)(area_left * Constants.SCREEN_WIDTH), ConstantsBatObstacle.OBSTACLE_TOP);
    }
}


