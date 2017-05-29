package com.example.bkmiecik.android61;

import android.app.Activity;
import android.hardware.*;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.hardware.camera2.CaptureRequest.SENSOR_SENSITIVITY;

public class ASensor extends Activity implements SensorEventListener {

    private SensorManager sensorManager = null;
    private int  mSensorType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asensor);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorType = getIntent().getIntExtra("sensorType",Sensor.TYPE_LIGHT);
        if(mSensorType==Sensor.TYPE_LIGHT) setTitle("Światło");
        else if(mSensorType==Sensor.TYPE_ACCELEROMETER) setTitle("Przyspieszenie");
    }

    @Override
    protected void onResume() {
        final Sensor sensor= sensorManager.getSensorList(mSensorType).get(0);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView t1 = (TextView) findViewById(R.id.t_data);
        StringBuilder sb = new StringBuilder();
        if(mSensorType==Sensor.TYPE_LIGHT){
            sb.append("Ambient light level: ");
            sb.append(event.values[0]);
            sb.append(" lux");
        }
        else if(mSensorType == Sensor.TYPE_ACCELEROMETER){
            sb.append("X acceleration: ");
            sb.append(String.format("%7.4f",event.values[0]));
            sb.append(" m/s\u00B2\nY acceleration: ");
            sb.append(String.format("%7.4f",event.values[1]));
            sb.append(" m/s\u00B2\nZ acceleration: ");
            sb.append(String.format("%7.4f",event.values[2]));
            sb.append(" m/s\u00B2");
        }
        else if(mSensorType == Sensor.TYPE_PROXIMITY){
            if(event.values[0]<=3)
                sb.append("Near\n");
            else sb.append("Far\n");
        }
        else if(mSensorType==Sensor.TYPE_MAGNETIC_FIELD)
        {
            sb.append("MAGNETIC FIELD\n\n");
            sb.append(String.format("%7.4f",event.values[0])+" \u00B5T\n");
            sb.append(String.format("%7.4f",event.values[1])+" \u00B5T\n");
            sb.append(String.format("%7.4f",event.values[2])+" \u00B5T\n");
        }
        else if(mSensorType==Sensor.TYPE_ORIENTATION)
        {
            sb.append("ORIENTATION\n\n");
            sb.append(String.format("%7.4f",event.values[0])+" \n");
            sb.append(String.format("%7.4f",event.values[1])+" \u00B5T\n");
            sb.append(String.format("%7.4f",event.values[2])+" \u00B5T\n");
        }
        t1.setText(sb);

        t1 = (TextView) findViewById(R.id.t_status);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\nAccuracy: ");
        sb2.append(event.accuracy == 3?"High":event.accuracy==2?"Medium":"Low");
        t1.setText(sb2);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
