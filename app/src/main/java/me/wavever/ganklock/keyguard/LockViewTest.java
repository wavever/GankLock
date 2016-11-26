package me.wavever.ganklock.keyguard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextClock;
import android.widget.TextView;
import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.widget.SwipeUnLockLayout;
import me.wavever.ganklock.ui.widget.SwipeUnLockLayout.OnSwipeListener;
import me.wavever.ganklock.utils.DateUtil;

/**
 * Created by wavever on 2016/10/11.
 */

public class LockViewTest extends FrameLayout implements OnSwipeListener{
    private TextView mLockViewDate;
    private TextClock mLockViewTime;
    private Button mBtn;
    private SwipeUnLockLayout swipeUnLockLayout;


    public LockViewTest(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_lock_test, this);
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        mLockViewDate = (TextView) findViewById(R.id.lock_view_date_test);
        mLockViewTime = (TextClock) findViewById(R.id.lock_view_time_test);
        mLockViewDate.setText(DateUtil.getLockDateText());
        swipeUnLockLayout = (SwipeUnLockLayout) findViewById(R.id.slide_layout_test);
        swipeUnLockLayout.setOnSwipeListener(this);
    }


    @Override public void onSwipeFinish() {
        LockManager.removeLockView();
    }
}
