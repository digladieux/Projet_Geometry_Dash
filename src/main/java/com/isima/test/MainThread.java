package com.isima.test;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class MainThread extends Thread {
    private static final int MAX_FPS = 60;  /* final = const en c++ */
    private final SurfaceHolder surfaceHolder; /* Zone de la fenetre */
    private final GamePanel gamePanel; /* Zone du jeu */
    private boolean running; /* Le thread tourne t'il */
    //   public static Canvas canvas ; /* ce qu'on va dessiner */

    public void setRunning(boolean running) {
        this.running = running;
    }

    MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas(); /* Le canvas pourra etre modifier */
                synchronized (surfaceHolder) {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis() - time < 16.6)
                    {
                        this.gamePanel.update();
                    }
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}
