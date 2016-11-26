package me.wavever.ganklock.keyguard;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.service.LockService;

/**
 * Created by wavever on 2016/4/2.
 */
public class LockManager {

    private static LockViewTest mLockView;
    private static WindowManager windowManager;

    public static void startLock(boolean isShow) {
        ((KeyguardManager) MyApplication.getContext()
            .getSystemService(Context.KEYGUARD_SERVICE))
            .newKeyguardLock("GankLock").disableKeyguard();
        Intent intent = new Intent(MyApplication.getContext(),LockService.class);
        intent.putExtra("key_show_immediately",isShow);
        MyApplication.getContext().startService(intent);
    }


    public static void cancleLock() {
        ((KeyguardManager) MyApplication.getContext()
            .getSystemService(Context.KEYGUARD_SERVICE))
            .newKeyguardLock("GankLock").reenableKeyguard();
        MyApplication.getContext().stopService(new Intent(MyApplication.getContext(), LockService.class));
    }

    public static void createLockView() {
        //Intent intent = new Intent(MyApplication.getContext(), KeepLiveActivity.class);
        //intent.addFlags(268435456);
        //MyApplication.getContext().startActivity(intent);
        windowManager = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        LayoutParams lp = new LayoutParams();
        //lp.width = -1;
        //lp.height = -1;
        lp.format = PixelFormat.TRANSLUCENT;
        lp.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR;
        lp.type = 2010;//TYPE_SYSTEM_ERROR2010 TYPE_SYSTEM_ALERT2003
        lp.flags |= LayoutParams.FLAG_SHOW_WHEN_LOCKED | LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        mLockView = new LockViewTest(MyApplication.getContext());
        //mLockView.setSystemUiVisibility(4866);
        windowManager.addView(mLockView, lp);
    }

    public static void removeLockView() {
        windowManager.removeView(mLockView);
    }

}
