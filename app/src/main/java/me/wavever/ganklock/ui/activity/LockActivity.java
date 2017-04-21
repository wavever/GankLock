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
import android.widget.ViewSwitcher;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.LockPresenter;
import me.wavever.ganklock.ui.widget.SwipeUnLockLayout;
import me.wavever.ganklock.utils.DateUtil;
import me.wavever.ganklock.utils.PreferenceUtil;
import me.wavever.ganklock.view.ILockView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wavever on 2016/10/14.
 */

public class LockActivity extends BaseMvpActivity<ILockView,LockPresenter> implements ILockView,SwipeUnLockLayout.OnSwipeListener {

    private static final String TAG = "LockActivity-->";

    private ViewSwitcher mViewSwitcher;
    private ImageView mImg;
    private TextView mTitle;
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
        mViewSwitcher = (ViewSwitcher) findViewById(R.id.lock_view_switcher);
        mTitle = (TextView) findViewById(R.id.lock_view_gank_title);
        TextView mLockViewDate = (TextView) findViewById(R.id.lock_view_date);
        final String lockDateText = DateUtil.getLockDateText();
        mLockViewDate.setText(lockDateText);
        mImg = (ImageView) findViewById(R.id.lock_view_img);
        url = PreferenceUtil.getString("url");
        if (url.isEmpty()) {
            mImg.setImageResource(R.drawable.test_image);
        } else {
           getBitmap();
        }
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mViewSwitcher.showNext();
            }
        });

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

    @Override public void onSwipeFinish() {
        finish();
    }

    @Override public LockPresenter createPresenter() {
        return new LockPresenter();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setUI();
        }
    }


    @Override public void getTodayGankData() {

    }


    private void getBitmap(){
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override public void call(Subscriber<? super Bitmap> subscriber) {
                try {
                    bitmap = Picasso.with(LockActivity.this).load(url).get();
                    subscriber.onNext(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .subscribe(new Action1<Bitmap>() {
                @Override public void call(Bitmap bitmap) {
                    mImg.setImageBitmap(bitmap);
                }
            });

    }
}
