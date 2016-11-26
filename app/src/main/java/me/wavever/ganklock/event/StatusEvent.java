package me.wavever.ganklock.event;

import android.os.Bundle;

/**
 * Created by wavever on 2016/9/29.
 */

public class StatusEvent {

    public static String TYPE_ON_PHOTO_FILE_CHANGE = "file_change";
    public static String TYPE_ON_LIKE_CHANGE = "like_change";
    public static String TYPE_OPEN_GANK_LOCK = "open_gank_lock";
    public static String TYPE_CLOSE_GANK_LOCK = "close_gank_lock";

    public String type;

    public Bundle bundle;


    public StatusEvent(String type) {
        this(type, null);
    }


    public StatusEvent(String type, Bundle bundle) {
        this.type = type;
        this.bundle = bundle;
    }
}
