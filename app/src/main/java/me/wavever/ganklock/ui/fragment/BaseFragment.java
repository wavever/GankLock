package me.wavever.ganklock.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;

import me.wavever.ganklock.presenter.BasePresenter;
import me.wavever.ganklock.presenter.IPresenter;

/**
 * Created by wavever on 2016/8/12.
 */
public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment implements IPresenter<P>{
    protected P mPresenter;

    @Override
    public P createPresenter() {
        return null;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
