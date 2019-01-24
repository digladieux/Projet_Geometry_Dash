package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.isima.test.StaticMethod.createPicture;

class BatObstacle extends Obstacle {

    private BatObstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int area_bottom) {
        super(movement_right, movement_left, area_left, area_top, area_right, area_bottom);
    }

    static Obstacle initialisationBatObstacle(Context context, int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.bat, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        Bitmap scaledMovementRight = createPicture(context, R.drawable.bat_fly, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        return new BatObstacle(scaledMovementLeft, scaledMovementRight, area_left, area_top, area_right, area_bottom);
    }
}


