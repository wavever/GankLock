package me.wavever.ganklock.keyguard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wavever on 2016/10/9.
 */

public class BlackActivity extends AppCompatActivity{

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setType(2006);
        getWindow().addFlags(4718592);
    }

    @Override protected void onResume() {
        super.onResume();

    }
}
