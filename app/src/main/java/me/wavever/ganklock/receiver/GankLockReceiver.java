package me.wavever.ganklock.receiver;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

import com.activeandroid.query.Select;

import java.util.List;

import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.presenter.GankContentPresenter;
import me.wavever.ganklock.ui.activity.LockView;

/**
 * Created by WAVE on 2015/12/24.
 */
public class GankLockReceiver extends BroadcastReceiver {

    private static final String TAG = GankLockReceiver.class.getSimpleName();

    private KeyguardManager keyguardManager;
    private KeyguardManager.KeyguardLock keyguardLock;
    private View mContainer;
    private String girl;
    private GankContentPresenter gankContentPresenter;
    private LockView mLockView = null;
    private WindowManager windowManager = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        keyguardLock = keyguardManager.newKeyguardLock("GankLock");
        keyguardLock.disableKeyguard();
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            if (MyApplication.getSp().getBoolean(Config.LOCK_IS_OPEN, false)) {
                createLockView(context);
            }
        }
    }


    private void createLockView(Context context) {
        mLockView = new LockView(context);

        windowManager = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = -1;
        lp.height = -1;
        lp.type = 2010;
        lp.flags |= WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        windowManager.addView(mContainer, lp);

        //使用EventBus来传递解锁事件
        windowManager.removeView(mContainer);
    }


    private List<Gank> loadFromDB() {
        List<Gank> ganks = new Select().from(Gank.class).execute();
        return ganks;
    }


}
