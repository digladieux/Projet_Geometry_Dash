package com.isima.test;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private static final int MAX_FPS = 30;  /* final = const en c++ */
    private SurfaceHolder surfaceHolder ; /* Zone de la fenetre */
    private GamePanel gamePanel ; /* Zone du jeu */
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
        /* long = Gros int */
        long startTime ; /* Debut d'une frame */
        long timeMillis; /* Fin frame (creer + afficher) */
        long waitTime ; /* Combien de temps on doit attendre avant de relancer le prochain frame */
        int frameCount = 0 ;
        long totalTime = 0 ; /* Temps d'allumage de l'appli */
        long targetTime = 1000/MAX_FPS ; /* 1 FPS toutes les 33.33 ms valeur theorique */

        /* A chaque while, on cree une frame */
        while(running)
        {
            startTime = System.nanoTime() ; /* Timer du system en nano secondes */
            Canvas canvas = null;

            try
            {
                final SurfaceHolder verrou = this.surfaceHolder;
                canvas = this.surfaceHolder.lockCanvas() ; /* Le canvas pourra etre modifier */

                /* synchronized permet d'attendre la fin de ce thread pour lancer la suite */
                synchronized (verrou)
                {
                    /* Section Critique : si on a pas fini d'afficher le surface holder a temps N, on peut pas commencer a traiter les donner du frame N+1 */
                    this.gamePanel.update() ;
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
            timeMillis = (System.nanoTime() - startTime)/1000000 ; /* avoir le temps en milisec de la duree d'une frame */
            waitTime = targetTime - timeMillis ; /* Il reste x ms avant de relancer la frame */
            try
            {
                if (waitTime > 0)
                {
                    /* On endort le thread de x ms */
                    MainThread.sleep(waitTime);
                }
            }catch (Exception e) {e.printStackTrace();}

            totalTime += System.nanoTime() - startTime ;
            frameCount ++ ;
            if (frameCount >= MAX_FPS)
            {
                /* Moyenne fps */
                double averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0 ;
                totalTime = 0 ;
                System.out.println(averageFPS) ;
            }
        }

    }
}
