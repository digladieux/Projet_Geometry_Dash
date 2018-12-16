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

    /* Pour eviter de generer un rectangle en dehors de l'écran */

    private long startTime ; /* debut frame */

    private long initTime ; /* debut jeu */

    private int score = 0 ;


    ObstacleManager()
    {
        /* Espace entre les obstacles */
        startTime = initTime = System.currentTimeMillis() ;

        obstacles = new ArrayList<>() ;
        obstacles.add(initialisationSnakeObstacle(ConstantsSnakeObstacle.OBSTACLE_LEFT, ConstantsSnakeObstacle.OBSTACLE_TOP, ConstantsSnakeObstacle.OBSTACLE_RIGHT, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        //obstacles.add(initialisationSnakeObstacle(Constants.SCREEN_WIDTH / 2, ConstantsSnakeObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH / 2 + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        obstacles.add(initialisationBatObstacle(3 * Constants.SCREEN_WIDTH / 4, ConstantsBatObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH / 4 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH) {
            obstacles.add(initialisationGroundObstacle(ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_BOTTOM));
            i++;
        }
    }

    private Obstacles initialisationSnakeObstacle(int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap movement_left = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.snake_slime);
        Bitmap scaledMovementLeft = Bitmap.createScaledBitmap(movement_left, ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_HEIGHT, true);

        Matrix m = new Matrix();
        m.preScale(-1, 1); /* Permet de definir la "taille de l'image" [-1 ; 1 ] */
        Bitmap scaledMovementRight = Bitmap.createBitmap(scaledMovementLeft, 0, 0, scaledMovementLeft.getWidth(), scaledMovementLeft.getHeight(), m, false);
        return new SnakeObstacle(scaledMovementLeft, scaledMovementRight, area_left, area_top, area_right, area_bottom);
    }

    private Obstacles initialisationBatObstacle(int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap movement_left = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bat);
        Bitmap movement_right = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bat_fly);
        Bitmap scaledMovementLeft = Bitmap.createScaledBitmap(movement_left, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT, true);
        Bitmap scaledMovementRight = Bitmap.createScaledBitmap(movement_right, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT, true);
        return new BatObstacle(scaledMovementLeft, scaledMovementRight, area_left, area_top, area_right, area_bottom);
    }

    private Obstacles initialisationGroundObstacle(int area_left, int area_top, int area_right, int area_bottom) {
        Bitmap movement_left = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.grass_block);
        Bitmap scaledMovementLeft = Bitmap.createScaledBitmap(movement_left, ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_HEIGHT, true);

        Matrix m = new Matrix();
        m.preScale(-1, 1); /* Permet de definir la "taille de l'image" [-1 ; 1 ] */
        Bitmap scaledMovementRight = Bitmap.createBitmap(scaledMovementLeft, 0, 0, scaledMovementLeft.getWidth(), scaledMovementLeft.getHeight(), m, false);
        return new GroundObstacle(scaledMovementLeft, scaledMovementRight, area_left, area_top, area_right, area_bottom);
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
            if (!(ob instanceof GroundObstacle)) {
                ob.incrementX(speed * elapsedTime);
            }
            ob.update();
            if (ob.getRectangle().right <= 0) {
                if (ob instanceof SnakeObstacle) {
                    obstacles.add(0, initialisationSnakeObstacle(ConstantsSnakeObstacle.OBSTACLE_LEFT, ConstantsSnakeObstacle.OBSTACLE_TOP, ConstantsSnakeObstacle.OBSTACLE_RIGHT, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
                } else if (ob instanceof BatObstacle) {
                    obstacles.add(0, initialisationBatObstacle(ConstantsBatObstacle.OBSTACLE_LEFT, ConstantsBatObstacle.OBSTACLE_TOP, ConstantsBatObstacle.OBSTACLE_RIGHT, ConstantsBatObstacle.OBSTACLE_BOTTOM));
                }
                obstacles.remove(ob);
                score++;
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
        Paint paint = new Paint() ;
        paint.setTextSize(100) ;
        paint.setColor(Color.MAGENTA);
        /* distance entre le haut du paint et la bas du paint */
        canvas.drawText("" + score, 50 + paint.descent() - paint.ascent() , 50 + paint.descent() - paint.ascent() , paint);
    }
}