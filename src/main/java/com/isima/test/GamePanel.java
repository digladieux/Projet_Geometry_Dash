package com.isima.test;

/* Ajout des bibliotheques */
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 *
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    /**
     *
     */
    private MainThread thread ;
    /**
     *
     */
    private final SceneManager manager;

    /**
     *
     * @param context
     */
    public GamePanel(Context context) /*Context : L'etat a l'intant t du telephone. On peut allumer bluetooth, la camera */
    {
        super(context) ;

        /* getHolder : recupere la surface */
        /* addCallBack : On regarde si il y a eu des nouveaux events */
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this) ;
        manager = new SceneManager(context) ;
        /* TODO :  FOCUSABLE_AUTO en parametre */
        /* Focus du "curseur" sur la fenetre de GamePanel */
        setFocusable(true);
    }

    /**
     *
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override /* Permet de dire que cette fonction va heriter */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) /* methode virtuel */
    {

    }

    /**
     *
     * @param holder
     */
    @Override
    /* TODO : pourquoi holder en parametre, enlever getHolder ? */
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread = new MainThread(getHolder(), this) ;
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true) ;
        thread.start() ;
    }

    /**
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true ; /* Essaie de fermer le jeu plusieurs fois */
        while(retry) /* On tente de le fermer plusieurs fois car cela peut planter defois */
        {
            try {
                thread.setRunning(false) ; /* Arrete le jeu */
                thread.join() ; /* Arrete le thread et le detruit */

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }  /* Retourne sur la sortie d'erreur standard, la trace (log) des exception */
            retry = false ;
        }

    }

    /**
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.receiveTouch(event) ;
        return true;
    }

    /**
     *
     */
    public void update()
    {
        manager.update();
    }

    @Override

    /**
     *
     */
    public void draw(Canvas canvas)
    {
        super.draw(canvas) ;
        manager.draw(canvas);
    }


}
