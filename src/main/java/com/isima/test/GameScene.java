package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GameScene implements Scene {

    /* Zone pour l'affichage de l'erreur */
    static int mapNumber = 0 ;
    private final AlienSprite player;
    private Point playerPoint ;
    private ObstacleManager obstacleManager ;
    private boolean movingPlayer = false ;
    private boolean gameOver = false ;
    private boolean win = false;
    private long frameTime ; /* vitesse du bonhomme */
    private final Bitmap scaledBackground;
    private boolean actionDown;
    private int attempt;
    private Context context ;
    private long gameOverTime ;
    private boolean flagGameOverTime ;
    private boolean gameNotStarted ;

    GameScene(Context context)
    {
        this.context = context ;
        this.gameNotStarted = true ;
        player = new AlienSprite(context, new Rect(PlayerConstants.LEFT_PLAYER, PlayerConstants.TOP_PLAYER, PlayerConstants.RIGHT_PLAYER, PlayerConstants.BOTTOM_PLAYER));
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        obstacleManager = new ObstacleManager(context, mapNumber);
        this.actionDown = false;
        this.attempt = 0;
        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_space);
        this.scaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);
    }
    private void reset() {
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        player.resetCurrentSpeed();
        obstacleManager = new ObstacleManager(context,mapNumber);
        movingPlayer = false ;
    }

    @Override
    public void update() {
        if (gameNotStarted)
        {
            gameNotStarted = false ;
            reset();
        }
        else {
            if ((!gameOver) && (!movingPlayer) && (!win) && (actionDown)) {
                movingPlayer = true;
            }
            if ((!gameOver) && (!win)) {
                if (movingPlayer) {
                    player.incrementCurrentSpeed();
                    playerPoint.y += player.getCurrentSpeed();
                    if (playerPoint.y == PlayerConstants.INIT_POSITION_Y) {
                        movingPlayer = false;
                        player.resetCurrentSpeed();
                    }
                }

                player.update(playerPoint);
                obstacleManager.update();

                if (obstacleManager.playerCollide(player)) {
                    this.attempt++;
                    gameOver = true;
                }

                if (obstacleManager.size() < 4) {
                    this.attempt = 0;
                    win = true;
                }
            }
        }
    }


    @Override
    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        Rect dest = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(scaledBackground, src, dest, null);
        player.draw(canvas);
        obstacleManager.draw(canvas);
        Paint paintAttempt = new Paint();
        paintAttempt.setTextSize(100);
        paintAttempt.setColor(Color.MAGENTA);
        canvas.drawText("Tentative n°" + this.attempt, 50 + paintAttempt.descent() - paintAttempt.ascent(), 50 + paintAttempt.descent() - paintAttempt.ascent(), paintAttempt);

        if ((gameOver) || (win))
        {
            if (flagGameOverTime)
            {
                if (System.currentTimeMillis() - gameOverTime > 1000)
                {
                    reset();
                    win = false;
                    gameOver = false ;
                    flagGameOverTime = false ;
                }
                else
                {
                    Paint paint = new Paint() ;
                    paint.setTextSize(100);
                    if (gameOver) {
                        paint.setColor(Color.RED);
                        paint.setTextAlign(Paint.Align.CENTER);
                        canvas.drawText("GameOver! Retry", Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, paint);
                    } else {
                        this.terminate();
                    }
                }
            }
            else
            {
                flagGameOverTime = true ;
                gameOverTime = System.currentTimeMillis() ;
            }
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 3;
    }

    @Override

    public void recieveTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.actionDown = true;
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            this.actionDown = false;
        }
    }
}
