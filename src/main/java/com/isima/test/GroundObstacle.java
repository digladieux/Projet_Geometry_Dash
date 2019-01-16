package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GroundObstacle extends Obstacle {
    private GroundObstacle(Bitmap idle, int area_left, int area_top, int area_right, int area_bottom)
    {
        super(idle, area_left, area_top, area_right, area_bottom);
    }


    static Obstacle initialisationGroundObstacle(Context context, int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap idle = BitmapFactory.decodeResource(context.getResources(), R.drawable.grass_block);
        Bitmap scaledIdle = Bitmap.createScaledBitmap(idle, ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_HEIGHT, true);
        return new GroundObstacle(scaledIdle, area_left, area_top, area_right, area_bottom);
    }

    @Override
    public boolean playerCollide(AlienSprite player) {
        return player.getRectangle().bottom > areaObstacle.top;
    }
}
