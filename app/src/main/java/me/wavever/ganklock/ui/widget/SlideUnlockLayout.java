package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import me.wavever.ganklock.util.DisplayUtil;

/**
 * Created by WAVE on 2016/3/10.
 */
public class SlideUnlockLayout extends RelativeLayout {

    //解锁屏幕的滑动距离
    private int unlockYoffset;
    private boolean isUnLock = false;
    private OnUnLockListener onUnLockListener;

    public SlideUnlockLayout(Context context) {
        this(context, null, 0);
    }


    public SlideUnlockLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SlideUnlockLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnUnLockListener(OnUnLockListener onUnLockListener){
        this.onUnLockListener = onUnLockListener;
    }


    /**
     * 加载完xml回调的方法
     */
    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        unlockYoffset = DisplayUtil.getScreenHeight() / 5;
    }


    int lastY;

    @Override public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = y - lastY;
                if(!isUnLock(moveY)){
                    scrollBy(0,moveY);
                }else{
                    isUnLock = true;
                    onUnLockListener.unLock();
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean isUnLock(int moveY){
        if(Math.abs(moveY)>=Math.abs(unlockYoffset)){
            return true;
        }
        return false;
    }

    public interface OnUnLockListener{
        void unLock();
    }

}
