package me.wavever.ganklock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.ui.activity.LockActivity;

/**
 * Created by wavever on 2015/12/24.
 */
public class GankLockReceiver extends BroadcastReceiver {

    private static final String TAG = GankLockReceiver.class.getSimpleName();


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        /*if (action.equals(Intent.ACTION_SCREEN_ON)) {
            LockManager.createLockView();
            onPhoneRing();
        }*/
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Intent mLockIntent = new Intent(context, LockActivity.class);
            mLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(mLockIntent);
        }
    }


    public void onPhoneRing() {
        //手机处于响铃状态
        if (((TelephonyManager) MyApplication.getContext()
            .getSystemService("phone")).getCallState() == TelephonyManager.CALL_STATE_IDLE) {
        }
    }

}
