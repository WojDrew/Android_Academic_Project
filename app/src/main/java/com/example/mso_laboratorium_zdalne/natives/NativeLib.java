package com.example.mso_laboratorium_zdalne.natives;

public class NativeLib {
    static {
        System.loadLibrary("native_func");
    }

    public static native int[]  sort(int[] a);
}
