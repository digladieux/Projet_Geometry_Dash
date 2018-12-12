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
    private Rect ground ;

    /* Centre du rectangle du joueur */
    private Point playerPoint ;
    private ObstacleManager obstacleManager ;

    private boolean movingPlayer = false ;
    private boolean gameOver = false ;
    private long gameOverTime ;
    private long jumpStart ;

    private OrientationData orientationData ;
    private long frameTime ; /* vitesse du bonhomme */
    GamePlayScene()
    {
        player = new RectPlayer(new Rect(PlayerConstants.LEFT_PLAYER, PlayerConstants.TOP_PLAYER, PlayerConstants.RIGHT_PLAYER, PlayerConstants.BOTTOM_PLAYER));

        /* on aurait pus appelle reset ici */
        /* On fait apparait le rectangle au mileu 3/4 en bas */
        playerPoint = new Point(PlayerConstants.PLAYER_GAP, PlayerConstants.INIT_POSITION_Y); /* a cause de l'image il faut regle */
        /* afficher le rectangle autour du point */
        player.update(playerPoint) ;
        obstacleManager = new ObstacleManager(Color.BLACK);

        orientationData = new OrientationData() ;
        orientationData.register();
        frameTime = System.currentTimeMillis() ;
        ground = new Rect(0, Constants.SCREEN_HEIGHT - Constants.HEIGH_GROUND, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT) ;
    }
    private void reset() {
        playerPoint = new Point(PlayerConstants.PLAYER_GAP, PlayerConstants.INIT_POSITION_Y);
        /* afficher le rectangle autour du point */
        player.update(playerPoint) ;
        obstacleManager = new ObstacleManager(Color.BLACK);
        movingPlayer = false ;

    }
    @Override
    public void update() {
        if (!gameOver) {
            if (frameTime < Constants.INIT_TIME) {
                frameTime = Constants.INIT_TIME;
            }
            frameTime = System.currentTimeMillis();
            if (movingPlayer)
            {
                /* a opti */
                if (System.currentTimeMillis() - jumpStart < PlayerConstants.JUMP_TIME / 2) {
                    if (playerPoint.y > Constants.SCREEN_HEIGHT - Constants.HEIGH_GROUND - PlayerConstants.HEIGHT_JUMP)
                    {
                        playerPoint.y -= 2/25 * (System.currentTimeMillis() - jumpStart) +20;
                    }
                } else if (System.currentTimeMillis() - jumpStart < PlayerConstants.JUMP_TIME) {
                    if (playerPoint.y < PlayerConstants.INIT_POSITION_Y)
                    {
                        playerPoint.y += 3/25 * ((System.currentTimeMillis() - jumpStart)/2) +20 ;
                    }
                } else {
                    movingPlayer = false;
                    playerPoint.y = PlayerConstants.INIT_POSITION_Y;
                }
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
        Paint ground_paint = new Paint() ;
        ground_paint.setColor(Color.BLUE);
        canvas.drawRect(ground,ground_paint);
        if (gameOver)
        {
            Paint paint = new Paint() ;
            paint.setTextSize(100);
            paint.setColor(Color.MAGENTA);
            drawCenterText(canvas, paint);
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0 ;
    }

    @Override

    public void recieveTouch(MotionEvent event) {
        /* getAction : bouton relache ? appuye ? bouge */
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
                /* Si le joueur n'a pas perdu et appuie dans le rectangle */
                if ( (!gameOver) && (!movingPlayer) )
                {
                    movingPlayer = true ;
                    jumpStart = System.currentTimeMillis() ;
                }
                /* On veut que l'ecran de game over reste 2 sec a l'écran */
                if (gameOver && System.currentTimeMillis() - gameOverTime >= 2000)
                {
                    reset() ;
                    gameOver = false ;
                    orientationData.newGame();
                }
        }
    }

    private void drawCenterText(Canvas canvas, Paint paint) {
        String text = "GameOver !";
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
