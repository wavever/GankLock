package me.wavever.ganklock.view;


import java.util.List;
import me.wavever.ganklock.model.bean.GankDaily;

/**
 * Created by wavever on 2016/8/14.
 */
public interface IDailyGankView extends IBaseView{
    void showLoading();
    void loadDailyData();
    void loadFailure();
    void loadTodayDailyData();
    void showDailyData(List<GankDaily> ganks);
}
