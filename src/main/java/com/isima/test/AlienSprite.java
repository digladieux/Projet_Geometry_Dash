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
    private AnimationManager animationManager;
    private final double velocity;
    private final double initSpeed;
    private double currentSpeed;

    AlienSprite(Context context, Rect rectangle)
    {
        this.rectangle = rectangle ;

        this.velocity = PlayerConstants.VELOCITY;
        this.initSpeed = PlayerConstants.SPEED;
        this.currentSpeed = initSpeed;

        initialisationAnimationSprite(context);
    }

    private void initialisationAnimationSprite(Context context)
    {
          /* Creer des objet bitmap qui provienne de plusieurs sources differentes ici des fichiers */

        /* On va decoder l'image bitmap, en la recuperant et la mettant dans une image bitmap */
        Bitmap walkBlue1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue_walk1) ;
        Bitmap walkBlue2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue_walk2) ;
        Bitmap jumpBlue = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue_jump);
        Bitmap gravityBlue = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienblue_hurt);

        Bitmap scaledWalkBlue1 = Bitmap.createScaledBitmap(walkBlue1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledWalkBlue2 = Bitmap.createScaledBitmap(walkBlue2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledJumpBlue = Bitmap.createScaledBitmap(jumpBlue, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledGravityBlue = Bitmap.createScaledBitmap(gravityBlue, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);

        Animation animJumpBlue = new Animation(new Bitmap[]{scaledJumpBlue}, 2);
        Animation animGravityBlue = new Animation(new Bitmap[]{scaledGravityBlue}, 2);
        Animation animWalkRightBlue = new Animation(new Bitmap[]{scaledWalkBlue1, scaledWalkBlue2}, 0.25f);


        Bitmap walkYellow1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienyellow_walk1) ;
        Bitmap walkYellow2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienyellow_walk2) ;
        Bitmap jumpYellow = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienyellow_jump);
        Bitmap gravityYellow = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienyellow_hurt);

        Bitmap scaledWalkYellow1 = Bitmap.createScaledBitmap(walkYellow1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledWalkYellow2 = Bitmap.createScaledBitmap(walkYellow2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledJumpYellow = Bitmap.createScaledBitmap(jumpYellow, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledGravityYellow = Bitmap.createScaledBitmap(gravityYellow, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);

        Animation animJumpYellow = new Animation(new Bitmap[]{scaledJumpYellow}, 2);
        Animation animGravityYellow = new Animation(new Bitmap[]{scaledGravityYellow}, 2);
        Animation animWalkRightYellow = new Animation(new Bitmap[]{scaledWalkYellow1, scaledWalkYellow2}, 0.25f);


        Bitmap walkGreen1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen_walk1) ;
        Bitmap walkGreen2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen_walk2) ;
        Bitmap jumpGreen = BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen_jump);
        Bitmap gravityGreen = BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen_hurt);

        Bitmap scaledWalkGreen1 = Bitmap.createScaledBitmap(walkGreen1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledWalkGreen2 = Bitmap.createScaledBitmap(walkGreen2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledJumpGreen = Bitmap.createScaledBitmap(jumpGreen, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledGravityGreen = Bitmap.createScaledBitmap(gravityGreen, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);

        Animation animJumpGreen = new Animation(new Bitmap[]{scaledJumpGreen}, 2);
        Animation animGravityGreen = new Animation(new Bitmap[]{scaledGravityGreen}, 2);
        Animation animWalkRightGreen = new Animation(new Bitmap[]{scaledWalkGreen1, scaledWalkGreen2}, 0.25f);


        Bitmap walkPink1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienpink_walk1) ;
        Bitmap walkPink2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienpink_walk2) ;
        Bitmap jumpPink = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienpink_jump);
        Bitmap gravityPink = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienpink_hurt);

        Bitmap scaledWalkPink1 = Bitmap.createScaledBitmap(walkPink1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledWalkPink2 = Bitmap.createScaledBitmap(walkPink2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledJumpPink = Bitmap.createScaledBitmap(jumpPink, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledGravityPink = Bitmap.createScaledBitmap(gravityPink, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);

        Animation animJumpPink = new Animation(new Bitmap[]{scaledJumpPink}, 2);
        Animation animGravityPink = new Animation(new Bitmap[]{scaledGravityPink}, 2);
        Animation animWalkRightPink = new Animation(new Bitmap[]{scaledWalkPink1, scaledWalkPink2}, 0.25f);


        Bitmap walkBeige1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienbeige_walk1) ;
        Bitmap walkBeige2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienbeige_walk2) ;
        Bitmap jumpBeige = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienbeige_jump);
        Bitmap gravityBeige = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienbeige_hurt);

        Bitmap scaledWalkBeige1 = Bitmap.createScaledBitmap(walkBeige1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledWalkBeige2 = Bitmap.createScaledBitmap(walkBeige2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledJumpBeige = Bitmap.createScaledBitmap(jumpBeige, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);
        Bitmap scaledGravityBeige = Bitmap.createScaledBitmap(gravityBeige, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT, true);

        Animation animJumpBeige = new Animation(new Bitmap[]{scaledJumpBeige}, 2);
        Animation animGravityBeige = new Animation(new Bitmap[]{scaledGravityBeige}, 2);
        Animation animWalkRightBeige = new Animation(new Bitmap[]{scaledWalkBeige1, scaledWalkBeige2}, 0.25f);

        this.animationManager = new AnimationManager(new Animation[]
                {animWalkRightBlue, animJumpBlue, animGravityBlue,
                        animWalkRightBeige, animJumpBeige, animGravityBeige,
                        animWalkRightPink, animJumpPink, animGravityPink,
                        animWalkRightYellow, animJumpYellow, animGravityYellow,
                        animWalkRightGreen, animJumpGreen, animGravityGreen
                });

    }
    Rect getRectangle() {
        return rectangle;
    }


    double getCurrentSpeed() {
        return currentSpeed;
    }


    void resetCurrentSpeed()
    {
        this.currentSpeed = this.initSpeed;
    }

    void incrementCurrentSpeed()
    {
        this.currentSpeed += velocity;
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
/* TODO : faire fonction */
        int state ;
        switch (MapScene.activeMap)
        {
            case 1:
                state = giveStateSprite(oldTop, 0, 1 , 2) ;
                break ;
            case 2:
                state = giveStateSprite(oldTop,3,4,5); /* etat de l'anim, idle, walk1 walk 2 ? */
                break ;
            case 3:
                state = giveStateSprite(oldTop,6,7,8); /* etat de l'anim, idle, walk1 walk 2 ? */
                break ;
            case 4:
                state = giveStateSprite(oldTop,9,10,11); /* etat de l'anim, idle, walk1 walk 2 ? */

                break ;
            default:
                throw new IllegalArgumentException();
        }

        animationManager.playAnim(state);
        animationManager.update();
    }

    private int giveStateSprite(float oldTop, int walk, int jump, int gravity)
    {
        int state = walk ;
        if (rectangle.top - oldTop > 5) /* On aurait pu mettre 0, mais sinon il y aurait eu trop d'anim, on prefere laisser un peu d'espace entre une anim donc 5 pixels*/ {
            state = gravity; /* allez a gauche */
        } else if (rectangle.top - oldTop < -5) {
            state = jump; /* allez a droite */
        }
        return state ;
    }
}

