package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import static com.isima.test.StaticMethod.createPicture;

class AlienSprite {

    /**
     *
     */
    private final Rect rectangle;
    /**
     *
     */
    private AnimationManager animationManager;
    /**
     *
     */
    private final double velocity;
    /**
     *
     */
    private final double initSpeed;
    /**
     *
     */
    private double currentSpeed;
    /**
     *
     */
    static int currentDress = 0;

    /**
     *
     * @param context
     * @param rectangle
     */
    AlienSprite(Context context, Rect rectangle)
    {
        this.rectangle = rectangle ;
        this.velocity = PlayerConstants.VELOCITY;
        this.initSpeed = PlayerConstants.SPEED;
        this.currentSpeed = initSpeed;

        initialisationAnimationSprite(context);
    }

    /**
     *
     * @param context
     */
    private void initialisationAnimationSprite(Context context)
    {
        Bitmap scaledWalkBlue1 = createPicture(context, R.drawable.alienblue_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledWalkBlue2 = createPicture(context, R.drawable.alienblue_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledJumpBlue = createPicture(context, R.drawable.alienblue_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledGravityBlue = createPicture(context, R.drawable.alienblue_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);

        Bitmap scaledWalkBeige1 = createPicture(context, R.drawable.alienbeige_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledWalkBeige2 = createPicture(context, R.drawable.alienbeige_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledJumpBeige = createPicture(context, R.drawable.alienbeige_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledGravityBeige = createPicture(context, R.drawable.alienbeige_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);

        Bitmap scaledWalkPink1 = createPicture(context, R.drawable.alienpink_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledWalkPink2 = createPicture(context, R.drawable.alienpink_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledJumpPink = createPicture(context, R.drawable.alienpink_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledGravityPink = createPicture(context, R.drawable.alienpink_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);

        Bitmap scaledWalkYellow1 = createPicture(context, R.drawable.alienyellow_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledWalkYellow2 = createPicture(context, R.drawable.alienyellow_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledJumpYellow = createPicture(context, R.drawable.alienyellow_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledGravityYellow = createPicture(context, R.drawable.alienyellow_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);

        Bitmap scaledWalkGreen1 = createPicture(context, R.drawable.aliengreen_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledWalkGreen2 = createPicture(context, R.drawable.aliengreen_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledJumpGreen = createPicture(context, R.drawable.aliengreen_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledGravityGreen = createPicture(context, R.drawable.aliengreen_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);

        Animation animJumpBlue = new Animation(new Bitmap[]{scaledJumpBlue}, 2);
        Animation animGravityBlue = new Animation(new Bitmap[]{scaledGravityBlue}, 2);
        Animation animWalkRightBlue = new Animation(new Bitmap[]{scaledWalkBlue1, scaledWalkBlue2}, 0.25f);

        Animation animJumpBeige = new Animation(new Bitmap[]{scaledJumpBeige}, 2);
        Animation animGravityBeige = new Animation(new Bitmap[]{scaledGravityBeige}, 2);
        Animation animWalkRightBeige = new Animation(new Bitmap[]{scaledWalkBeige1, scaledWalkBeige2}, 0.25f);

        Animation animJumpPink = new Animation(new Bitmap[]{scaledJumpPink}, 2);
        Animation animGravityPink = new Animation(new Bitmap[]{scaledGravityPink}, 2);
        Animation animWalkRightPink = new Animation(new Bitmap[]{scaledWalkPink1, scaledWalkPink2}, 0.25f);

        Animation animJumpYellow = new Animation(new Bitmap[]{scaledJumpYellow}, 2);
        Animation animGravityYellow = new Animation(new Bitmap[]{scaledGravityYellow}, 2);
        Animation animWalkRightYellow = new Animation(new Bitmap[]{scaledWalkYellow1, scaledWalkYellow2}, 0.25f);

        Animation animJumpGreen = new Animation(new Bitmap[]{scaledJumpGreen}, 2);
        Animation animGravityGreen = new Animation(new Bitmap[]{scaledGravityGreen}, 2);
        Animation animWalkRightGreen = new Animation(new Bitmap[]{scaledWalkGreen1, scaledWalkGreen2}, 0.25f);

        this.animationManager = new AnimationManager(new Animation[]
                {animWalkRightBlue, animJumpBlue, animGravityBlue,
                        animWalkRightBeige, animJumpBeige, animGravityBeige,
                        animWalkRightPink, animJumpPink, animGravityPink,
                        animWalkRightYellow, animJumpYellow, animGravityYellow,
                        animWalkRightGreen, animJumpGreen, animGravityGreen
                });

    }

    /**
     *
     * @return
     */
    Rect getRectangle() {
        return rectangle;
    }


    /**
     *
     * @return
     */
    double getCurrentSpeed() {
        return currentSpeed;
    }


    /**
     *
     */
    void resetCurrentSpeed()
    {
        this.currentSpeed = this.initSpeed;
    }

    /**
     *
     */
    void incrementCurrentSpeed()
    {
        this.currentSpeed += velocity;
    }

    /**
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        animationManager.draw(canvas, rectangle);
    }

    /**
     *
     * @param point
     */
    public void update(Point point)
    {
        float oldTop = rectangle.top; /* est ton aller a gauche ou pas */
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2,point.x + rectangle.width()/2, point.y + rectangle.height()/2);
        int state = setState(oldTop) ;
        animationManager.playAnim(state);
        animationManager.update();
    }

    /**
     *
     * @param oldTop
     * @return
     */
    private int setState(float oldTop)
    {
        int state ;
        switch (currentDress)
        {
            case 0:
                state = giveStateSprite(oldTop, 0, 1 , 2) ;
                break ;
            case 1:
                state = giveStateSprite(oldTop,3,4,5); /* etat de l'anim, idle, walk1 walk 2 ? */
                break ;
            case 2:
                state = giveStateSprite(oldTop,6,7,8); /* etat de l'anim, idle, walk1 walk 2 ? */
                break ;
            case 3:
                state = giveStateSprite(oldTop,9,10,11); /* etat de l'anim, idle, walk1 walk 2 ? */
                break ;
            default:
                throw new IllegalArgumentException();
        }
        return state ;
    }

    /**
     *
     * @param oldTop
     * @param walk
     * @param jump
     * @param gravity
     * @return
     */
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

