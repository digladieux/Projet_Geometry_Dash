package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;

import static com.isima.test.StaticMethod.createPicture;

/**
 *
 */
public class GroundObstacle extends Obstacle {
    /**
     *
     * @param idle
     * @param area_left
     * @param area_right
     */
    private GroundObstacle(Bitmap idle, int area_left, int area_right)
    {
        super(idle, area_left, area_right);
    }

    /**
     *
     * @param context
     * @param area_left
     * @param area_right
     * @return
     */
    static Obstacle initialisationGroundObstacle(Context context, int area_left, int area_right) {
       Bitmap scaledIdle = createPicture(context, R.drawable.grass_block, ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_HEIGHT);
       return new GroundObstacle(scaledIdle, area_left, area_right);
    }

    /**
     *
     * @param player
     * @return
     */
    @Override
    public boolean playerCollide(AlienSprite player) {
        return player.getRectangle().bottom > areaObstacle.top;
    }
}
