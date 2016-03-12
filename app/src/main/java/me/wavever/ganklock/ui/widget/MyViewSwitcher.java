package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Scroller;
import android.widget.ViewSwitcher;
import me.wavever.ganklock.util.DisplayUtil;

/**
 * Created by WAVE on 2016/3/10.
 */
public class MyViewSwitcher extends ViewSwitcher {

    Scroller scroller;


    public MyViewSwitcher(Context context) {
        super(context);
        scroller = new Scroller(context);
    }


    public MyViewSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }


    @Override public void computeScroll() {
        super.computeScroll();
        if (!scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            postInvalidate();
        }
    }


    int lastX;
    int startX;
    int endX;
    int screenWidth = DisplayUtil.getScreenWidth();

    @Override public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int mCurrentView = getDisplayedChild();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                startX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                int offsetX = lastX - x;
                if (getScrollX() < 0) {
                    offsetX = 0;
                }
                if (getScrollX() > getWidth() - screenWidth) {
                    offsetX = 0;
                }
                scrollBy(offsetX, 0);
                lastX = x;
                break;
            case MotionEvent.ACTION_UP:
                endX = getScrollX();
                int scrollX = endX - startX;
                break;
        }
        return super.onTouchEvent(event);
    }

}
