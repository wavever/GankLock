package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import me.wavever.ganklock.R;

/**
 * Created by WAVE on 2016/3/12.
 */
public class AboutActivity extends BaseActivity{


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("关于");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_about ;
    }

    @Override protected void initPresenter() {

    }
}
