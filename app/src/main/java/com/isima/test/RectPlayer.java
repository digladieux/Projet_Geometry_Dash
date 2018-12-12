package com.isima.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;

public class RectPlayer implements GameObject {

    /* Classe de android studio toute faite RECT */
    private Rect rectangle ;

    private Animation walkRight ;
    private AnimationManager animManager ;
    private Point point;

    RectPlayer(Rect rectangle)
    {
        this.rectangle = rectangle ;

        /* Creer des objet bitmap qui provienne de plusieurs sources differentes ici des fichiers */

        /* On va decoder l'image bitmap, en la recuperant et la mettant dans une image bitmap */
        Bitmap walk1 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienblue_walk1) ;
        Bitmap walk2 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienblue_walk2) ;
        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f) ;
        animManager = new AnimationManager(new Animation[]{walkRight}) ;
        animManager.playAnim(0);

    }

    Rect getRectangle() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        animManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
        animManager.update();
    }

    /* Classe de android studio toute faite Point */
    public void update(Point point)
    {
        this.point = point;

        /* l'origine n'est pas en bas a gauche mais en haut a gauche, et quand on descend on augmenter et a droite on augmente */
        /* left, top, right, bottom */
        /* Le point est le centre du rectangle */
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2,point.x + rectangle.width()/2, point.y + rectangle.height()/2);
        animManager.update();
    }
}

