package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.widget.TextView;
import me.wavever.ganklock.BuildConfig;
import me.wavever.ganklock.R;

/**
 * Created by WAVE on 2016/3/12.
 */
public class AboutActivity extends BaseActivity{

    private TextView mTvVersion;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTvVersion = (TextView) findViewById(R.id.tv_version);
        mTvVersion.setText("版本："+BuildConfig.VERSION_NAME);
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_about ;
    }

    @Override protected void initPresenter() {

    }
}
