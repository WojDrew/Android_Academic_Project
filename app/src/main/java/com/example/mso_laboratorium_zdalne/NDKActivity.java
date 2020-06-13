package com.example.mso_laboratorium_zdalne;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mso_laboratorium_zdalne.natives.NativeLib;

public class NDKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_d_k);
    }

    public void getStrClick(View view) {
        int[] a = {1,3,2,6,1,8};

        TextView przed = findViewById(R.id.textViewPrzed);
        for(int i = 0; i < 6; i++)
            przed.append(Integer.valueOf(a[i]).toString());
        int[] b = NativeLib.sort(a);
        TextView po = findViewById(R.id.textViewPo);
        for(int i = 0; i < 6; i++)
            po.append(Integer.valueOf(b[i]).toString());
    }

}