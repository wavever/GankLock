package me.wavever.ganklock.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SPUtil(Context context) {
        prefs = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }


    public boolean putString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }


    public String getString(String key,String value) {
        return prefs.getString(key, value);
    }


    public boolean putInt(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }


    public int getInt(String key) {
        return prefs.getInt(key, 1);
    }


    public boolean putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }


    public boolean getBoolean(String key,boolean value) {
        return prefs.getBoolean(key,value);
    }


    public boolean clear() {
        editor.clear();
        return editor.commit();
    }


    public void close() {
        prefs = null;
    }
}
