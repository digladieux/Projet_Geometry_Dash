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
    private Rect message = new Rect();
    private RectPlayer player ;
    private Point playerPoint ;
    private ObstacleManager obstacleManager ;
    private boolean movingPlayer = false ;
    private boolean gameOver = false ;
    private boolean win = false;
    private long frameTime ; /* vitesse du bonhomme */
    private Bitmap mScaledBackground;
    private boolean actionDown;
    private int attempt;

    GamePlayScene()
    {
        player = new RectPlayer(new Rect(PlayerConstants.LEFT_PLAYER, PlayerConstants.TOP_PLAYER, PlayerConstants.RIGHT_PLAYER, PlayerConstants.BOTTOM_PLAYER), 1, -30);
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        obstacleManager = new ObstacleManager(1);
        this.actionDown = false;
        frameTime = System.currentTimeMillis() ;
        this.attempt = 0;
        Bitmap mBackground = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.background_space);
        this.mScaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);
    }
    private void reset() {
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        player.setCurrentSpeed(true);
        obstacleManager = new ObstacleManager(1);
        movingPlayer = false ;
    }
    @Override
    public void update() {
        if ((!gameOver) && (!win)) {
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
                this.attempt++;
                gameOver = true;
            }

            if (obstacleManager.size() == 0) {
                this.attempt = 0;
                win = true;
            }
        }
    }


    @Override
    public void draw(Canvas canvas) {
        if ((gameOver) || (win))
        {
            Paint paint = new Paint() ;
            paint.setTextSize(100);
            if (gameOver) {
                paint.setColor(Color.RED);
                drawCenterText("GameOver!", canvas, paint);
            } else {
                paint.setColor(Color.GREEN);
                drawCenterText("Gagné!", canvas, paint);
            }
        } else {

            Rect src = new Rect(0, 0, mScaledBackground.getWidth() - 1, mScaledBackground.getHeight() - 1);
            Rect dest = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
            canvas.drawBitmap(mScaledBackground, src, dest, null);
            player.draw(canvas);
            obstacleManager.draw(canvas);
            Paint paintAttempt = new Paint();
            paintAttempt.setTextSize(100);
            paintAttempt.setColor(Color.MAGENTA);
            canvas.drawText("Tentative n°" + this.attempt, 50 + paintAttempt.descent() - paintAttempt.ascent(), 50 + paintAttempt.descent() - paintAttempt.ascent(), paintAttempt);

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
            if ((!gameOver) && (!movingPlayer) && (!win))
                {
                    movingPlayer = true ;
                }
                /* On veut que l'ecran de game over reste 2 sec a l'écran */
            if (gameOver)
                {
                    reset() ;
                    gameOver = false ;
                } else if (win) {
                reset();
                win = false;
            }
        }
    }

    private void drawCenterText(String text, Canvas canvas, Paint paint) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(message);
        int cHeight = message.height();
        int cWidth = message.width();
        paint.getTextBounds(text, 0, text.length(), message);
        float x = cWidth / 2f - message.width() / 2f - message.left;
        float y = cHeight / 2f + message.height() / 2f - message.bottom;
        canvas.drawText(text, x, y, paint);
    }
}
