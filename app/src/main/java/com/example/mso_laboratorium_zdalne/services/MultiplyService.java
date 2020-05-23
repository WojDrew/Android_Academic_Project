package com.example.mso_laboratorium_zdalne.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MultiplyService extends Service {
    private final IBinder mBinder = new MyBinder();
    public MultiplyService() {
    }

    public class MyBinder extends Binder {
        public MultiplyService getService() {
            return MultiplyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int multiply(int a, int b) {
        return a*b;
    }
}
