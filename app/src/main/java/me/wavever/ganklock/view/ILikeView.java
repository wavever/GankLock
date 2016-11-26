package me.wavever.ganklock.view;

import java.util.List;
import me.wavever.ganklock.model.bean.Gank;

/**
 * Created by wavever on 2016/9/2.
 */
public interface ILikeView extends IBaseView{
    void showEmptyTip();
    void showLikeData(List<Gank> list);
    void refreshLikeData(List<Gank> list);
}
