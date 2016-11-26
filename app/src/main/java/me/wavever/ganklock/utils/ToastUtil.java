package me.wavever.ganklock.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wavever on 2016/8/14.
 */
public class ToastUtil {

    private static Toast sToast;

    public static void showToastShort(Context context, String content) {
        if (sToast == null) {
            sToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(content);
        }
        sToast.show();
    }

    public static void showToastLong(Context context, String content) {
        if (sToast == null) {
            sToast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        } else {
            sToast.setText(content);
        }
        sToast.show();
    }

    public static void showToastShort(Context context, int resId) {
        showToastShort(context, context.getResources().getString(resId));
    }

    public static void showToastLong(Context context, int resId) {
        showToastLong(context, context.getResources().getString(resId));
    }
}
