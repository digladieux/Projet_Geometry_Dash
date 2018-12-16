package com.isima.test;

import java.util.ArrayList;

public final class Map {

    static public ArrayList <Obstacles> initialisationMap(int mapChoice) {
        switch (mapChoice) {
            case 1:
                return initialisationMap1();
            default:
                return initialisationMap1();
        }
    }

    static private ArrayList <Obstacles> initialisationMap1() {
        ArrayList <Obstacles> level = new ArrayList <>();

        level.add(SnakeObstacle.initialisationSnakeObstacle(Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(2 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 2 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(3 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(4 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 4 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(SnakeObstacle.initialisationSnakeObstacle(5 * Constants.SCREEN_WIDTH, ConstantsSnakeObstacle.OBSTACLE_TOP, 5 * Constants.SCREEN_WIDTH + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(2 * Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 2 * Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(3 * Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 3 * Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(4 * Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 4 * Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        level.add(BatObstacle.initialisationBatObstacle(5 * Constants.SCREEN_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP, 5 * Constants.SCREEN_WIDTH + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_BOTTOM));
        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH * 5) {
            level.add(GroundObstacle.initialisationGroundObstacle(ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_LEFT, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_WIDTH * i + ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_BOTTOM));
            i++;
        }
        return level;
    }
}
