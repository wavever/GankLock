package me.wavever.ganklock.view;

import java.util.List;
import me.wavever.ganklock.model.bean.Gank;

/**
 * Created by wavever on 2016/2/22.
 */
public interface IGankContentView extends IBaseView {

    void loadData(String date);
    void showData(List<Gank> list);
    void showError();
}
