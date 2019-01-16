package com.isima.test;

import android.content.Context;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.Iterator;

class ObstacleManager {
    private final ArrayList <Obstacle> obstacles;
    private long startTime ; /* debut frame */ /*todo : utile ? */
    private final long initTime; /* debut jeu */

    ObstacleManager(Context context, int map)
    {
        startTime = initTime = System.currentTimeMillis() ;
        obstacles = Map.initialisationMap(context, map);
    }

    public int size() {
        return obstacles.size();
    }


    boolean playerCollide(AlienSprite player)
    {
        boolean collision = false;
        for (Obstacle ob : obstacles) {
            if (ob.playerCollide(player)) {
                collision = true;
            }
        }
        return collision;
    }

    public void update()
    {
        if (startTime < Constants.INIT_TIME)
        {
            startTime = Constants.INIT_TIME ;
        }
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis() ;
        /* Combien de temps pour parcourir un ecran (10sec) et ca diminue apres */
        float speedBat = (float) (Math.sqrt(1 + (startTime - initTime) / 3000.0)) * Constants.SCREEN_HEIGHT / 3000.0f;
        float speedMonster = (float) (Math.sqrt(1 + (startTime - initTime) / 5000.0)) * Constants.SCREEN_HEIGHT / 5000.0f;

        Iterator <Obstacle> it = obstacles.iterator();
        while (it.hasNext())
        {
            Obstacle ob = it.next();
            if (ob instanceof BatObstacle) {
                ob.incrementX(speedBat * elapsedTime);
            } else {
                ob.incrementX(speedMonster * elapsedTime);
            }
            ob.update();
            if (ob.getRectangle().right <= 0) {
                it.remove();
            }
        }
    }

    public void draw(Canvas canvas)
    {
        /* for each obstacle */
        for (Obstacle ob : obstacles)
        {
            ob.draw(canvas);
        }
    }
}