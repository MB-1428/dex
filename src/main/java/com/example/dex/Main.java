package com.example.dex;

import android.util.Log;
import android.view.Surface;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class Main {

    private static final String TAG = "imgui-MB";

    private Main() {
    }

    public static void main(String[] args) {

        Log.i(TAG, "================================");
        Log.i(TAG, " SurfaceControl test");
        Log.i(TAG, "================================");

        try {
            Log.i(
                    TAG,
                    "SDK = " + android.os.Build.VERSION.SDK_INT
            );

            Log.i(TAG, "Loading android.view.SurfaceControl...");

            Class<?> surfaceControlClass =
                    Class.forName("android.view.SurfaceControl");

            Log.i(
                    TAG,
                    "SurfaceControl class found: "
                            + surfaceControlClass
            );

            /*
             * SurfaceControl.Builder
             */
            Class<?> builderClass =
                    Class.forName(
                            "android.view.SurfaceControl$Builder"
                    );

            Log.i(TAG, "Builder class found");

            Constructor<?> builderConstructor =
                    builderClass.getDeclaredConstructor();

            builderConstructor.setAccessible(true);

            Object builder =
                    builderConstructor.newInstance();

            Log.i(TAG, "Builder created");

            /*
             * setName()
             */
            Method setName =
                    builderClass.getMethod(
                            "setName",
                            String.class
                    );

            setName.invoke(
                    builder,
                    "imgui-MB-Test"
            );

            /*
             * setBufferSize()
             */
            Method setBufferSize =
                    builderClass.getMethod(
                            "setBufferSize",
                            int.class,
                            int.class
                    );

            setBufferSize.invoke(
                    builder,
                    800,
                    600
            );

            Log.i(TAG, "Builder configured");

            /*
             * build()
             */
            Method build =
                    builderClass.getMethod("build");

            Object control =
                    build.invoke(builder);

            if (control == null) {
                throw new RuntimeException(
                        "SurfaceControl.build() returned null"
                );
            }

            Log.i(TAG, "SurfaceControl created");

            /*
             * Surface(SurfaceControl)
             */
            Constructor<Surface> surfaceConstructor =
                    Surface.class.getConstructor(
                            surfaceControlClass
                    );

            Surface surface =
                    surfaceConstructor.newInstance(
                            control
                    );

            Log.i(TAG, "Surface created");

            Log.i(
                    TAG,
                    "Surface valid = "
                            + surface.isValid()
            );

            /*
             * Transaction
             */
            Class<?> transactionClass =
                    Class.forName(
                            "android.view.SurfaceControl$Transaction"
                    );

            Constructor<?> transactionConstructor =
                    transactionClass.getDeclaredConstructor();

            transactionConstructor.setAccessible(true);

            Object transaction =
                    transactionConstructor.newInstance();

            Log.i(TAG, "Transaction created");

            /*
             * setLayer()
             */
            Method setLayer =
                    transactionClass.getMethod(
                            "setLayer",
                            surfaceControlClass,
                            int.class
                    );

            setLayer.invoke(
                    transaction,
                    control,
                    100000
            );

            /*
             * show()
             */
            Method show =
                    transactionClass.getMethod(
                            "show",
                            surfaceControlClass
                    );

            show.invoke(
                    transaction,
                    control
            );

            /*
             * apply()
             */
            Method apply =
                    transactionClass.getMethod(
                            "apply"
                    );

            apply.invoke(transaction);

            Log.i(TAG, "Transaction applied");

            Log.i(TAG, "================================");
            Log.i(TAG, " SurfaceControl test SUCCESS");
            Log.i(TAG, "================================");

            /*
             * 保持对象存活。
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
                    "SurfaceControl test FAILED",
                    e
            );
        }
    }
}
