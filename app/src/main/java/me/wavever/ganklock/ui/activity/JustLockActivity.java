package me.wavever.ganklock.ui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.widget.SwipeUnLockLayout;
import me.wavever.ganklock.ui.widget.SwipeUnLockLayout.OnSwipeListener;
import me.wavever.ganklock.utils.DateUtil;
import me.wavever.ganklock.utils.LogUtil;
import me.wavever.ganklock.utils.PreferenceUtil;

/**
 * Created by wavever on 2016/10/14.
 */

public class JustLockActivity extends BaseActivity implements OnSwipeListener {

    private static final String TAG = JustLockActivity.class.getSimpleName();

    private TextView mLockViewDate;
    private ImageView mImg;
    private Bitmap bitmap;
    private String url;


    @Override protected int loadView() {
        return R.layout.view_lock_test;
    }


    @Override protected void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE);
        SwipeUnLockLayout swipeUnLockLayout = (SwipeUnLockLayout) findViewById(
            R.id.slide_layout_test);
        swipeUnLockLayout.setOnSwipeListener(this);
        mLockViewDate = (TextView) findViewById(R.id.lock_view_date_test);
        mLockViewDate.setText(DateUtil.getLockDateText());
        mImg = (ImageView) findViewById(R.id.lock_view_img_test);
        url = PreferenceUtil.getString("url");
        LogUtil.d(TAG + url);
        if (url.isEmpty()) {
            mImg.setImageResource(R.drawable.gank2);
        } else {
            new Thread(new Runnable() {
                @Override public void run() {
                    try {
                        //同步加载一张图片,注意只能在子线程中调用并且Bitmap不会被缓存到内存里.
                        bitmap = Picasso.with(JustLockActivity.this).load(url).get();
                        runOnUiThread(new Runnable() {
                            @Override public void run() {
                                mImg.setImageBitmap(bitmap);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }


    @Override public void onSwipeFinish() {
        finish();
    }
}
