package me.wavever.ganklock.theme;

import me.wavever.ganklock.utils.PreferenceUtil;

/**
 * Created by wavever on 2016/10/10.
 */

public class ThemeHelper {
    private static final String TAG = ThemeHelper.class.getSimpleName();
    private static final String CURRENT_THEME = "theme_current";

    public static final int THEME_MAIN = 0x1;
    public static final int THEME_PINK = 0x2;
    public static final int THEME_BLUE = 0x3;
    public static final int THEME_GREEN = 0x4;
    public static final int THEME_GREEN_LIGHT = 0x5;
    public static final int THEME_YELLOW = 0x6;
    public static final int THEME_ORANGE = 0x7;
    public static final int THEME_RED = 0x8;

    public static void setTheme(int themeId) {
        PreferenceUtil.putInt(CURRENT_THEME,themeId);
    }

    public static int getTheme() {
        return PreferenceUtil.getInt(CURRENT_THEME,THEME_MAIN);
    }

    public static boolean isDefaultTheme() {
        return getTheme() == THEME_MAIN;
    }

    public static String getName(int currentTheme) {
        switch (currentTheme) {
            case THEME_MAIN:
                return "THEME_MAIN";
            case THEME_PINK:
                return "THEME_PINK";
            case THEME_BLUE:
                return "THEME_BLUE";
            case THEME_GREEN:
                return "THEME_GREEN";
            case THEME_GREEN_LIGHT:
                return "THEME_GREEN_LIGHT";
            case THEME_YELLOW:
                return "THEME_YELLOW";
            case THEME_ORANGE:
                return "THEME_ORANGE";
            case THEME_RED:
                return "THEME_RED";
        }
        return "";
    }
}
