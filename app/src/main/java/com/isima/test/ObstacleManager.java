package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;
public class ObstacleManager {
    private ArrayList <Obstacles> obstacles;
    private long startTime ; /* debut frame */
    private long initTime ; /* debut jeu */

    ObstacleManager(int map)
    {
        startTime = initTime = System.currentTimeMillis() ;
        obstacles = Map.initialisationMap(map);
    }

    public int size() {
        return obstacles.size();
    }


    boolean playerCollide(RectPlayer player)
    {
        boolean collision = false;
        for (Obstacles ob : obstacles) {
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
        float speed = (float) (Math.sqrt(1 + (startTime - initTime) / 5000.0)) * Constants.SCREEN_HEIGHT / 3000.0f;
        for (Obstacles ob : obstacles)
        {
            //if (!(ob instanceof GroundObstacle)) {
                ob.incrementX(speed * elapsedTime);
            //}
            ob.update();
            if (ob.getRectangle().right <= 0) {
                obstacles.remove(ob);
            }
        }
    }

    public void draw(Canvas canvas)
    {
        /* for each obstacle */
        for (Obstacles ob : obstacles)
        {
            ob.draw(canvas);
        }
    }
}