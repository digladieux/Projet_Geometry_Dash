package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

class SnakeObstacle extends Obstacle {

    private SnakeObstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_top, int area_right, int area_bottom) {
        super(movement_right, movement_left, area_left, area_top, area_right, area_bottom);
    }

    static Obstacle initialisationSnakeObstacle(Context context, int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap movement_left = BitmapFactory.decodeResource(context.getResources(), R.drawable.snake_slime);
        Bitmap scaledMovementLeft = Bitmap.createScaledBitmap(movement_left, ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_HEIGHT, true);

        Matrix m = new Matrix();
        m.preScale(-1, 1); /* Permet de definir la "taille de l'image" [-1 ; 1 ] */
        Bitmap scaledMovementRight = Bitmap.createBitmap(scaledMovementLeft, 0, 0, scaledMovementLeft.getWidth(), scaledMovementLeft.getHeight(), m, false);
        return new SnakeObstacle(scaledMovementLeft, scaledMovementRight, area_left, area_top, area_right, area_bottom);
    }
}

