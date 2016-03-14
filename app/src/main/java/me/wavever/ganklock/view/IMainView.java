package me.wavever.ganklock.view;

/**
 * Created by WAVE on 2016/2/22.
 */
public interface IMainView<Gank> extends IBaseView {

    boolean isGetData();

    void showErrorSnack(String msg);

    void showUpdate();

    void completeGetData();

    boolean checkIsOpenLock();

    void getLastData(String lastDate);

    void saveToDB();

    void loadFromDB();

}
