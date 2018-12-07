package com.isima.test;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class OrientationData implements SensorEventListener {
    private SensorManager manager; /* Permet d'acceder au capteur */
    private Sensor accelerometer ;
    private Sensor magnometer ;


    private float[] accelOutput ; /* Contient vecteur de gravité */
    private float[] magOutput ; /* Vecteur geomagnetic */

    private float[] orientation = new float[3] ;
    public float[] getOrientation()
    {
        return orientation ;
    }

    private float[] startOrientation = null ;
    public float[] getStartOrientation()
    {
        return startOrientation ;
    }
    /* A chaque nouveau jeu, on remet la position de reference du tel a la ou il se trouve */
    public void newGame()
    {
        startOrientation = null ;
    }

    public OrientationData()
    {
        manager = (SensorManager) Constants.CURRENT_CONTEXT.getSystemService(Context.SENSOR_SERVICE) ;
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) ;
        magnometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) ;
    }

    public void register()
    {
        /* Pour eviter de consommer du CPU en continue sur la recuperation des donnes de l'accelerometre, on indique que l'on va recuperer les events sur les sensors,
        pour l'acceleroetre, pour les 0.0000001s (SENSOR_DELAY_GAME*/
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this, magnometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void pause()
    {
        /* Enlever toutes les registres init */
        manager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        /* values[0,1,2] = x , y , z */
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            accelOutput = event.values ;
        }
        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
        {
            magOutput = event.values ;
        }
        if (accelOutput != null && magOutput != null)
        {
            float [] R = new float[9] ; /* Rotation Coordoones en 3 Dimensiosn des 3 valeurs */
            float [] I = new float[9] ; /* Inclinaison Coordoones en 3 Dimensiosn des 3 valeurs */
            boolean success = SensorManager.getRotationMatrix(R, I, accelOutput, magOutput) ; /* Si on a reussi a reucp */
            if (success)
            {
                SensorManager.getOrientation(R, orientation) ; /* z x y dans orientation, calcule l'orientation du tel avec les valeurs des rotations de R */
                if(startOrientation == null)
                {
                    startOrientation = new float[orientation.length] ;
                    /* Copie de vecteur */
                    System.arraycopy(orientation,0,startOrientation,0,orientation.length);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
