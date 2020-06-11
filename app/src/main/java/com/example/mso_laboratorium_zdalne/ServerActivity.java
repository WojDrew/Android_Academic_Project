package com.example.mso_laboratorium_zdalne;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;


public class ServerActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private int delay;
    private  AsyncTask<Object,Object,Void> at;
    private  AsyncTask<Object,Object,Void> sender;
    private ServerSocket ss;
    private float x, y, z;
    private Socket socket;
    private boolean disconnected;

    public ServerActivity() {
        delay = 5000;
        x = 0;
        y = 0;
        z = 0;
        socket = null;
        disconnected = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @SuppressLint("StaticFieldLeak")
    public void startServer(View view) {
        at = new AsyncTask<Object, Object, Void>() {
            @Override
            protected Void doInBackground(Object... params) {
                try {
                    ss = new ServerSocket(0);
                    Log.i("LocalPort", "" + ss.getLocalPort());

                    //listening
                    while (true) {
                        if(socket == null || disconnected) {
                            socket = ss.accept();
                            Log.i("ss.accept", "" + ss.getLocalPort());
                            disconnected = false;
                        }
                        OutputStream output = socket.getOutputStream();
                        PrintWriter writer = new PrintWriter(output, true);
                        writer.println("x: " + x + " y: " + y + " z: " + z);

                        if(isCancelled())
                            break;
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {

                        }
                        byte[] buf = new byte[4];
                        socket.getInputStream().read(buf);
                        ByteBuffer wrapped = ByteBuffer.wrap(buf);
                        int num = wrapped.getInt();
                        if(num == 1)
                            disconnected = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                try {
                    Log.i("server:"," stop");
                    socket.close();
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        at.execute((Object[]) null);

    }

    public void stopServer(View view) {
        if(at.getStatus() == AsyncTask.Status.RUNNING)
            at.cancel(true);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
