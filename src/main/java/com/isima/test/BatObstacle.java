package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class BatObstacle extends Obstacles {

    private BatObstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int area_bottom) {
        super(movement_right, movement_left, area_left, area_top, area_right, area_bottom);
    }

    static Obstacles initialisationBatObstacle(Context context, int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap movement_left = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat);
        Bitmap movement_right = BitmapFactory.decodeResource(context.getResources(), R.drawable.bat_fly);
        Bitmap scaledMovementLeft = Bitmap.createScaledBitmap(movement_left, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT, true);
        Bitmap scaledMovementRight = Bitmap.createScaledBitmap(movement_right, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT, true);
        return new BatObstacle(scaledMovementLeft, scaledMovementRight, area_left, area_top, area_right, area_bottom);
    }
}


