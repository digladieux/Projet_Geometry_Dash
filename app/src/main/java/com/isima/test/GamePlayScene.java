package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GamePlayScene implements Scene {

    /* Zone pour l'affichage de l'erreur */
    private Rect text_gameover = new Rect();
    private RectPlayer player ;
    private Point playerPoint ;
    private ObstacleManager obstacleManager ;
    private boolean movingPlayer = false ;
    private boolean gameOver = false ;
    private long gameOverTime ;
    private long frameTime ; /* vitesse du bonhomme */
    private Bitmap mScaledBackground;
    private boolean actionDown;

    GamePlayScene()
    {
        player = new RectPlayer(new Rect(PlayerConstants.LEFT_PLAYER, PlayerConstants.TOP_PLAYER, PlayerConstants.RIGHT_PLAYER, PlayerConstants.BOTTOM_PLAYER), 1, -30);
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        obstacleManager = new ObstacleManager();
        this.actionDown = false;
        frameTime = System.currentTimeMillis() ;
        Bitmap mBackground = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.background_space);
        this.mScaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);
    }
    private void reset() {
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        player.setCurrentSpeed(true);
        obstacleManager = new ObstacleManager();
        movingPlayer = false ;

    }
    @Override
    public void update() {
        if (!gameOver) {
            if (frameTime < Constants.INIT_TIME) {
                frameTime = Constants.INIT_TIME;
            }
            frameTime = System.currentTimeMillis();
            if (movingPlayer) {
                player.setCurrentSpeed(false);
                playerPoint.y += player.getCurrentSpeed();
                if (playerPoint.y == PlayerConstants.INIT_POSITION_Y) {
                    movingPlayer = false;
                    player.setCurrentSpeed(true);
                }
            }

            player.update(playerPoint);
            obstacleManager.update();

            if (obstacleManager.playerCollide(player)) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
    }


    @Override
    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, mScaledBackground.getWidth() - 1, mScaledBackground.getHeight() - 1);
        Rect dest = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(mScaledBackground, src, dest, null);
        player.draw(canvas);
        obstacleManager.draw(canvas);
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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.actionDown = true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            this.actionDown = false;
        }
        if (this.actionDown)
        {
                /* Si le joueur n'a pas perdu et appuie dans le rectangle */
                if ( (!gameOver) && (!movingPlayer) )
                {
                    movingPlayer = true ;
                }
                /* On veut que l'ecran de game over reste 2 sec a l'écran */
                if (gameOver && System.currentTimeMillis() - gameOverTime >= 2000)
                {
                    reset() ;
                    gameOver = false ;
                }
        }
    }

    private void drawCenterText(Canvas canvas, Paint paint) {
        String text = "GameOver !";
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(text_gameover);
        int cHeight = text_gameover.height();
        int cWidth = text_gameover.width();
        paint.getTextBounds(text, 0, text.length(), text_gameover);
        float x = cWidth / 2f - text_gameover.width() / 2f - text_gameover.left;
        float y = cHeight / 2f + text_gameover.height() / 2f - text_gameover.bottom;
        canvas.drawText(text, x, y, paint);
    }
}
