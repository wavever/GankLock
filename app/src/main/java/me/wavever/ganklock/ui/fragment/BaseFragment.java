package me.wavever.ganklock.ui.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.wavever.ganklock.presenter.BasePresenter;
import me.wavever.ganklock.presenter.IPresenter;
import rx.Subscription;

/**
 * Created by wavever on 2016/8/12.
 */
public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment implements IPresenter<P> {

    protected P mPresenter = null;
    protected Activity mContext = null;
    protected Subscription mSubscrition = null;

    /**
     * 加载xml
     * @return xml的id
     */
    protected abstract int loadView();

    /**
     * 初始化View
     */
    protected abstract void initView();

    @Override
    public P getPresenter() {
        return mPresenter;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
            mPresenter.onFragmentLifeCreate();
        }
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = createPresenter();
            if(mPresenter!=null){
                mPresenter.attachView((V) this);
            }
        }
        mPresenter.onFragmentLifeCreateView();
        return inflater.inflate(loadView(), container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.onFragmentLifeActivityCreated();
        }
        initView();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onFragmentLifeStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onFragmentLifeResume();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onFragmentLifePause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onFragmentLifeStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onFragmentLifeDestory();
        }
        if(mSubscrition!=null&&mSubscrition.isUnsubscribed()){
            mSubscrition.unsubscribe();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
