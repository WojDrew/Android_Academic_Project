package com.example.mso_laboratorium_zdalne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void drawingOnClick(View view) {
        Intent drawingActivityIntent = new Intent(getApplicationContext(),DrawingActivity.class);
        startActivity(drawingActivityIntent);
    }

    public void dbOnClick(View view) {
        Intent dBActivityIntent = new Intent(getApplicationContext(),DBActivity.class);
        startActivity(dBActivityIntent);
    }

    public void serviceOnClick(View view) {
        Intent sActivityIntent = new Intent(getApplicationContext(),ServiceActivity.class);
        startActivity(sActivityIntent);
    }

    public void serverOnClick(View view) {
        Intent serverActivityIntent = new Intent(getApplicationContext(),ServerActivity.class);
        startActivity(serverActivityIntent);
    }

    public void ndkOnClick(View view) {
        Intent ndkActivityIntent = new Intent(getApplicationContext(),NDKActivity.class);
        startActivity(ndkActivityIntent);
    }
}
