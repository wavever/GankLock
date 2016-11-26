package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by wavever on 2016/10/19.
 * 可以左滑操作的layout
 */

public class SlideableLayout extends FrameLayout {

    private ViewDragHelper mDragger;

    public SlideableLayout(Context context) {
        this(context,null);
    }


    public SlideableLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public SlideableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragger = ViewDragHelper.create(this, 1.0f, new SlideCallback());
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }


    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragger.shouldInterceptTouchEvent(ev);
    }


    class SlideCallback extends ViewDragHelper.Callback{

        @Override public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }
    }
}
