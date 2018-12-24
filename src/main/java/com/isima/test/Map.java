package com.isima.test;

import android.content.Context;

import java.util.ArrayList;

final class Map {

    static ArrayList <Obstacles> initialisationMap(Context context, int mapChoice) {
        switch (mapChoice) {
            case 1:
                return initialisationMap1(context);
            default:
                return initialisationMap1(context);
        }
    }

    static private ArrayList <Obstacles> initialisationMap1(Context context) {
        ArrayList <Obstacles> level = new ArrayList <>();

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context,2 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 2 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context,3 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context,4 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 4 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context,5 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 5 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,Constants.SCREEN_WIDTH / 2, ConstantsBatObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH / 2 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,3 * Constants.SCREEN_WIDTH / 2, ConstantsBatObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH / 2 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,5 * Constants.SCREEN_WIDTH / 2, ConstantsBatObstacle.OBSTACLE_TOP, 5 * Constants.SCREEN_WIDTH / 2 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 2, ConstantsBatObstacle.OBSTACLE_TOP, 7 * Constants.SCREEN_WIDTH / 2 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,9 * Constants.SCREEN_WIDTH / 2, ConstantsBatObstacle.OBSTACLE_TOP, 9 * Constants.SCREEN_WIDTH / 2 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH * 5) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_BOTTOM));
            i++;
        }
        return level;
    }
}
