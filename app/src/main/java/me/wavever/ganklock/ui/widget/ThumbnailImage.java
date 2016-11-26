package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by wavever on 2016/10/14.
 */

public class ThumbnailImage extends ImageView{

    public ThumbnailImage(Context context) {
        this(context,null);
    }


    public ThumbnailImage(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public ThumbnailImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
