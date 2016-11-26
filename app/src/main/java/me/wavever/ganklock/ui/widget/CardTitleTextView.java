package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import me.wavever.ganklock.utils.StringUtil;

/**
 * Created by wavever on 2016/9/23.
 */

public class CardTitleTextView extends TextView{
    public CardTitleTextView(Context context) {
        this(context,null);
    }


    public CardTitleTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public CardTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(StringUtil.getTypeFace("jdjl.TTF"));
    }
}
