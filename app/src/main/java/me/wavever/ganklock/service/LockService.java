package me.wavever.ganklock.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.receiver.GankLockReceiver;

/**
 * Created by WAVE on 2015/12/26.
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
        unregisterReceiver(receiver);
        if(MyApplication.getSp().getBoolean(Config.LOCK_IS_OPEN,false)){
            startService(new Intent(LockService.this,LockService.class));
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
