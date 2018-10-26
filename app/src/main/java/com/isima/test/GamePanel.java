package com.isima.test;

/* Ajout des bibliotheques */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/* SurfaceView : "ecran du jeu", l'endroit du FULL_SCREEN */
/* SurfaceHolder.Callback : Tous les changements autour de l'ecran, pour les faire en parallele du prog */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread ;

    private RectPlayer player ;
    private Point playerPoint ;
    private ObstacleManager obstacleManager ;

    public GamePanel(Context context) /*Context : L'etat a l'intant t du telephone. On peut allumer bluetooth, la camera */
    {
        super(context) ;

        /* getHolder : recupere la surface */
        /* addCallBack : On regarde si il y a eu des nouveaux events */
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this) ;
        player = new RectPlayer(new Rect(100,100,200,200), Color.rgb(255,0,0)) ;
        playerPoint = new Point(150,150) ;
        obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK) ;

        /* TODO :  FOCUSABLE_AUTO en parametre */
        /* Focus du "curseur" sur la fenetre de GamePanel */
        setFocusable(true);
    }

    @Override /* Permet de dire que cette fonction va heriter */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) /* methode virtuel */
    {

    }

    @Override
    /* TODO : pourquoi holder en parametre, enlever getHolder ? */
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread = new MainThread(getHolder(), this) ;

        thread.setRunning(true) ;
        thread.start() ;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true ; /* Essaie de fermer le jeu plusieurs fois */
        while(true) /* On tente de le fermer plusieurs fois car cela peut planter defois */
        {
            try {
                thread.setRunning(false) ; /* Arrete le jeu */
                thread.join() ; /* Arrete le thread et le detruit */

            } catch (Exception e) {e.printStackTrace();}  /* Retourne sur la sortie d'erreur standard, la trace (log) des exception */
            retry = false ;
        }

    }

    /* MotionEvent : Capture les evenements de l'utilisateur */
    /* Quand l'utilisateur rester appuye sur l'ecran */


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        /* getAction : bouton relache ? appuye ? bouge */
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN :
            case MotionEvent.ACTION_MOVE :
                /* retourne les coordoonnées où on appuie et les affecte au point*/
                playerPoint.set((int)event.getX(), (int)event.getY()) ;
        }
        /* detect tout les event */
        return true ;
        //return super.onTouchEvent(event) ;
    }

    /* Mettre a jour le jeu, image par image */
    public void update()
    {
        player.update(playerPoint);
        obstacleManager.update();
    }

    @Override

    /*Canvas : Creation du cadre, dessine tout de notre jeu */
    public void draw(Canvas canvas)
    {
        super.draw(canvas) ;
        /* Le fond d'écran est blanc */
        canvas.drawColor(Color.WHITE);
        /*Dessine sur le canvas le rectangle */
        player.draw(canvas);
        obstacleManager.draw(canvas);

    }
}
