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
    static int JUMP_TIME = 2000;
    static int LEFT_PLAYER = 100;
    static int RIGHT_PLAYER = 200;
    static int TOP_PLAYER = 100;
    static int BOTTOM_PLAYER = 200;
    static int PLAYER_GAP = LEFT_PLAYER;
    static int HEIGHT_JUMP = 400;
    static int INIT_POSITION_Y = Constants.SCREEN_HEIGHT - Constants.HEIGH_GROUND - PlayerConstants.LEFT_PLAYER + 20;
    static int GAP_RIGHT = 30;
}

class ObstaclesGroundConstants {

    private static int OBSTACLE_HEIGHT = 100;
    static int OBSTACLE_WIDTH = 75;
    static int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH;
    static int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT - Constants.HEIGH_GROUND;
    static int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - ObstaclesGroundConstants.OBSTACLE_HEIGHT - Constants.HEIGH_GROUND;
    static int OBSTACLE_LEFT = Constants.SCREEN_WIDTH + ObstaclesGroundConstants.OBSTACLE_WIDTH;
}

class ObstaclesAerianConstants {

    private static int OBSTACLE_HEIGHT = 50;
    private static int OBSTACLE_WIDTH = 100;
    private static int OBSTACLE_GAP_TOP = 600;
    static int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH;
    static int OBSTACLE_BOTTOM = OBSTACLE_HEIGHT + OBSTACLE_GAP_TOP;
    static int OBSTACLE_TOP = ObstaclesAerianConstants.OBSTACLE_HEIGHT - Constants.HEIGH_GROUND + OBSTACLE_GAP_TOP;
    static int OBSTACLE_LEFT = Constants.SCREEN_WIDTH + ObstaclesAerianConstants.OBSTACLE_WIDTH;
}