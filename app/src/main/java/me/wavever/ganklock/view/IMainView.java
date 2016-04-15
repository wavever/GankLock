package me.wavever.ganklock.view;

/**
 * Created by WAVE on 2016/2/22.
 */
public interface IMainView<Gank> extends IBaseView {

    void showErrorSnack(String msg);

    void completeGetData(String date);

    void showLoading();

    void dismissLoading();

    /**
     * 是否获取到今日数据
     * @return
     */
    boolean isGetTodayData();
}
