package com.example.dex;

import android.util.Log;
import android.view.Surface;
import android.view.SurfaceControl;

public final class Main {

    private static final String TAG = "imgui-MB";

    public static void main(String[] args) {

        Log.i(TAG, "================================");
        Log.i(TAG, " SurfaceControl test");
        Log.i(TAG, "================================");

        try {
            Log.i(TAG, "SDK = " +
                    android.os.Build.VERSION.SDK_INT);

            Log.i(TAG, "Creating SurfaceControl...");

            SurfaceControl control =
                    new SurfaceControl.Builder()
                            .setName("imgui-MB-Test")
                            .setBufferSize(800, 600)
                            .build();

            Log.i(TAG, "SurfaceControl created");

            Surface surface =
                    new Surface(control);

            Log.i(TAG, "Surface created");
            Log.i(TAG, "isValid = " + surface.isValid());

            SurfaceControl.Transaction transaction =
                    new SurfaceControl.Transaction();

            transaction
                    .setLayer(control, 100000)
                    .show(control)
                    .apply();

            Log.i(TAG, "Transaction applied");

            /*
             * 暂时不要退出。
             *
             * 保持 SurfaceControl 存活，
             * 方便后面接 EGL。
             */
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }

        } catch (Throwable e) {

            Log.e(
                    TAG,
                    "SurfaceControl test failed",
                    e
            );
        }
    }
}
