package me.wavever.ganklock;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

import com.activeandroid.app.Application;

import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.service.LockService;
import me.wavever.ganklock.utils.PreferenceUtil;

/**
 * Created by wavever on 2016/3/2.
 */
public class MyApplication extends Application{

    private static Context context;

    private static PreferenceUtil sp;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (PreferenceUtil.getBoolean(Config.GANK_LOCK_IS_OPEN)) {
            context.startService(new Intent(context, LockService.class));
        }
    }

    /**
     * 获得一个全局的Context对象
     */
    public static Context getContext() {
        return context;
    }
}
