package me.wavever.ganklock.util;

import android.content.Context;
import android.view.WindowManager;
import me.wavever.ganklock.MyApplication;

/**
 * Created by WAVE on 2016/3/10.
 */
public class DisplayUtil {

    private static WindowManager manager
            = (WindowManager) MyApplication.getContext()
                                           .getSystemService(
                                                   Context.WINDOW_SERVICE);


    public static int getScreenWidth() {
        return manager.getDefaultDisplay().getWidth();
    }


    public static int getScreenHeight() {
        return manager.getDefaultDisplay().getHeight();
    }
}
