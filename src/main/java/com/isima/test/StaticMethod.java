package com.isima.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

class StaticMethod {
    static Bitmap createPicture(Context context, int id, int width, int height)
    {
        Bitmap picture = BitmapFactory.decodeResource(context.getResources(),id) ;
        return Bitmap.createScaledBitmap(picture, width, height, true);
    }


    static void drawBitmapBackground(Canvas canvas, Bitmap scaledBackground)
    {
        Rect srcBackground = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        Rect destBackground = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        canvas.drawBitmap(scaledBackground, srcBackground, destBackground, null);
    }

    static void drawBitmap(Canvas canvas, Bitmap scaledButton, float x , float y )
    {
        int left = (int) (x * Constants.SCREEN_WIDTH)  - scaledButton.getWidth()/2 ;
        int top = (int) (y * Constants.SCREEN_HEIGHT) - scaledButton.getHeight()/2 ;
        int right =  (int) (x * Constants.SCREEN_WIDTH) + scaledButton.getWidth()/2 ;
        int bottom = (int) (y * Constants.SCREEN_HEIGHT)  + scaledButton.getHeight()/2;
        Rect srcBitmap = new Rect(0, 0, scaledButton.getWidth() - 1, scaledButton.getHeight() - 1);
        Rect destBitmap= new Rect(left, top,right, bottom);
        canvas.drawBitmap(scaledButton, srcBitmap, destBitmap, null);
    }


    static void drawBitmapReturn(Canvas canvas, Bitmap scaledButton)
    {
        int left = (13 * Constants.SCREEN_WIDTH/14);
        int top = 0 ;
        int right =  Constants.SCREEN_WIDTH ;
        int bottom = Constants.SCREEN_WIDTH/14 ;
        Rect srcBitmap = new Rect(0, 0, scaledButton.getWidth() - 1, scaledButton.getHeight() - 1);
        Rect destBitmap= new Rect(left, top,right, bottom);
        canvas.drawBitmap(scaledButton, srcBitmap, destBitmap, null);
    }

    static boolean isButtonClick(MotionEvent event, Bitmap bitmap, float x, float y)
    {
        return (event.getAction() == MotionEvent.ACTION_UP)
                && (event.getRawX() >= x * Constants.SCREEN_WIDTH - bitmap.getWidth()/2)
                && (event.getRawX() <= x * Constants.SCREEN_WIDTH + bitmap.getWidth()/2)
                && (event.getRawY() >= y * Constants.SCREEN_HEIGHT - bitmap.getHeight()/2)
                && (event.getRawY() <= y * Constants.SCREEN_HEIGHT + bitmap.getHeight()/2);
    }

    static boolean isButtonClick(MotionEvent event, Bitmap bitmap, float x, float y, int mapAvailable)
    {
        return isButtonClick(event, bitmap, x, y)  && (MapScene.mapAvailable >= mapAvailable);
    }


    static boolean isButtonClick(MotionEvent event)
    {
        return (event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > 13 * Constants.SCREEN_WIDTH/14) && (event.getRawY() < Constants.SCREEN_WIDTH/14);
    }

   /* static void resetMusic(MediaPlayer music)
    {
        music.stop();

        try {
            music.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        music.seekTo(0);
    }*/




}
