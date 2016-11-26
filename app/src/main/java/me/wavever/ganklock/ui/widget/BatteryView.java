package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wavever on 2016/5/19.
 */
public class BatteryView extends View {

    public BatteryView(Context context) {
        this(context,null);
    }

    public BatteryView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
