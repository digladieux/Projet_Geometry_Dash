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
            default:
                throw new IllegalArgumentException();
        }
    }


    static private ArrayList <Obstacle> initialisationMap0(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT, 4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM + + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT - 5, 4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT, 4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT, 4 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT, 4 * Constants.SCREEN_WIDTH / 8 + 3 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 4 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));

        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT, 7 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 7 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT, 7 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT, 7 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT, 7 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT, 7 * Constants.SCREEN_WIDTH / 8 + 3 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM + ConstantsBatObstacle.OBSTACLE_HEIGHT));


        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT, 10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT - 5, 10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT, 10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 10 * Constants.SCREEN_WIDTH / 8 + 3 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT - 5, 10 * Constants.SCREEN_WIDTH / 8 + 3 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT, 10 * Constants.SCREEN_WIDTH / 8 + 3 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));

        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT, 10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT, 10 * Constants.SCREEN_WIDTH / 8 + 3 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM + ConstantsBatObstacle.OBSTACLE_HEIGHT));

        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT, 10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH ) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH));
            i++;
        }
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap1(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 7 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,8 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 8 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,9 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 9 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH));

        level.add(BatObstacle.initialisationBatObstacle(context,20 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 20 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,21 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 21 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(context,22 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 22 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH * 2) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH));
            i++;
        }
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap2(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 4 * Constants.SCREEN_WIDTH / 8, 4 * Constants.SCREEN_WIDTH/8 + ConstantsSnakeObstacle.OBSTACLE_WIDTH));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 8 * Constants.SCREEN_WIDTH / 8, 8 * Constants.SCREEN_WIDTH/8 + ConstantsSnakeObstacle.OBSTACLE_WIDTH));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 12* Constants.SCREEN_WIDTH / 8, 12 * Constants.SCREEN_WIDTH/8 + ConstantsSnakeObstacle.OBSTACLE_WIDTH));

        level.add(BatObstacle.initialisationBatObstacle(context,30 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP, 30 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 20 * Constants.SCREEN_WIDTH / 8, 20 * Constants.SCREEN_WIDTH/8 + ConstantsSnakeObstacle.OBSTACLE_WIDTH));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 23 * Constants.SCREEN_WIDTH / 8, 23 * Constants.SCREEN_WIDTH/8 + ConstantsSnakeObstacle.OBSTACLE_WIDTH));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 26 * Constants.SCREEN_WIDTH / 8, 26 * Constants.SCREEN_WIDTH/8 + ConstantsSnakeObstacle.OBSTACLE_WIDTH));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH * 4) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH));
            i++;
        }
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap3(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, Constants.SCREEN_WIDTH/4, Constants.SCREEN_WIDTH/4 + ConstantsSnakeObstacle.OBSTACLE_WIDTH));
        level.add(BatObstacle.initialisationBatObstacle(context,Constants.SCREEN_WIDTH /4, ConstantsBatObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH /4 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 3 * Constants.SCREEN_WIDTH/4, 3 * Constants.SCREEN_WIDTH/4 + ConstantsSnakeObstacle.OBSTACLE_WIDTH));
        level.add(BatObstacle.initialisationBatObstacle(context,3 * Constants.SCREEN_WIDTH /4, ConstantsBatObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH /4 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH));
            i++;
        }
        return level;
    }
}
