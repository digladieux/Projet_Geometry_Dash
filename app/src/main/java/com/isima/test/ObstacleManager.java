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
        switch (map) {
            case 1:
                obstacles = initialisationMap1();
                break;
        }

    }

    public int size() {
        return obstacles.size();
    }

    private ArrayList <Obstacles> initialisationMap1() {
        ArrayList <Obstacles> level = new ArrayList <>();

        level.add(SnakeObstacle.initialisationSnakeObstacle(Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(2 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 2 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(3 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(4 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 4 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(5 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 5 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(2 * Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 2 * Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(3 * Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(4 * Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 4 * Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(5 * Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 5 * Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH * 5) {
            level.add(GroundObstacle.initialisationGroundObstacle(ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_BOTTOM));
            i++;
        }
        return level;
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