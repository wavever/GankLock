package me.wavever.ganklock.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import me.wavever.ganklock.R;

/**
 * Created by wavever on 2016/10/11.
 */

public class SwipeUnLockLayout extends FrameLayout {

    private static final int MIN_FLING_VELOCITY = 200;

    //TODO 只能够移动他的直接子View
    private View mTimeLayout;
    private View mArrow;

    private int mLastInterceptX;

    private float mStartX;
    private int mLayoutX;
    private int mArrowX;
    private float minVel;

    private OnSwipeListener mOnSwipeListener;

    public SwipeUnLockLayout(Context context) {
        this(context, null);
    }


    public SwipeUnLockLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SwipeUnLockLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        final float density = getResources().getDisplayMetrics().density;
        minVel = MIN_FLING_VELOCITY * density;
    }


    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.mOnSwipeListener = onSwipeListener;
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastInterceptX = x;
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(x==mLastInterceptX){
                    intercepted = false;
                }else {
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;
        }

        return intercepted;
    }


    @Override public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX= x - mStartX;
                if (moveX < 0)
                    moveX = 0;
                mTimeLayout.setTranslationX(-moveX);
                mArrow.setTranslationX(moveX);
                break;
            case MotionEvent.ACTION_UP:
                float moveX1= x - mStartX;
                ObjectAnimator animator1;
                ObjectAnimator animator2;
                AnimatorSet set = new AnimatorSet();
                if(moveX1 > minVel){
                    animator1 = ObjectAnimator.ofFloat(mTimeLayout,"translationX",-200);
                    animator2 = ObjectAnimator.ofFloat(mArrow,"translationX",-200);
                    set.setDuration(250).playTogether(animator1,animator2);
                    set.addListener(new AnimatorListenerAdapter() {
                        @Override public void onAnimationEnd(Animator animation) {
                            mOnSwipeListener.onSwipeFinish();
                        }
                    });
                }else{
                    animator1 = ObjectAnimator.ofFloat(mTimeLayout,"translationX",mLayoutX);
                    animator2 = ObjectAnimator.ofFloat(mArrow,"translationX",-mArrowX);
                    set.setDuration(250).playTogether(animator1,animator2);
                }
                set.start();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        mTimeLayout = findViewById(R.id.lock_view_time_layout);
        mArrow = findViewById(R.id.lock_view_arrow);
    }


    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //记录初始的位置
        mLayoutX = mTimeLayout.getLeft();
        mArrowX = mArrow.getLeft();
    }

    public interface OnSwipeListener{
        void onSwipeFinish();
    }


}
