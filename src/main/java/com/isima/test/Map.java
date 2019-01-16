package com.isima.test;

import android.content.Context;

import java.util.ArrayList;

final class Map {

    static ArrayList <Obstacle> initialisationMap(Context context, int mapChoice) {
        switch (mapChoice) {
            case 0:
                return initialisationMap0(context);
            case 1:
                return initialisationMap1(context);
            case 2:
                return initialisationMap2(context);
            case 3:
                return initialisationMap3(context);
            case 4 :
                return initialisationMap4(context);
            default:
                return initialisationMap0(context);
        }
    }

    static private ArrayList <Obstacle> initialisationMap0(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();
        level.add(GroundObstacle.initialisationGroundObstacle(context,0, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_BOTTOM));
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap1(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, Constants.SCREEN_WIDTH / 2, ConstantsSnakeObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH/2 + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_BOTTOM));
            i++;
        }
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap2(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(BatObstacle.initialisationBatObstacle(context,Constants.SCREEN_WIDTH / 2, ConstantsBatObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH / 2 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_BOTTOM));
            i++;
        }
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap3(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, Constants.SCREEN_WIDTH/2, ConstantsSnakeObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH/2 + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,Constants.SCREEN_WIDTH / 2, ConstantsBatObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH / 2 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH ) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_BOTTOM));
            i++;
        }
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap4(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, Constants.SCREEN_WIDTH/4, ConstantsSnakeObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH/4 + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,Constants.SCREEN_WIDTH /4, ConstantsBatObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH /4 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 3 * Constants.SCREEN_WIDTH/4, ConstantsSnakeObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH/4 + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,3 * Constants.SCREEN_WIDTH /4, ConstantsBatObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH /4 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_BOTTOM));
            i++;
        }
        return level;
    }
}
