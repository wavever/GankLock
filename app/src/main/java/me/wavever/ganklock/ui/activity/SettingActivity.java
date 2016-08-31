package me.wavever.ganklock.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.fragment.SettingFragment;

/**
 * Created by WAVE on 2016/2/23.
 */
public class SettingActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        replaceFragment(R.id.setting_container, new SettingFragment());
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("设置");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    public void replaceFragment(int resId, Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(resId, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
