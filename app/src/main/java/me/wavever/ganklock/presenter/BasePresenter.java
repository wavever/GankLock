package me.wavever.ganklock.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by wavever on 2016/8/12.
 */
public abstract class BasePresenter<V> {

    protected Reference<V> mViewRef;

    protected void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    protected boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    protected void detachView() {
        if (mViewRef.get() != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    //在Presenter中实现Activity的生命周期
    protected void onActivityLifeCreate() {}

    protected void onActivityLifeStart() {}

    protected void onActivityLifeResume() {}

    protected void onActivityLifePause() {}

    protected void onActivityLifeStop() {}

    protected void onActivityLifeDestory() {}
}

