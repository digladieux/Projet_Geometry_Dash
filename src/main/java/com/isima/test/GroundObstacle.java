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
     */
    private GroundObstacle(Bitmap idle, int area_left)
    {
        super(idle, area_left * ConstantsGroundObstacle.OBSTACLE_WIDTH,area_left * ConstantsGroundObstacle.OBSTACLE_WIDTH + ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_BOTTOM);
    }

    /**
     *
     * @param context
     * @param area_left
     * @return
     */
    static Obstacle initialisationGroundObstacle(Context context, int area_left) {
       Bitmap scaledIdle = createPicture(context, R.drawable.grass_block, ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_HEIGHT);
       return new GroundObstacle(scaledIdle, area_left);
    }

    /**
     * Collision entre un joueur et un obstacle
     * @param player Notre joueur
     * @return true si collision, false sinon
     */
    @Override
    public int playerCollide(AlienSprite player) {
        boolean collide = player.getRectangle().bottom > areaObstacle.top;
        int codeCollision = 0 ;
        if (collide)
        {
            codeCollision = 1 ;
        }
        return  codeCollision ;
    }
}
