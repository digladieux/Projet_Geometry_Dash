package com.isima.test;

import android.content.Context;

class Constants
{
    static int SCREEN_WIDTH ;
    static int SCREEN_HEIGHT ;

    static Context CURRENT_CONTEXT ;
    static long INIT_TIME ;
}

class PlayerConstants {
    static final int VELOCITY = (Constants.SCREEN_HEIGHT / 1080) * 2;
    static final int SPEED = (Constants.SCREEN_HEIGHT / 1080) * -50;
    static final int PLAYER_HEIGHT = (Constants.SCREEN_HEIGHT / 1080) * 186;
    static final int PLAYER_WIDTH = (Constants.SCREEN_WIDTH / 1794) * 132;
    static final int INIT_POSITION_X = (Constants.SCREEN_WIDTH / 1794) * 200;
    static final int INIT_POSITION_Y = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT - PLAYER_HEIGHT / 2;
    static final int LEFT_PLAYER = (Constants.SCREEN_WIDTH / 1794) * 100;
    static final int TOP_PLAYER = ConstantsGroundObstacle.OBSTACLE_HEIGHT;
    static final int RIGHT_PLAYER = LEFT_PLAYER + PLAYER_WIDTH;
    static final int BOTTOM_PLAYER = ConstantsGroundObstacle.OBSTACLE_HEIGHT + PLAYER_HEIGHT;
}

class ConstantsSnakeObstacle {

    static final int OBSTACLE_HEIGHT = (Constants.SCREEN_HEIGHT / 1080) * 294;
    static final int OBSTACLE_WIDTH = (Constants.SCREEN_WIDTH / 1794) * 106;
    //static final int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH + OBSTACLE_WIDTH;
    static final int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - OBSTACLE_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT + 2;
    //static int OBSTACLE_LEFT = Constants.SCREEN_WIDTH;
    static final int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT + 2;

}

class ConstantsGroundObstacle {

    static final int OBSTACLE_HEIGHT = (Constants.SCREEN_HEIGHT / 1080) * 140;
    static final int OBSTACLE_WIDTH = (Constants.SCREEN_WIDTH / 1794) * 140;
    //static final int OBSTACLE_RIGHT = OBSTACLE_WIDTH;
    static final int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - OBSTACLE_HEIGHT;
    static final int OBSTACLE_LEFT = 0;
    static final int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT;

}

class ConstantsBatObstacle {

    static final int OBSTACLE_HEIGHT = (Constants.SCREEN_HEIGHT / 1080) * 117;
    static final int OBSTACLE_WIDTH = (Constants.SCREEN_WIDTH / 1794) * 175;
    //static final int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH + OBSTACLE_WIDTH;
    //static final int OBSTACLE_LEFT = Constants.SCREEN_WIDTH;
    private static final int OBSTACLE_GAP_TOP = 400;
    static final int OBSTACLE_TOP = OBSTACLE_GAP_TOP;
    static final int OBSTACLE_BOTTOM = OBSTACLE_HEIGHT + OBSTACLE_GAP_TOP;

}