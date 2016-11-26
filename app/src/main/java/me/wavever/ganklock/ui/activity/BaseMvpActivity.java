package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import me.wavever.ganklock.presenter.BasePresenter;
import me.wavever.ganklock.presenter.IPresenter;

/**
 * Created by wavever on 2015/12/26.
 */
public abstract class BaseMvpActivity<V, P extends BasePresenter<V>> extends BaseActivity implements IPresenter<P> {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
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
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onActivityLifeStart();
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
        if (mPresenter != null) {
            mPresenter.onActivityLifeDestory();
            mPresenter.detachView();
        }
    }
}
