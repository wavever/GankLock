package me.wavever.ganklock.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import me.wavever.ganklock.R;

/**
 * Created by waveverht on 2016/9/30.
 */

public class LockMainView extends LinearLayout{

    public LockMainView(Context context) {
        this(context,null);
    }


    public LockMainView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public LockMainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.TRANSPARENT);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
        setOrientation(VERTICAL);
        setLayoutParams(params);

        TextView textView  = new TextView(context);
        textView.setText("主界面");
        textView.setTextSize(20);
        textView.setTextColor(R.color.black);
        addView(textView);

    }
}
