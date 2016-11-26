package me.wavever.ganklock.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by wavever on 2016/8/12.
 */
public abstract class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public BasePresenter(){}

    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    public V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef.get() != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    //在Presenter中实现Activity的生命周期
    public void onActivityLifeCreate() {}

    public void onActivityLifeStart() {}

    public void onActivityLifeResume() {}

    public void onActivityLifePause() {}

    public void onActivityLifeStop() {}

    public void onActivityLifeDestory() {}


    //在Presenter中实现Fragment的生命周期

    public void onFragmentLifeAttach(){}//Fragment与Activity关联时调用

    public void onFragmentLifeCreate(){}

    public void onFragmentLifeCreateView(){}//Fragment加载布局时调用

    public void onFragmentLifeActivityCreated(){}

    public void onFragmentLifeStart(){}

    public void onFragmentLifeResume(){}

    public void onFragmentLifePause(){}

    public void onFragmentLifeStop(){}

    public void onFragmentLifeDestroyView(){}//Fragment中的布局被移除时调用

    public void onFragmentLifeDestory(){}

    public void onFragmentLifeDetach(){}//Fragment与Activity解除关联时调用
}

