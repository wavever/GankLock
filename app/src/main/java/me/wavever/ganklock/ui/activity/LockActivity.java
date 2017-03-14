package me.wavever.ganklock.ui.activity;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.widget.SwipeUnLockLayout;
import me.wavever.ganklock.ui.widget.SwipeUnLockLayout.OnSwipeListener;
import me.wavever.ganklock.utils.DateUtil;
import me.wavever.ganklock.utils.PreferenceUtil;

/**
 * Created by wavever on 2016/10/14.
 */

public class LockActivity extends BaseActivity implements OnSwipeListener {

    private static final String TAG = LockActivity.class.getSimpleName();

    private TextView mLockViewDate;
    private ImageView mImg;
    private Bitmap bitmap;
    private String url;


    @Override protected int loadView() {
        return R.layout.activity_lock;
    }


    @Override protected void initView() {
        getWindow().addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setUI();
        SwipeUnLockLayout swipeUnLockLayout = (SwipeUnLockLayout) findViewById(
            R.id.slide_layout);
        swipeUnLockLayout.setOnSwipeListener(this);
        mLockViewDate = (TextView) findViewById(R.id.lock_view_date);
        mLockViewDate.setText(DateUtil.getLockDateText());
        mImg = (ImageView) findViewById(R.id.lock_view_img);
        url = PreferenceUtil.getString("url");
        if (url.isEmpty()) {
            mImg.setImageResource(R.drawable.test_image);
        } else {
            new Thread(new Runnable() {
                @Override public void run() {
                    try {
                        bitmap = Picasso.with(LockActivity.this).load(url).get();
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setUI();
        }
    }

    private void setUI() {
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //这个属性重复设置会导致NAVIGATION BAR显示
            //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0);
        }
    }
}
