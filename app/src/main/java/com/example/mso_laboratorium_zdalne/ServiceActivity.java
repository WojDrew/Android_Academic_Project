package com.example.mso_laboratorium_zdalne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mso_laboratorium_zdalne.services.MultiplyService;

public class ServiceActivity extends AppCompatActivity {
    private MultiplyService multiplyService;
    private boolean isBound = false;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            MultiplyService.MyBinder binder = (MultiplyService.MyBinder) service;
            multiplyService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent multiplyIntent = new Intent(this, MultiplyService.class);
        bindService(multiplyIntent,mConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(mConnection);
            isBound = false;
        }
    }

    public void obliczOnClick(View view){
        EditText editText = findViewById(R.id.editText2);
        int a = Integer.parseInt(editText.getText().toString());
        editText = findViewById(R.id.editText3);
        int b = Integer.parseInt(editText.getText().toString());
        Integer out = multiplyService.multiply(a,b);
        TextView textView = findViewById(R.id.textView2);
        textView.setText(out.toString());
    }
}
