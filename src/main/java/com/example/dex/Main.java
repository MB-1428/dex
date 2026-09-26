package com.example.dex;

import android.app.Activity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Main extends Activity
        implements SurfaceHolder.Callback {

    private SurfaceView surfaceView;
    private Thread renderThread;
    private volatile boolean running;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        surfaceView = new SurfaceView(this);

        surfaceView
                .getHolder()
                .addCallback(this);

        setContentView(surfaceView);
    }

    @Override
    public void surfaceCreated(
            SurfaceHolder holder) {

        Surface surface = holder.getSurface();

        if (!NativeBridge.createFromSurface(surface)) {
            return;
        }

        running = true;

        renderThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        while (running) {
                            NativeBridge.drawFrame();

                            try {
                                Thread.sleep(16);
                            } catch (InterruptedException e) {
                                break;
                            }
                        }
                    }
                });

        renderThread.start();
    }

    @Override
    public void surfaceDestroyed(
            SurfaceHolder holder) {

        running = false;

        if (renderThread != null) {
            renderThread.interrupt();
            renderThread = null;
        }

        NativeBridge.destroy();
    }

    @Override
    public void surfaceChanged(
            SurfaceHolder holder,
            int format,
            int width,
            int height) {
    }
}