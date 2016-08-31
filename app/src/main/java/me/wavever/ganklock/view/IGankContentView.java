package me.wavever.ganklock.view;

import java.util.List;

import me.wavever.ganklock.model.bean.Gank;

/**
 * Created by WAVE on 2016/2/22.
 */
public interface IGankContentView extends IBaseView {

    void showErrorSnack(String msg);

    void completeGetData(String date);

    void showLoading();

    void dismissLoading();

    /**
     * 填充干货数据
     * @param list
     * @param girlUrl
     */
    void fillData(List<Gank> list,String girlUrl);

}
