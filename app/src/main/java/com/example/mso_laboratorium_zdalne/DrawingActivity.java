package com.example.mso_laboratorium_zdalne;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;


public class DrawingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Zakonczyc?");
        builder.setCancelable(true);
        builder.setPositiveButton("Tak", (dialog, which) -> {
            super.onBackPressed();
        });
        builder.setNegativeButton("Nie", (dialog, which) -> {

        });
        builder.create().show();
    }
}
