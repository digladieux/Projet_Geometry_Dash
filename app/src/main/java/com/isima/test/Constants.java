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
    static int TOP_PLAYER = ConstantsGroundObstacle.OBSTACLE_HEIGHT;
    static int INIT_POSITION_X = 200;
    static int LEFT_PLAYER = 100;
    static int PLAYER_WIDTH = 66;
    static int RIGHT_PLAYER = LEFT_PLAYER + PLAYER_WIDTH;
    static int PLAYER_HEIGHT = 93;
    static int BOTTOM_PLAYER = ConstantsGroundObstacle.OBSTACLE_HEIGHT + PLAYER_HEIGHT;
    static int INIT_POSITION_Y = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT - PLAYER_HEIGHT / 2;
}

class ConstantsSnakeObstacle {

    static int OBSTACLE_HEIGHT = 147;
    static int OBSTACLE_WIDTH = 53;
    static int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH + OBSTACLE_WIDTH;
    static int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - OBSTACLE_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT;
    static int OBSTACLE_LEFT = Constants.SCREEN_WIDTH;
    static int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT;

}

class ConstantsGroundObstacle {

    static int OBSTACLE_HEIGHT = 70;
    static int OBSTACLE_WIDTH = 70;
    static int OBSTACLE_RIGHT = OBSTACLE_WIDTH;
    static int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - OBSTACLE_HEIGHT;
    static int OBSTACLE_LEFT = 0;
    static int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT;

}

class ConstantsBatObstacle {

    static int OBSTACLE_HEIGHT = 47;
    static int OBSTACLE_WIDTH = 70;
    static int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH + OBSTACLE_WIDTH;
    static int OBSTACLE_LEFT = Constants.SCREEN_WIDTH;
    private static int OBSTACLE_GAP_TOP = 400;
    static int OBSTACLE_TOP = OBSTACLE_GAP_TOP;
    static int OBSTACLE_BOTTOM = OBSTACLE_HEIGHT + OBSTACLE_GAP_TOP;

}