package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.BasePresenter;
import me.wavever.ganklock.util.DateUtil;

/**
 * Created by WAVE on 2015/12/26.
 */
public abstract class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity {

    protected Toolbar mToolbar;

    protected final String today = DateUtil.getTodayFormatDate();


    protected abstract int getLayoutId();

    protected abstract void initPresenter();


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initPresenter();
        initToolbar();
    }


    protected void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    long time = 0;

    @Override public void onBackPressed() {
            super.onBackPressed();
    }
}
