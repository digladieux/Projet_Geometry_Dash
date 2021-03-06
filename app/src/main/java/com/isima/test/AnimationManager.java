package com.isima.test;

import android.graphics.Canvas;
import android.graphics.Rect;

class AnimationManager {

    private final Animation[] animations;
    private int animationIndex = 0;

    AnimationManager(Animation[] animations) {
        this.animations = animations;
    }

    void playAnim(int index) {
        for (int i = 0; i < animations.length; i++) {
            if (i == index) {
                if (!animations[animationIndex].isPLaying()) {
                    animations[i].play();
                }
            } else {
                animations[i].stop();
            }
        }
        animationIndex = index;
    }

    public void draw(Canvas canvas, Rect rect) {
        if (animations[animationIndex].isPLaying()) {
            animations[animationIndex].draw(canvas, rect);
        }
    }

    public void update() {
        if (animations[animationIndex].isPLaying()) {
            animations[animationIndex].update();
        }
    }
}
