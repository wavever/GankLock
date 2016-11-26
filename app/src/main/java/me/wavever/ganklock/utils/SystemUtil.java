package me.wavever.ganklock.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import me.wavever.ganklock.MyApplication;

/**
 * Created by wavever on 2016/9/20.
 */

public class SystemUtil {

    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) MyApplication.getContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return info.isAvailable();
        }
        return false;
    }


    /**
     * 获取当前网络状态：WiFi OR Mobile
     * @return if wifi return 1 or mobile return 1
     */
    public static int getNetType() {
        ConnectivityManager manager = (ConnectivityManager) MyApplication.getContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                        return 1;//wifi
                    } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        return 0;//mobile
                    }
                }
            }
        }
        return -1;
    }

}
