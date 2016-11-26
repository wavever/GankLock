package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.AttributeSet;
import android.widget.ImageView;

import me.wavever.ganklock.R;

/**
 * Created by wavever on 2016/9/9.
 */
public class WifiView extends ImageView{
    public WifiView(Context context) {
        super(context);
    }

    public WifiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WifiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setWifiState(int i) {
        if (i == 0) {
            setImageResource(R.drawable.ic_lockscreen_wifi_1);
        } else if (i <= 1) {
            setImageResource(R.drawable.ic_lockscreen_wifi_2);
        } else if (i <= 2) {
            setImageResource(R.drawable.ic_lockscreen_wifi_3);
        } else if (i <= 3) {
            setImageResource(R.drawable.ic_lockscreen_wifi_4);
        } else {
            setImageResource(R.drawable.ic_lockscreen_wifi_5);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        changeWifiState();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setVisibility(INVISIBLE);
    }

    private void changeWifiState(){
        NetworkInfo networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getNetworkInfo(NetworkInfo.CONTENTS_FILE_DESCRIPTOR);
        if(networkInfo == null && !networkInfo.isConnectedOrConnecting()){
            setVisibility(INVISIBLE);
            return;
        }
        setVisibility(VISIBLE);
        WifiManager wifiManager = (WifiManager) getContext().getSystemService("wifi");
        if(wifiManager.isWifiEnabled()){
            setWifiState(WifiManager.calculateSignalLevel(wifiManager.getConnectionInfo().getRssi(),5));
        }else {
            setVisibility(INVISIBLE);
        }
    }
}
