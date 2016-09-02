package me.wavever.ganklock.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.wavever.ganklock.presenter.BasePresenter;
import me.wavever.ganklock.presenter.IPresenter;

/**
 * Created by wavever on 2016/8/12.
 */
public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment implements IPresenter<P> {
    protected P mPresenter= null;

    protected abstract int loadView();

    @Override
    public P getPresenter() {
        return mPresenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
        mPresenter.onFragmentLifeCreateView();
        return inflater.inflate(loadView(), container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mPresenter!=null){
            mPresenter.onFragmentLifeCreate();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mPresenter!=null){
            mPresenter.onFragmentLifeStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter!=null){
            mPresenter.onFragmentLifeResume();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mPresenter!=null){
            mPresenter.onFragmentLifeActivityCreated();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mPresenter!=null){
            mPresenter.onFragmentLifePause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mPresenter!=null){
            mPresenter.onFragmentLifeStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.onFragmentLifeDestory();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
