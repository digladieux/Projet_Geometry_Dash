package com.isima.test;

import android.content.Context;

import static com.isima.test.StaticMethod.createPicture;

class FlagArrival extends Obstacle{

    FlagArrival(Context context, double areaLeft) {
        super(createPicture(context, R.drawable.flag, ConstantsFlagArrival.OBSTACLE_WIDTH, ConstantsFlagArrival.OBSTACLE_HEIGHT),(int)(Constants.SCREEN_WIDTH * areaLeft), (int)( Constants.SCREEN_WIDTH * areaLeft + ConstantsFlagArrival.OBSTACLE_WIDTH), ConstantsFlagArrival.OBSTACLE_TOP, ConstantsFlagArrival.OBSTACLE_BOTTOM);
    }


}
