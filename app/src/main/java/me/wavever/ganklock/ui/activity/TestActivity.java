package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.widget.SwipeUnLockLayout;
import me.wavever.ganklock.ui.widget.SwipeUnLockLayout.OnSwipeListener;
import me.wavever.ganklock.utils.DateUtil;

/**
 * Created by wavever on 2016/8/4.
 */
public class TestActivity extends AppCompatActivity implements OnSwipeListener{

    private TextView mDate;
    private SwipeUnLockLayout swipeUnLockLayout;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_lock_test);
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
        mDate = (TextView) findViewById(R.id.lock_view_date_test);
        mDate.setText(DateUtil.getLockDateText());
        swipeUnLockLayout = (SwipeUnLockLayout) findViewById(R.id.slide_layout_test);
        swipeUnLockLayout.setOnSwipeListener(this);
    }

    @Override public void onSwipeFinish() {
        finish();
    }
}
