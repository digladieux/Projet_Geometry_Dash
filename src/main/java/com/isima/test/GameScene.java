package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import java.io.IOException;

import static com.isima.test.StaticMethod.createPicture;
import static com.isima.test.StaticMethod.drawBitmap;
import static com.isima.test.StaticMethod.drawBitmapBackground;
import static com.isima.test.StaticMethod.drawBitmapReturn;
import static com.isima.test.StaticMethod.isButtonClick;

public class GameScene implements Scene {

    /* Zone pour l'affichage de l'erreur */
    static int mapNumber ;
    private final Bitmap scaledBackground;
    private final Bitmap scaledReturnMenu;
    private final Bitmap scaledBackgroundAttempt ;
    private final AlienSprite player;
    private Point playerPoint ;
    private ObstacleManager obstacleManager ;

    private boolean movingPlayer = false ;
    private boolean gameOver = false ;
    private boolean win = false;
    private boolean actionDown = false;
    private boolean flagGameOverTime ;
    private boolean gameNotStarted = true;
    private boolean changingMap = false;

    private int attempt = 0;
    private final Context context ;
    private long gameOverTime ;

    private final MediaPlayer gamingMusic ;
    private final MediaPlayer gameOverMusic;



    GameScene(Context context)
    {
        mapNumber = 0;
        player = new AlienSprite(context, new Rect(PlayerConstants.LEFT_PLAYER, PlayerConstants.TOP_PLAYER, PlayerConstants.RIGHT_PLAYER, PlayerConstants.BOTTOM_PLAYER));
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        obstacleManager = new ObstacleManager(context, mapNumber);

        this.context = context ;
        this.scaledReturnMenu  = createPicture(context, R.drawable.return_arrow, Constants.SCREEN_WIDTH/14,Constants.SCREEN_WIDTH/14);
        this.scaledBackgroundAttempt = createPicture(context, R.drawable.attempt, Constants.SCREEN_WIDTH/3, Constants.SCREEN_HEIGHT/6);
        this.scaledBackground = createPicture(context, R.drawable.background_game, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

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

                    try {
                        gamingMusic.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gamingMusic.seekTo(0);
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

        drawBitmapBackground(canvas, scaledBackground);
        drawBitmapReturn(canvas, scaledReturnMenu);
        drawBitmap(canvas, scaledBackgroundAttempt,(float)1/5, (float)1/7);
        player.draw(canvas);
        obstacleManager.draw(canvas);
        Paint paintAttempt = new Paint();
        paintAttempt.setTextSize(Constants.SCREEN_WIDTH/14);
        paintAttempt.setColor(Color.WHITE);

        canvas.drawText(""+this.attempt, Constants.SCREEN_WIDTH/5 + scaledBackgroundAttempt.getWidth()/2 - (int) paintAttempt.measureText(" "+ attempt), Constants.SCREEN_HEIGHT/5, paintAttempt);

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
                    if (gameOver) {
                        drawBitmap(canvas, scaledBackgroundAttempt,(float)1/2,(float)1/2 );
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

        gamingMusic.stop();
        gameOverMusic.stop();

        try {
            gamingMusic.prepare();
            gameOverMusic.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gamingMusic.seekTo(0);
        gameOverMusic.seekTo(0);

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
        if (isButtonClick(event))
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
