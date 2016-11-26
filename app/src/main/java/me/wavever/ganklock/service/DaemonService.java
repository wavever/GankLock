package me.wavever.ganklock.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by wavever on 2016/9/26.
 */

public class DaemonService extends Service {
    @Nullable @Override public IBinder onBind(Intent intent) {
        return null;
    }
}
