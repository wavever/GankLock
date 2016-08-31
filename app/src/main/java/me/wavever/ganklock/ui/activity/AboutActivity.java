package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import me.wavever.ganklock.BuildConfig;
import me.wavever.ganklock.R;

/**
 * Created by wavever on 2016/3/12.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (mToolbar != null && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/
        setContentView(R.layout.activity_about);
        TextView mTvVersion = (TextView) findViewById(R.id.tv_version);
        mTvVersion.setText(R.string.app_version_code+BuildConfig.VERSION_CODE);
    }


}
