package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by wavever on 2016/11/29.
 */

public class SwipeItemLayout extends FrameLayout {

    private ViewDragHelper mDragHelper;

    private View mContentView;
    private View mSwipeView;


    public SwipeItemLayout(Context context) {
        this(context, null);
    }


    public SwipeItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SwipeItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, 1.0f, mDragHelperCallback);
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        mSwipeView = getChildAt(0);
        mContentView = getChildAt(1);
    }


    private Callback mDragHelperCallback = new Callback() {

        @Override public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }

    };

}
