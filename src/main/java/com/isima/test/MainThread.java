package com.isima.test;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class MainThread extends Thread {
    private static final int MAX_FPS = 60;  /* final = const en c++ */
    private final SurfaceHolder surfaceHolder; /* Zone de la fenetre */
    private final GamePanel gamePanel; /* Zone du jeu */
    private boolean running ; /* Le thread tourne t'il */
    //   public static Canvas canvas ; /* ce qu'on va dessiner */

    public void setRunning(boolean running)
    {
        this.running = running ;
    }

    MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        super() ;
        /* TODO : changer les noms */
        this.surfaceHolder = surfaceHolder ;
        this.gamePanel = gamePanel ;

    }

    @Override
    public void run()
    {
        long previous = System.currentTimeMillis();
        double lag = 0.0 ;
        long elapsed ;
        long current ;
        /* long = Gros int */
        long MS_PER_UPDATE = 1000/MAX_FPS ; /* 1 FPS toutes les 33.33 ms valeur theorique */
        /* A chaque while, on cree une frame */
        while(running)
        {
            current = System.currentTimeMillis();
            elapsed = current - previous ;
            previous = current ;
            lag += elapsed ;
            Canvas canvas = null;
            while (lag >= MS_PER_UPDATE)
            {
                this.gamePanel.update();
                lag -= MS_PER_UPDATE ;
            }
            try
            {
                canvas = this.surfaceHolder.lockCanvas() ; /* Le canvas pourra etre modifier */

                /* synchronized permet d'attendre la fin de ce thread pour lancer la suite */
                synchronized (surfaceHolder)
                {
                    /* Section Critique : si on a pas fini d'afficher le surface holder a temps N, on peut pas commencer a traiter les donner du frame N+1 */
                    this.gamePanel.draw(canvas) ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally { /* Se fait dans tous les cas */
                /* Si le canvas existe, meme après une erreur, on l'affiche quand meme pour pas faire planter le programme */
                if (canvas != null)
                {
                    try
                    {
                        /* Arrete l'edition du canvas et l'affiche a l'ecran */
                        surfaceHolder.unlockCanvasAndPost(canvas) ;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}
