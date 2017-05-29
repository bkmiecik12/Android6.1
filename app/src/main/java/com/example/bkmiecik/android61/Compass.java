package com.example.bkmiecik.android61;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Compass extends Activity implements SensorEventListener {

    private float currentDegree = 0f;

    private SensorManager sensorManager;

    private ImageView img;
    private TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        img = (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.img);

        t1 = (TextView) findViewById(R.id.t1);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float degree = Math.round(event.values[0]);

        if(degree<45 || degree>=315) t1.setText("North "+degree);
        else if(degree>=45 && degree<135) t1.setText("East "+degree);
        else if(degree>=135 && degree<225) t1.setText("South "+degree);
        else if(degree>=225 && degree<315) t1.setText("West "+degree);


        img.setRotation(-degree);
        //currentDegree=-degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
