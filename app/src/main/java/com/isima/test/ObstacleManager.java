package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
public class ObstacleManager {
    private ArrayList <Obstacles> obstacles;

    /* Pour eviter de generer un rectangle en dehors de l'écran */

    private long startTime ; /* debut frame */

    private long initTime ; /* debut jeu */

    private int score = 0 ;


    ObstacleManager()
    {
        /* Espace entre les obstacles */
        startTime = initTime = System.currentTimeMillis() ;

        obstacles = new ArrayList<>() ;
        obstacles.add(initialisationGroundObstacle(ObstaclesGroundConstants.OBSTACLE_LEFT, ObstaclesGroundConstants.OBSTACLE_TOP, ObstaclesGroundConstants.OBSTACLE_RIGHT, ObstaclesGroundConstants.OBSTACLE_BOTTOM));
        //obstacles.add(initialisationGroundObstacle(Constants.SCREEN_WIDTH/2 + ObstaclesGroundConstants.OBSTACLE_WIDTH, ObstaclesGroundConstants.OBSTACLE_TOP, ObstaclesGroundConstants.OBSTACLE_RIGHT/2, ObstaclesGroundConstants.OBSTACLE_BOTTOM));
        //obstacles.add(initialisationAerianObstacle(ObstaclesAerianConstants.OBSTACLE_LEFT, ObstaclesAerianConstants.OBSTACLE_TOP, ObstaclesAerianConstants.OBSTACLE_RIGHT, ObstaclesAerianConstants.OBSTACLE_BOTTOM));
    }

    private Obstacles initialisationGroundObstacle(int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap movement_left = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.snake_slime);
        Bitmap movement_right = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.snake_slime_ani);
        return new GroundObstacle(movement_left, movement_right, area_left, area_top, area_right, area_bottom);
    }

    private Obstacles initialisationAerianObstacle(int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap movement_left = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bat);
        Bitmap movement_right = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bat_fly);
        return new AerianObstacle(movement_left, movement_right, area_left, area_top, area_right, area_bottom);
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
        float speed = (float)(Math.sqrt(1 + (startTime - initTime)/5000.0)) * Constants.SCREEN_HEIGHT/10000.0f ;
        for (Obstacles ob : obstacles)
        {
            ob.incrementX(speed * elapsedTime);
            ob.update();
        }
        if(obstacles.get(obstacles.size() -1 ).getRectangle().left <= 0)
        {
            obstacles.add(0, initialisationGroundObstacle(ObstaclesGroundConstants.OBSTACLE_LEFT, ObstaclesGroundConstants.OBSTACLE_TOP, ObstaclesGroundConstants.OBSTACLE_RIGHT, ObstaclesGroundConstants.OBSTACLE_BOTTOM));
            obstacles.remove(obstacles.size()-1) ;
            score ++ ;
        }
    }

    public void draw(Canvas canvas)
    {
        /* for each obstacle */
        for (Obstacles ob : obstacles)
        {
            ob.draw(canvas);
        }
        Paint paint = new Paint() ;
        paint.setTextSize(100) ;
        paint.setColor(Color.MAGENTA);
        /* distance entre le haut du paint et la bas du paint */
        canvas.drawText("" + score, 50 + paint.descent() - paint.ascent() , 50 + paint.descent() - paint.ascent() , paint);
    }
}