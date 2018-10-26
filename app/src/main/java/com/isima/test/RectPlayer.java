package com.isima.test;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RectPlayer implements GameObject {

    /* Classe de android studio toute faite RECT */
    private Rect rectangle ;
    private int color ;

    public RectPlayer(Rect rectangle, int color)
    {
        this.rectangle = rectangle ;
        this.color = color ;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        /* Partie graphique de notre rectangle, comment on va le colorier, representer */
        Paint paint = new Paint() ;
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }

    /* Classe de android studio toute faite Point */
    public void update(Point point)
    {
        /* l'origine n'est pas en bas a gauche mais en haut a gauche, et quand on descend on augmenter et a droite on augmente */
        /* left, top, right, bottom */
        /* Le point est le centre du rectangle */
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2,point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
