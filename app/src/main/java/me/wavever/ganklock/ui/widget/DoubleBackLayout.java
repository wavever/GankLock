package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by WAVE on 2016/3/15.
 */
public class DoubleBackLayout extends RelativeLayout implements
        GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{

    GestureDetector mGestureDetector;

    OnDoubleBackListener onDoubleTouchListener;

    public DoubleBackLayout(Context context) {
        this(context,null);
    }


    public DoubleBackLayout(Context context, AttributeSet attrs) {
        this(context,null,0);
    }


    public DoubleBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void setOnDoubleTouchListener(OnDoubleBackListener onDoubleTouchListener) {
        this.onDoubleTouchListener = onDoubleTouchListener;
    }


    private void init(Context context) {
        mGestureDetector = new GestureDetector(context,this);
        mGestureDetector.setIsLongpressEnabled(false);
    }


    @Override public boolean onTouchEvent(MotionEvent event) {
        boolean b = mGestureDetector.onTouchEvent(event);
        return b;
    }


    @Override public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }


    @Override public boolean onDoubleTap(MotionEvent e) {
        onDoubleTouchListener.onDoubleTouch();
        return false;
    }


    @Override public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }


    @Override public boolean onDown(MotionEvent e) {
        return false;
    }


    @Override public void onShowPress(MotionEvent e) {

    }


    @Override public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }


    @Override public void onLongPress(MotionEvent e) {

    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public interface OnDoubleBackListener{
        void onDoubleTouch();
    }
}
