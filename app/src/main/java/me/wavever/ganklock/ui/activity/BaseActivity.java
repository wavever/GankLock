package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import me.wavever.ganklock.presenter.BasePresenter;
import me.wavever.ganklock.util.DateUtil;

/**
 * Created by WAVE on 2015/12/26.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;

    protected final String today = DateUtil.getTodayFormatDate();

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initPresenter();
    }

}
