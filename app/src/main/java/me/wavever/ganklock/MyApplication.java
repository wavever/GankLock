package me.wavever.ganklock;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import com.activeandroid.app.Application;
import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bilibili.magicasakura.utils.ThemeUtils.switchColor;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.service.LockService;
import me.wavever.ganklock.theme.ThemeHelper;
import me.wavever.ganklock.utils.PreferenceUtil;

/**
 * Created by wavever on 2016/3/2.
 */
public class MyApplication extends Application implements switchColor {

    private static Context context;

    private static PreferenceUtil sp;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (PreferenceUtil.getBoolean(Config.GANK_LOCK_IS_OPEN)) {
            context.startService(new Intent(context, LockService.class));
        }
        ThemeUtils.setSwitchColor(this);
    }


    /**
     * 获得一个全局的Context对象
     */
    public static Context getContext() {
        return context;
    }


    @Override public int replaceColorById(Context context, @ColorRes int colorId) {
        if (ThemeHelper.isDefaultTheme()) {
            return context.getResources().getColor(colorId);
        }
        String theme = getThemes();
        if (theme != null) {
            colorId = getThemeColorId(context, colorId, theme);
        }
        return context.getResources().getColor(colorId);
    }


    private String getThemes() {
        if (ThemeHelper.getTheme() == ThemeHelper.THEME_PINK) {
            return "pink";
        } else if (ThemeHelper.getTheme() == ThemeHelper.THEME_BLUE) {
            return "blue";
        } else if (ThemeHelper.getTheme() == ThemeHelper.THEME_GREEN) {
            return "green";
        } else if (ThemeHelper.getTheme() == ThemeHelper.THEME_GREEN_LIGHT) {
            return "green_light";
        } else if (ThemeHelper.getTheme() == ThemeHelper.THEME_YELLOW) {
            return "yellow";
        } else if (ThemeHelper.getTheme() == ThemeHelper.THEME_ORANGE) {
            return "orange";
        } else if (ThemeHelper.getTheme() == ThemeHelper.THEME_RED) {
            return "red";
        }
        return null;
    }


    @Override public int replaceColor(Context context, @ColorInt int originColor) {
        if (ThemeHelper.isDefaultTheme()) {
            return originColor;
        }
        String theme = getThemes();
        int colorId = -1;

        if (theme != null) {
            colorId = getThemeColor(context, originColor, theme);
        }
        return colorId != -1 ? getResources().getColor(colorId) : originColor;
    }


    private
    @ColorRes
    int getThemeColorId(Context context, int colorId, String theme) {
        switch (colorId) {
            case R.color.theme_color_primary:
                return context.getResources().getIdentifier(theme, "color", getPackageName());
            case R.color.theme_color_primary_dark:
                return context.getResources()
                    .getIdentifier(theme + "_dark", "color", getPackageName());
            case R.color.theme_color_primary_trans:
                return context.getResources()
                    .getIdentifier(theme + "_trans", "color", getPackageName());
        }
        return colorId;
    }


    private
    @ColorRes
    int getThemeColor(Context context, int color, String theme) {
        switch (color) {
            case 0xfffb7299:
                return context.getResources().getIdentifier(theme, "color", getPackageName());
            case 0xffb85671:
                return context.getResources()
                    .getIdentifier(theme + "_dark", "color", getPackageName());
            case 0x99f0486c:
                return context.getResources()
                    .getIdentifier(theme + "_trans", "color", getPackageName());
        }
        return -1;
    }
}
