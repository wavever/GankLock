package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.BasePresenter;
import me.wavever.ganklock.presenter.IPresenter;
import me.wavever.ganklock.util.DateUtil;

/**
 * Created by wavever on 2015/12/26.
 */
public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity implements IPresenter<P> {

    protected P mPresenter;

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
    }

    protected void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    public P getPresenter() {
        if (mPresenter != null) {
            return mPresenter;
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mPresenter!=null){

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
