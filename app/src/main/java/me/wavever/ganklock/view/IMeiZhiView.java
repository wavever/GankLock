package me.wavever.ganklock.view;

import java.io.File;
import java.util.List;

/**
 * Created by wavever on 2016/9/2.
 */
public interface IMeiZhiView extends IBaseView{

    void showMeizhi(List<File> list);
    void showEmptyView();
    void showErrorView();
}
