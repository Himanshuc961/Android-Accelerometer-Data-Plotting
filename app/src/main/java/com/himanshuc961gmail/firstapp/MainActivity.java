package com.himanshuc961gmail.firstapp;

import java.util.ArrayList;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView ;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;



public class MainActivity extends Activity implements SensorEventListener{

    private TextView xText, yText, zText;
    private Button btnStart, btnStop ;

    private Sensor mySensor;
    private SensorManager SM;

    private float x = 0;
    private float y = 0;
    private float z = 0;
    private double acc = 0;
    private int count = 0;

    private int state = 0;

    private LineGraphSeries<DataPoint> series;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create our Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Assign TextView
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);

        // Assign Button
       /* btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnStart.setEnabled(true);
        btnStop.setEnabled(false); */

        // Initializing Graph
        series = new LineGraphSeries<>();
        series.appendData(new DataPoint(0, 0), true, 200);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        /* series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        }); */
        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(200);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
         x = event.values[0] ;
         y = event.values[1] ;
         z = event.values[2] ;
        acc = Math.sqrt(x*x+y*y+z*z);
        xText.setText("X: " + x);
        yText.setText("Y: " + y);
        zText.setText("Z: " + z);

        if (state == 1){
            count++;
            series.appendData(new DataPoint(count, acc), true, 200);
            //graph.addSeries(series);
        }
    }
    public void startfn(View v){
        state = 1;

    }
    public void stopfn(View v){

          state = 0;
    }

}