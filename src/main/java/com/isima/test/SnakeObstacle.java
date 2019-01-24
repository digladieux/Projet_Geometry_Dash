package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import static com.isima.test.StaticMethod.createPicture;

class SnakeObstacle extends Obstacle {

    private SnakeObstacle(Bitmap movement_right, Bitmap movement_left, int area_left, int area_right) {
        super(movement_right, movement_left, area_left, ConstantsSnakeObstacle.OBSTACLE_TOP, area_right, ConstantsSnakeObstacle.OBSTACLE_BOTTOM);
    }

    static Obstacle initialisationSnakeObstacle(Context context, int area_left, int area_right) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.snake_slime, ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_HEIGHT);
        Matrix m = new Matrix();
        m.preScale(-1, 1); /* Permet de definir la "taille de l'image" [-1 ; 1 ] */
        Bitmap scaledMovementRight = Bitmap.createBitmap(scaledMovementLeft, 0, 0, scaledMovementLeft.getWidth(), scaledMovementLeft.getHeight(), m, false);
        return new SnakeObstacle(scaledMovementLeft, scaledMovementRight, area_left, area_right);
    }
}

