package com.isima.test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
public class ObstacleManager {
    private ArrayList<Obstacle> obstacles ;

    /* Pour eviter de generer un rectangle en dehors de l'écran */
    private int playerGap ;

    /* Espace entre les obstacles */
    private int obstacleHeight ;
    private int color ;
    private long startTime ; /* debut frame */

    private long initTime ; /* debut jeu */

    private int score = 0 ;

    ObstacleManager(int playerGap, int obstacleHeight, int color)
    {
        this.playerGap = playerGap ;
        this.obstacleHeight = obstacleHeight ;
        this.color = color ;

        startTime = initTime = System.currentTimeMillis() ;

        obstacles = new ArrayList<>() ;

        obstacles.add(new Obstacle(obstacleHeight, color, Constants.SCREEN_WIDTH, playerGap)) ;
    }

    boolean playerCollide(RectPlayer player)
    {
        for (Obstacle ob : obstacles) /* On regarde pour tous les obstacles si il y a une colision */
        {
            if (ob.playerCollide(player))
            {
                return true ;
            }
        }
        return false ;
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
        for(Obstacle ob : obstacles)
        {
            ob.incrementY(speed * elapsedTime);
            ob.update();
        }
        if(obstacles.get(obstacles.size() -1 ).getRectangle().left <= 0)
        {
            obstacles.add(0, new Obstacle(obstacleHeight,color, Constants.SCREEN_WIDTH, playerGap)) ;
            obstacles.remove(obstacles.size()-1) ;
            score ++ ;
        }
        /*else if (obstacles.get(obstacles.size() -1 ).getRectangle().right < obstacleGap)
        {
            obstacles.add(new Obstacle(obstacleHeight, color, Constants.SCREEN_WIDTH, playerGap)) ;
        }*/
    }

    public void draw(Canvas canvas)
    {
        /* for each obstacle */
        for (Obstacle ob : obstacles)
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
