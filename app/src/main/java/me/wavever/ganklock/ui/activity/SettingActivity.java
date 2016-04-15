package me.wavever.ganklock.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.fragment.SettingFragment;

/**
 * Created by WAVE on 2016/2/23.
 */
public class SettingActivity extends BaseActivity {


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(R.id.setting_container, new SettingFragment());
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("设置");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    public void replaceFragment(int resId, Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(resId, fragment).commit();
    }

}
