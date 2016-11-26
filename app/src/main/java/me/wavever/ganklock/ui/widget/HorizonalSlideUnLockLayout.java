package me.wavever.ganklock.ui.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import me.wavever.ganklock.keyguard.LockManager;
import me.wavever.ganklock.utils.UIUtil;

/**
 * Created by wavever on 2016/7/24.
 */
public class HorizonalSlideUnLockLayout extends RelativeLayout {

    private float mStartX;
    private int mWidth = UIUtil.getScreenSize()[0];
    private HorizonalSlideUnLockLayout mMoveView;
    private OnSwipeUnLockListener onHorizonalLayoutUnLock;

    public HorizonalSlideUnLockLayout(Context context) {
        this(context, null);
    }

    public HorizonalSlideUnLockLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizonalSlideUnLockLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMoveView = this;
        //setBackgroundColor(Color.TRANSPARENT);
    }


    public void setOnHorizonalLayoutUnLock(OnSwipeUnLockListener onHorizonalLayoutUnLock) {
        this.onHorizonalLayoutUnLock = onHorizonalLayoutUnLock;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final float x = event.getX();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                handlerMoveView(x);
                break;
            case MotionEvent.ACTION_UP:
                doTriggerEvent(x);
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;//返回true表示，touch事件由该layout处理
    }

    /**
     * 控制View随手指的移动
     *
     * @param x
     */
    private void handlerMoveView(float x) {
        float moveX = x - mStartX;
        if (moveX < 0)
            moveX = 0;
        mMoveView.setTranslationX(moveX);
    }

    /**
     * 处理手指离开后，View的移动
     *
     * @param x
     */
    private void doTriggerEvent(float x) {
        float moveX = x - mStartX;
        ObjectAnimator animator;
        if(moveX > mWidth / 3){
            animator = ObjectAnimator.ofFloat(mMoveView,"translationX",mWidth-mMoveView.getLeft());
            onHorizonalLayoutUnLock.onUnLock();//回调解锁事件
            LockManager.removeLockView();
        }else{
            animator = ObjectAnimator.ofFloat(mMoveView,"translationX",-mMoveView.getLeft());
        }
        animator.setDuration(250).start();
    }

    public interface OnSwipeUnLockListener{
        void onSwipe();
        void onUnLock();
    }
}

