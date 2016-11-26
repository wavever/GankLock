package me.wavever.ganklock.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.keyguard.LockManager;
import me.wavever.ganklock.receiver.GankLockReceiver;
import me.wavever.ganklock.utils.PreferenceUtil;

/**
 * Created by wavever on 2015/12/26.
 */
public class LockService extends Service {

    private BroadcastReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        receiver = new GankLockReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(PreferenceUtil.getBoolean(Config.GANK_LOCK_IS_OPEN)){
            //LockManager.startLock(false);
            startService(new Intent(LockService.this,LockService.class));
            return;
        }
        LockManager.cancleLock();
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
