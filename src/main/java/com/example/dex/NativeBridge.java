package com.example.dex;

import android.view.Surface;

public final class NativeBridge {

    static {
        System.loadLibrary("imgui-v17");
    }

    private NativeBridge() {
    }

    public static native boolean createFromSurface(
            Surface surface);

    public static native void drawFrame();

    public static native void destroy();
}