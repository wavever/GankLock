package me.wavever.ganklock.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import me.wavever.ganklock.MyApplication;

/**
 * Created by WAVE on 2016/3/10.
 */
public class DisplayUtil {

    /**
     * 获取屏幕宽高
     *
     * @param
     * @return
     */
    public static int[] getScreenSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager
                = (WindowManager) MyApplication.getContext()
                .getSystemService(
                        Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }

    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(int sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.getResources().getDisplayMetrics());
    }

}
