package me.wavever.ganklock.utils;

import android.util.Log;

/**
 * Created by WAVE on 2016/2/23.
 */
public class LogUtil {

    private static final String TAG = "TAG";

    public static final int VERBOSE = 1;

    public static final int DEBUG = 2;

    public static final int INFO = 3;

    public static final int WRAN = 4;

    public static final int ERROR = 5;

    public static final int NOTHING = 6;

    public static final int LEVEL = VERBOSE;

    //只需要修改LEVEL的值，就可以自由的控制日志的打印，
    // 当LEVEL为NOTHING时，就可以屏蔽掉所有的日志

    public static void v(String message) {
        if (LEVEL <= VERBOSE) {
            Log.v(TAG, message);
        }
    }

    public static void d(String message) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG,message);
        }
    }

    public static void i(String message) {
        if (LEVEL <= INFO) {
            Log.i(TAG,message);
        }
    }

    public static void w(String message) {
        if (LEVEL <= WRAN) {
            Log.w(TAG,message);
        }
    }

    public static void e(String message) {
        if (LEVEL <= ERROR) {
            Log.e(TAG,message);
        }
    }

}
