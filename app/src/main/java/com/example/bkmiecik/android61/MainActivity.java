package com.example.bkmiecik.android61;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        final SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        boolean enabled = !sensorManager.getSensorList(Sensor.TYPE_LIGHT).isEmpty();
        TextView t1 = (TextView) findViewById(R.id.t1);
        t1.setText(enabled ? "Światła dostępne" : "Światła niedostępne");
        t1.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.b1).setEnabled(enabled);

        enabled = !sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty();
        t1 = (TextView) findViewById(R.id.t2);
        t1.setText(enabled?"Przyspieszenie dostępne":"Przyspieszenie dostępne");
        t1.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.b2).setEnabled(enabled);

        final LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        t1 = (TextView) findViewById(R.id.t3);
        t1.setText(enabled?"GPS dostępny":"GPS niedostępny");
        t1.setTextColor(enabled ? Color.GREEN : Color.RED);
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
        }
        startActivity(intent);
    }
}
