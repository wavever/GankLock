package me.wavever.ganklock.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import me.wavever.ganklock.MyApplication;

public class PreferenceUtil {
    private static SharedPreferences prefs;
    private static Editor editor;


    static {
        prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        editor = prefs.edit();
    }


    public static void putString(String key, String value) {
        editor.putString(key, value).apply();
    }


    public static String getString(String key) {
        return prefs.getString(key, "");
    }


    public static void putInt(String key, int value) {
        editor.putInt(key, value).apply();
    }


    public static int getInt(String key,int value) {
        return prefs.getInt(key, value);
    }


    public static void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value).apply();//apply没有返回值，commit返回值为布尔值
    }

    public static boolean getBoolean(String key) {
        return prefs.getBoolean(key, false);
    }


    public static boolean clear() {
        editor.clear();
        return editor.commit();
    }


    public static void close() {
        prefs = null;
    }
}
