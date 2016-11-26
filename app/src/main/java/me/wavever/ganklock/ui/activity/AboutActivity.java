package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.widget.TextView;
import me.wavever.ganklock.BuildConfig;
import me.wavever.ganklock.R;

/**
 * Created by wavever on 2016/3/12.
 */
public class AboutActivity extends BaseActivity {

    private TextView mTvVersion;

    @Override protected int loadView() {
        return R.layout.activity_about;
    }

    @Override protected void initView() {
        mTvVersion = (TextView) findViewById(R.id.tv_version);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mToolbar != null && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mTvVersion.setText(getResources().getString(R.string.app_version_code)+BuildConfig.VERSION_NAME);
    }


}
