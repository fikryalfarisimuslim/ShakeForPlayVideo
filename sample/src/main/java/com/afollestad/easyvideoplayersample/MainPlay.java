package com.afollestad.easyvideoplayersample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainPlay extends AppCompatActivity implements SensorEventListener {
    Button bPlay;
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    boolean stat = false;
    AlertDialog.Builder alert;
    AlertDialog alert1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_play);

        bPlay = (Button) findViewById(R.id.buttonplay);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            public void onShake() {
                onShake2();
            }
        });




        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stat = true;
                Toast.makeText(MainPlay.this, "Shake to play video!", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void onShake2(){
        if(stat) {
            Vibrator az = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            az.vibrate(1000);
            Toast.makeText(MainPlay.this, "Play Video", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }

    }
    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}
