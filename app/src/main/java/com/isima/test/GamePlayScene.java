package com.isima.test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GamePlayScene implements Scene {

    /* Zone pour l'affichage de l'erreur */
    private Rect r = new Rect() ;
    private RectPlayer player ;

    /* Centre du rectangle du joueur */
    private Point playerPoint ;
    private ObstacleManager obstacleManager ;

    private boolean movingPlayer = false ;
    private boolean gameOver = false ;
    private long gameOverTime ;

    private OrientationData orientationData ;
    private long frameTime ; /* vitesse du bonhomme */
    public GamePlayScene()
    {
        player = new RectPlayer(new Rect(100,100,200,200), Color.rgb(255,0,0)) ;

        /* on aurait pus appelle reset ici */
        /* On fait apparait le rectangle au mileu 3/4 en bas */
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4) ;
        /* afficher le rectangle autour du point */
        player.update(playerPoint) ;
        obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK) ;

        orientationData = new OrientationData() ;
        orientationData.register();
        frameTime = System.currentTimeMillis() ;

    }
    private void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4) ;
        /* afficher le rectangle autour du point */
        player.update(playerPoint) ;
        obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK) ;
        movingPlayer = false ;

    }
    @Override
    public void update() {
        if (!gameOver)
        {
            if (frameTime < Constants.INIT_TIME)
            {
                frameTime = Constants.INIT_TIME ;
            }
            int elapsedTime = (int)( System.currentTimeMillis() - frameTime) ; /* temps qu'il reste avant une autre frame */
            frameTime = System.currentTimeMillis() ;
            if (orientationData.getOrientation() != null && orientationData.getStartOrientation() != null)
            {
                float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1]; /*x*/
                float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2]; /*y*/

                float xSpeed = 2 * roll * Constants.SCREEN_WIDTH/1000f ;/* vitesse du bonhomme */
                float ySpeed = pitch * Constants.SCREEN_HEIGHT/1000f ; /* il peut parcourir l'ecran en 1 s */

                playerPoint.x += Math.abs(xSpeed * elapsedTime) > 5 ? xSpeed * elapsedTime : 0 ;
                playerPoint.y -= Math.abs(ySpeed * elapsedTime) > 5 ? ySpeed * elapsedTime : 0 ;
            }

            if(playerPoint.x < 0)
            {
                playerPoint.x = 0 ;
            }
            else if (playerPoint.x > Constants.SCREEN_WIDTH)
            {
                playerPoint.x = Constants.SCREEN_WIDTH ;
            }
            if(playerPoint.y < 0)
            {
                playerPoint.y = 0 ;
            }
            else if (playerPoint.y > Constants.SCREEN_HEIGHT)
            {
                playerPoint.y = Constants.SCREEN_HEIGHT ;
            }
            player.update(playerPoint);
            obstacleManager.update();

            if (obstacleManager.playerCollide(player))
            {
                gameOver = true ;
                gameOverTime = System.currentTimeMillis() ;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        /* Le fond d'écran est blanc */
        canvas.drawColor(Color.WHITE);
        /*Dessine sur le canvas le rectangle */
        player.draw(canvas);
        obstacleManager.draw(canvas);
        if (gameOver)
        {
            Paint paint = new Paint() ;
            paint.setTextSize(100);
            paint.setColor(Color.MAGENTA);
            drawCenterText(canvas, paint, "Game Over");
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0 ;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        /* getAction : bouton relache ? appuye ? bouge */
        switch (event.getAction())
        {

            case MotionEvent.ACTION_DOWN :
                /* Si le joueur n'a pas perdu et appuie dans le rectangle */
                if (!gameOver && player.getRectangle().contains((int)event.getX(), (int)event.getY()))
                {
                    movingPlayer = true ;
                }
                /* On veut que l'ecran de game over reste 2 sec a l'écran */
                if (gameOver && System.currentTimeMillis() - gameOverTime >= 2000)
                {
                    reset() ;
                    gameOver = false ;
                    orientationData.newGame();
                }
                break ;
            case MotionEvent.ACTION_MOVE :
                /* Si on a le droit de bouger */
                if (!gameOver && movingPlayer) {
                    /* retourne les coordoonnées où on appuie et les affecte au point*/
                    playerPoint.set((int) event.getX(), (int) event.getY());
                }
                break ;
            case MotionEvent.ACTION_UP :
                movingPlayer = false ;
                break ;
        }
    }

    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
}
