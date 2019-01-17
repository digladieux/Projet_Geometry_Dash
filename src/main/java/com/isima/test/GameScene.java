package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;

public class GameScene implements Scene {

    /* Zone pour l'affichage de l'erreur */
    static int mapNumber = 0 ;
    private final Bitmap scaledBackground;
    private final Bitmap scaledReturnMenu;
    private final Bitmap scaledBackgroundAttempt ;
    private final AlienSprite player;
    private Point playerPoint ;
    private ObstacleManager obstacleManager ;

    private boolean movingPlayer = false ;
    private boolean gameOver = false ;
    private boolean win = false;
    private boolean actionDown;
    private boolean flagGameOverTime ;
    private boolean gameNotStarted ;
    private boolean changingMap;

    private int attempt;
    private Context context ;
    private long gameOverTime ;

    private MediaPlayer gamingMusic ;
    private MediaPlayer gameOverMusic;



    GameScene(Context context)
    {
        this.context = context ;
        this.changingMap = false ;
        this.gameNotStarted = true ;
        player = new AlienSprite(context, new Rect(PlayerConstants.LEFT_PLAYER, PlayerConstants.TOP_PLAYER, PlayerConstants.RIGHT_PLAYER, PlayerConstants.BOTTOM_PLAYER));
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        obstacleManager = new ObstacleManager(context, mapNumber);
        this.actionDown = false;
        this.attempt = 0;
        Bitmap mReturnMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.return_menu);
        Bitmap mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_game);
        Bitmap mBackgroundAttempt = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_button);

        this.scaledBackgroundAttempt = Bitmap.createScaledBitmap(mBackgroundAttempt, 200, 150, true);
        this.scaledReturnMenu = Bitmap.createScaledBitmap(mReturnMenu, 100, 100, true);
        this.scaledBackground = Bitmap.createScaledBitmap(mBackground, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, true);

        gameOverMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.gameoversong);
        gamingMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.gamingsong);
    }
    private void reset() {
        gamingMusic.start();
        gameOver = false ;
        win = false ;
        actionDown = false ;
        changingMap = false ;
        movingPlayer = false ;

        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        player.resetCurrentSpeed();
        obstacleManager = new ObstacleManager(context,mapNumber);

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
                    if (playerPoint.y > PlayerConstants.INIT_POSITION_Y) {
                        movingPlayer = false;
                        player.resetCurrentSpeed();
                        playerPoint.y = PlayerConstants.INIT_POSITION_Y;
                    }
                }

                player.update(playerPoint);
                obstacleManager.update();

                if (obstacleManager.playerCollide(player)) {
                    this.attempt++;
                    gameOver = true;
                    gamingMusic.stop();
                    gameOverMusic.start();
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
        Rect srcBackground = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        Rect destBackground = new Rect(0, 0, Constants.SCREEN_WIDTH - 1, Constants.SCREEN_HEIGHT - 1);
        canvas.drawBitmap(scaledBackground, srcBackground, destBackground, null);

        Rect srcReturnMenu = new Rect(0, 0, scaledReturnMenu.getWidth() - 1, scaledReturnMenu.getHeight() - 1);
        Rect destReturnMenu = new Rect(Constants.SCREEN_WIDTH - 101, 0, Constants.SCREEN_WIDTH - 1, 100);
        canvas.drawBitmap(scaledReturnMenu, srcReturnMenu, destReturnMenu, null);


        player.draw(canvas);
        obstacleManager.draw(canvas);
        Paint paintAttempt = new Paint();
        paintAttempt.setTextSize(100);
        paintAttempt.setColor(Color.MAGENTA);
        Rect srcBackgroundAttempt = new Rect(0, 0, scaledBackgroundAttempt.getWidth() - 1, scaledBackgroundAttempt.getHeight() - 1);
        Rect destBackgroundAttempt = new Rect(50 + (int) paintAttempt.descent() -  (int) paintAttempt.ascent(), 50 + (int) paintAttempt.descent(), 50 + (int)paintAttempt.descent() - (int) paintAttempt.ascent() + (int) paintAttempt.measureText("Attempt n° " + this.attempt), 50 + (int)paintAttempt.descent() + (int)paintAttempt.getTextSize()) ;
        canvas.drawBitmap(scaledBackgroundAttempt, srcBackgroundAttempt, destBackgroundAttempt, null);
        canvas.drawText("Attempt n°" + this.attempt, 50 + paintAttempt.descent() - paintAttempt.ascent(), 50 + paintAttempt.descent() - paintAttempt.ascent(), paintAttempt);

        if ((gameOver) || (win))
        {
            if (flagGameOverTime)
            {
                if (System.currentTimeMillis() - gameOverTime > 1000)
                {
                    reset();
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
                        gamingMusic.stop();
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
        if (changingMap)
        {
            reset();
            gameNotStarted = true ;
            SceneManager.ACTIVE_SCENE = 1 ;
        }
        else
        {
            SceneManager.ACTIVE_SCENE = 3 ;
        }
    }

    @Override

    public void recieveTouch(MotionEvent event) {
        if ((event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > Constants.SCREEN_WIDTH - 100) && (event.getRawY() < 100))
        {

            this.actionDown = false ;
            changingMap = true ;
            this.terminate();
        }
        else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.actionDown = true;
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            this.actionDown = false;
        }
    }
}
