package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RectPlayer implements GameObject {

    /* Classe de android studio toute faite RECT */
    private Rect rectangle ;
    private int color ;

    private Animation idle ; /* a l'arret */
    private Animation walkRight ;
    private Animation walkLeft ;
    private AnimationManager animManager ;
    public RectPlayer(Rect rectangle, int color)
    {
        this.rectangle = rectangle ;
        this.color = color ;

        /* Creer des objet bitmap qui provienne de plusieurs sources differentes ici des fichiers */
        BitmapFactory bf = new BitmapFactory() ;

        /* On va decoder l'image bitmap, en la recuperant et la mettant dans une image bitmap */
        Bitmap idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienblue) ;
        Bitmap walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienblue_walk1) ;
        Bitmap walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienblue_walk2) ;

        idle = new Animation(new Bitmap[]{idleImg} , 2) ;
        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f) ;

        Matrix m = new Matrix();
        m.preScale(-1, 1); /* Permet de definir la "taille de l'image" [-1 ; 1 ] */

        /* Bitmap que l'on veut "etudier", premier pixel x, premier pixel y, width, height, matrice ??, false car on garde le meme nombre de pixel et le meme on les deplace juste */
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

        walkLeft = new Animation(new Bitmap[]{walk1, walk2}, 0.5f) ;

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft}) ;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        /* Partie graphique de notre rectangle, comment on va le colorier, representer */

        /* Le rectangle est un carre rouge */
        /*Paint paint = new Paint() ;
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);*/

        animManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
        animManager.update();
    }

    /* Classe de android studio toute faite Point */
    public void update(Point point)
    {
        float oldLeft = rectangle.left ; /* est ton aller a gauche ou pas */

        /* l'origine n'est pas en bas a gauche mais en haut a gauche, et quand on descend on augmenter et a droite on augmente */
        /* left, top, right, bottom */
        /* Le point est le centre du rectangle */
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2,point.x + rectangle.width()/2, point.y + rectangle.height()/2);

        int state = 0 ; /* etat de l'anim, idle, walk1 walk 2 ? */
        if (rectangle.left - oldLeft > 5) /* On aurait pu mettre 0, mais sinon il y aurait eu trop d'anim, on prefere laisser un peu d'espace entre une anim donc 5 pixels*/
        {
            state = 1 ; /* allez a gauche */
        }
        else if (rectangle.left - oldLeft < -5)
        {
            state = 2 ; /* allez a droite */
        }
        animManager.playAnim(state);
        animManager.update();
    }
}
