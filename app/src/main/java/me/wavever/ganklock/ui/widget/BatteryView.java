package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import me.wavever.ganklock.R;

/**
 * Created by waveverht on 2016/5/19.
 */
public class BatteryView extends ImageView {

    public static final int[] batteryImgs;

    static {
        batteryImgs = new int[]{R.drawable.l_e_0_9,
                R.drawable.l_e_10_39,
                R.drawable.l_e_40_59,
                R.drawable.l_e_60_90,
                R.drawable.l_e_90_100};
    }

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
