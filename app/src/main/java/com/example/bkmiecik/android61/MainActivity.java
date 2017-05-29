package com.example.bkmiecik.android61;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Sensor> listSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        final SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        listSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sb = new StringBuilder();
        for(Sensor p:listSensors){
            sb.append(p.getName()+"\n");
        }
        Toast.makeText(this,sb,Toast.LENGTH_LONG).show();

        //Toast.makeText(this, (CharSequence) sensorManager.getSensorList(Sensor.TYPE_ALL),Toast.LENGTH_SHORT).show();

        boolean enabled = !sensorManager.getSensorList(Sensor.TYPE_LIGHT).isEmpty();
        findViewById(R.id.b1).setEnabled(enabled);
        Button b1 = (Button) findViewById(R.id.b1);
        b1.setText("Light");

        enabled = !sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty();
        findViewById(R.id.b2).setEnabled(enabled);
        Button b2 = (Button) findViewById(R.id.b2);
        b2.setText("ACCELEROMETER");

        enabled = !sensorManager.getSensorList(Sensor.TYPE_PROXIMITY).isEmpty();
        findViewById(R.id.b7).setEnabled(enabled);
        Button b7 = (Button) findViewById(R.id.b7);
        b7.setText("PROXIMITY");

        enabled = !sensorManager.getSensorList(Sensor.TYPE_MOTION_DETECT).isEmpty();
        findViewById(R.id.b4).setEnabled(enabled);
        Button b4 = (Button) findViewById(R.id.b4);
        b4.setText("MOTION");

        enabled = !sensorManager.getSensorList(Sensor.TYPE_POSE_6DOF).isEmpty();
        findViewById(R.id.b5).setEnabled(enabled);
        Button b5 = (Button) findViewById(R.id.b5);
        b5.setText("POSE");

        enabled = !sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).isEmpty();
        findViewById(R.id.b6).setEnabled(enabled);
        Button b6 = (Button) findViewById(R.id.b6);
        b6.setText("MAGNETIC_FIELD");

        Button b8 = (Button) findViewById(R.id.b8);
        b8.setText("COMPASS");
        b8.setBackgroundColor(Color.rgb(80,160,240));
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Compass.class);
                startActivity(intent);
            }
        });

        enabled = !sensorManager.getSensorList(Sensor.TYPE_ORIENTATION).isEmpty();
        findViewById(R.id.b9).setEnabled(enabled);
        Button b9 = (Button) findViewById(R.id.b9);
        b9.setText("ORIENTATION");

        final LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        findViewById(R.id.b3).setEnabled(enabled);



        super.onResume();
    }

    public final void startAktywnosc(final View view){
        Intent intent;
        if(view.getId() == R.id.b3) intent = new Intent(this,GPS.class);
        else{
            intent=new Intent(this,ASensor.class);
            if(view.getId() == R.id.b1) intent.putExtra("sensorType",Sensor.TYPE_LIGHT);
            else if(view.getId() == R.id.b2) intent.putExtra("sensorType",Sensor.TYPE_ACCELEROMETER);
            else if(view.getId() == R.id.b6) intent.putExtra("sensorType",Sensor.TYPE_MAGNETIC_FIELD);
            else if(view.getId() == R.id.b7) intent.putExtra("sensorType",Sensor.TYPE_PROXIMITY);
            else if(view.getId() == R.id.b9) intent.putExtra("sensorType",Sensor.TYPE_ORIENTATION);
        }
        startActivity(intent);
    }
}
