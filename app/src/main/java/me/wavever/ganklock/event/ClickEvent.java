package me.wavever.ganklock.event;

import me.wavever.ganklock.model.bean.GankDaily;

/**
 * Created by wavever on 2016/9/26.
 */

public class ClickEvent {

    public static final int CLICK_TYPE_DAILY_PHOTO = 0;
    public static final int CLICK_TYPE_DAILY_TITLE = 1;

    public static final int CLICK_TYPE_MEIZHI = 2;

    public int eventType;

    public GankDaily gankDaily;

    public int position;

    public ClickEvent(int eventType,GankDaily gankDaily) {
        this.eventType = eventType;
        this.gankDaily = gankDaily;
    }

    public ClickEvent(int eventType,int position) {
        this.eventType = eventType;
        this.position = position;
    }


}
