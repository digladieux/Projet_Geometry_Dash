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

        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,0.5));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT - 5));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH));

        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,0.875));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));


        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,1.25));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT - 5));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT - 5));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));

        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));

        level.add(BatObstacle.initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < 3 * Constants.SCREEN_WIDTH/2 ) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,i));
            i++;
        }
        level.add(new FlagArrival(context, 1.5)) ;
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap1(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();
        level.add(BatObstacle.initialisationBatObstacle(context,0.5));
        level.add(BatObstacle.initialisationBatObstacle(context,0.6));
        level.add(BatObstacle.initialisationBatObstacle(context,0.7));

        level.add(SnakeObstacle.initialisationSnakeObstacle(context,1));

        level.add(BatObstacle.initialisationBatObstacle(context,1.5));
        level.add(BatObstacle.initialisationBatObstacle(context,1.6));
        level.add(BatObstacle.initialisationBatObstacle(context,1.7));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH * 2) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,i));
            i++;
        }
        level.add(new FlagArrival(context, 2)) ;
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap2(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 0.5));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 1));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 1.5));

        level.add(BatObstacle.initialisationBatObstacle(context,4));

        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 2.5));
        level.add(SnakeObstacle.initialisationSnakeObstacle(context, 3));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH * 4) {
            level.add(GroundObstacle.initialisationGroundObstacle(context, i));
            i++;
        }
        level.add(new FlagArrival(context, 4 * Constants.SCREEN_WIDTH)) ;
        return level;
    }

    static private ArrayList <Obstacle> initialisationMap3(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();
        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH) {
            level.add(GroundObstacle.initialisationGroundObstacle(context,i));
            i++;
        }
        level.add(new FlagArrival(context, 1)) ;

        return level;
    }
}
