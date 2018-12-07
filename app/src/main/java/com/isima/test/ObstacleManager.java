package com.isima.test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
    private long startTime ; /* debut frame */

    private long initTime ; /* debut jeu */

    private int score = 0 ;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color)
    {
        this.playerGap = playerGap ;
        this.obstacleGap = obstacleGap ;
        this.obstacleHeight = obstacleHeight ;
        this.color = color ;

        startTime = initTime = System.currentTimeMillis() ;

        obstacles = new ArrayList<>() ;

        populateObstacles() ;
    }

    public boolean playerCollide(RectPlayer player)
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
    private void populateObstacles()
    {
        /* Todo : currY La zone de jeu sera de 4/5 de l'écran pour avoir de la marche */
    //    int currY = - 5 * Constants.SCREEN_HEIGHT/4 ;
//
  //      /*Todo : pk < 0*/
    //    while (currY < 0)
      //  {
        //    /* Nombre aléatoire entre 0 et 1 */
          //  obstacles.add(new Obstacle(obstacleHeight, color, currY, playerGap)) ;
            //currY += obstacleHeight + obstacleGap ;
       // }
        int currY = Constants.SCREEN_WIDTH ;

              /*Todo : pk < 0*/
        while (currY > 0)
        {
            /* Nombre aléatoire entre 0 et 1 */
            obstacles.add(new Obstacle(obstacleHeight, color, currY, playerGap)) ;
            currY -= obstacleHeight + obstacleGap ;
        }
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
        }
     /*   if(obstacles.get(obstacles.size() -1 ).getRectangle().right >= Constants.SCREEN_WIDTH)
        {
            obstacles.add(0, new Obstacle(obstacleHeight,color, obstacles.get(0).getRectangle().right - obstacleHeight - obstacleGap, playerGap )) ;
            obstacles.remove(obstacles.size()-1) ;
            score ++ ;
        }
        */
        if(obstacles.get(obstacles.size() -1 ).getRectangle().left <= 0)
        {
           /* obstacles.add(0, new Obstacle(obstacleHeight,color, obstacles.get(0).getRectangle().right - obstacleHeight - obstacleGap, playerGap )) ;
            obstacles.remove(obstacles.size()-1) ;*/
            obstacles.add(0, new Obstacle(obstacleHeight,color, Constants.SCREEN_WIDTH, playerGap)) ;
            obstacles.remove(obstacles.size()-1) ;
            score ++ ;
        }
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
