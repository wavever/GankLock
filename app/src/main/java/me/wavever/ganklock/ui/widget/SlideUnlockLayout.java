package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import me.wavever.ganklock.util.LogUtil;

/**
 * Created by WAVE on 2016/3/10.
 */
public class SlideUnlockLayout extends RelativeLayout{

    //解锁屏幕的滑动距离
    private int unlockYoffset;
    private boolean isUnLock = false;
    private OnUnLockListener onUnLockListener;

    private int mScreenHeight;

    private Scroller mScroller;

    public SlideUnlockLayout(Context context) {
        this(context, null);
    }


    public SlideUnlockLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SlideUnlockLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScroller = new Scroller(context);
        unlockYoffset = mScreenHeight / 4;
    }

    public void setOnUnLockListener(OnUnLockListener onUnLockListener){
        this.onUnLockListener = onUnLockListener;
    }

    int lastY;
    int startY;
    int endY;

    @Override public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.d("按下ACTION_DOWN");
                lastY = y;
                startY = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d("移动ACTION_MOVE");
                getParent().requestDisallowInterceptTouchEvent(true);
                int moveY = y - lastY;
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                if(!isUnLock(moveY)){
                    scrollBy(0,moveY);
                }else{
                    isUnLock = true;
                    onUnLockListener.unLock();
                }
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.d("松开ACTION_UP");
                endY = getScrollY();
                int scrollY = endY - startY;
                if(scrollY>0){
                    if(scrollY>unlockYoffset){
                        isUnLock= true;
                        onUnLockListener.unLock();
                    }else {
                        mScroller.startScroll(0,getScrollY(),0,-scrollY);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void smoothScrollTo(int destX,int destY){

    }


    /**\
     * 是否解锁
     * @param moveY
     * @return
     */
    private boolean isUnLock(int moveY){
        if(Math.abs(moveY)>=Math.abs(unlockYoffset)){
            return true;
        }
        return false;
    }

    public interface OnUnLockListener{
        void unLock();
    }


    @Override public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }
}
