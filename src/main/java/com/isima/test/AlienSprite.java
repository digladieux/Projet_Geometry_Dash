package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

class AlienSprite {

    /* Classe de android studio toute faite RECT */
    private final Rect rectangle;
    private final AnimationManager animationManager;
    private final int velocity;
    private final int initSpeed;
    private int currentSpeed;

    AlienSprite(Context context, Rect rectangle)
    {
        this.rectangle = rectangle ;

        this.velocity = PlayerConstants.VELOCITY;
        this.initSpeed = PlayerConstants.SPEED;
        this.currentSpeed = initSpeed;

        /* Creer des objet bitmap qui provienne de plusieurs sources differentes ici des fichiers */

        /* On va decoder l'image bitmap, en la recuperant et la mettant dans une image bitmap */
        Bitmap walk1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue_walk1) ;
        Bitmap walk2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue_walk2) ;

        Bitmap jump1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue_jump);
        Bitmap gravity1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue_hurt);
        Bitmap scaledWalk1 = Bitmap.createScaledBitmap(walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledWalk2 = Bitmap.createScaledBitmap(walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledJump = Bitmap.createScaledBitmap(jump1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledGravity = Bitmap.createScaledBitmap(gravity1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);

        Animation jump = new Animation(new Bitmap[]{scaledJump}, 2);
        Animation gravity = new Animation(new Bitmap[]{scaledGravity}, 2);
        Animation walkRight = new Animation(new Bitmap[]{scaledWalk1, scaledWalk2}, 0.25f);

        animationManager = new AnimationManager(new Animation[]{walkRight, jump, gravity});
    }

    Rect getRectangle() {
        return rectangle;
    }


    int getCurrentSpeed() {
        return currentSpeed;
    }


    void setCurrentSpeed(boolean reset) {
        if (reset) {
            this.currentSpeed = this.initSpeed;
        } else {
            this.currentSpeed += velocity;
        }
    }

    public void draw(Canvas canvas) {
        animationManager.draw(canvas, rectangle);
    }

    /* Classe de android studio toute faite Point */
    public void update(Point point)
    {
        /* l'origine n'est pas en bas a gauche mais en haut a gauche, et quand on descend on augmenter et a droite on augmente */
        /* left, top, right, bottom */
        /* Le point est le centre du rectangle */
        float oldTop = rectangle.top; /* est ton aller a gauche ou pas */

        /* l'origine n'est pas en bas a gauche mais en haut a gauche, et quand on descend on augmenter et a droite on augmente */
        /* left, top, right, bottom */
        /* Le point est le centre du rectangle */
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2,point.x + rectangle.width()/2, point.y + rectangle.height()/2);

        int state = 0; /* etat de l'anim, idle, walk1 walk 2 ? */
        if (rectangle.top - oldTop > 5) /* On aurait pu mettre 0, mais sinon il y aurait eu trop d'anim, on prefere laisser un peu d'espace entre une anim donc 5 pixels*/ {
            state = 2; /* allez a gauche */
        } else if (rectangle.top - oldTop < -5) {
            state = 1; /* allez a droite */
        }
        animationManager.playAnim(state);
        animationManager.update();
    }
}

