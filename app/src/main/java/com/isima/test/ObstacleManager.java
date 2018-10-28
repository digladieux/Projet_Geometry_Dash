package com.isima.test;

import android.graphics.Canvas;

import java.util.ArrayList;
/* TODO : Comprendre obstacle Gap */
public class ObstacleManager {
    private ArrayList<Obstacle> obstacles ;

    /* Pour eviter de generer un rectangle en dehors de l'écran */
    private int playerGap ;

    /* Espace entre les obstacles */
    private int obstacleGap ;
    private int obstacleHeight ;
    private int color ;
    private long startTime ;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color)
    {
        this.playerGap = playerGap ;
        this.obstacleGap = obstacleGap ;
        this.obstacleHeight = obstacleHeight ;
        this.color = color ;

        startTime = System.currentTimeMillis() ;

        obstacles = new ArrayList<>() ;

        populateObstacles() ;
    }

    private void populateObstacles()
    {
        /* Todo : currY La zone de jeu sera de 4/5 de l'écran pour avoir de la marche */
        int currY = - 5 * Constants.SCREEN_HEIGHT/4 ;

        /*Todo : pk < 0*/
        while (currY < 0)
        {
            /* Nombre aléatoire entre 0 et 1 */
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle(obstacleHeight, color, xStart, currY, playerGap)) ;
            currY += obstacleHeight + obstacleGap ;
        }
    }

    public void update()
    {
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis() ;
        /* Combien de temps pour parcourir un ecran (10sec)*/
        float speed = Constants.SCREEN_HEIGHT/10000.0f ;
        for(Obstacle ob : obstacles)
        {
            ob.incrementY(speed * elapsedTime);
        }
        if(obstacles.get(obstacles.size() -1 ).getRectangle().top >= Constants.SCREEN_HEIGHT)
        {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight,color, xStart, obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap, playerGap )) ;
            obstacles.remove(obstacles.size()-1) ;
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
