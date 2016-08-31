package me.wavever.ganklock.presenter;

/**
 * Created by wavever on 2016/8/30.
 */
public interface IPresenter<P> {
    P createPresenter();

    P getPresenter();
}
