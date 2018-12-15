package com.isima.test;

import android.content.Context;

class Constants
{
    static int SCREEN_WIDTH ;
    static int SCREEN_HEIGHT ;

    static Context CURRENT_CONTEXT ;
    static long INIT_TIME ;
    static int HEIGH_GROUND = 100 ;
}

class PlayerConstants {
    static int TOP_PLAYER = Constants.HEIGH_GROUND;
    static int INIT_POSITION_X = 200;
    static int LEFT_PLAYER = 100;
    private static int PLAYER_WIDTH = 66;
    static int RIGHT_PLAYER = LEFT_PLAYER + PLAYER_WIDTH;
    private static int PLAYER_HEIGHT = 93;
    static int BOTTOM_PLAYER = Constants.HEIGH_GROUND + PLAYER_HEIGHT;
    static int INIT_POSITION_Y = Constants.SCREEN_HEIGHT - Constants.HEIGH_GROUND - PLAYER_HEIGHT / 2;
}

class ObstaclesGroundConstants {

    static int OBSTACLE_HEIGHT = 147;
    static int OBSTACLE_WIDTH = 53;
    static int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH;
    static int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT - Constants.HEIGH_GROUND;
    static int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - ObstaclesGroundConstants.OBSTACLE_HEIGHT - Constants.HEIGH_GROUND;
    static int OBSTACLE_LEFT = Constants.SCREEN_WIDTH + ObstaclesGroundConstants.OBSTACLE_WIDTH;
}

class ObstaclesAerianConstants {

    static int OBSTACLE_HEIGHT = 47;
    static int OBSTACLE_WIDTH = 70;
    static int OBSTACLE_LEFT = Constants.SCREEN_WIDTH - ObstaclesAerianConstants.OBSTACLE_WIDTH;
    private static int OBSTACLE_GAP_TOP = 200;
    static int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH;
    static int OBSTACLE_BOTTOM = OBSTACLE_HEIGHT + OBSTACLE_GAP_TOP;
    static int OBSTACLE_TOP = OBSTACLE_GAP_TOP;
}