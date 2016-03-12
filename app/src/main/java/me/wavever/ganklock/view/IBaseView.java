package me.wavever.ganklock.view;

import java.util.List;
import me.wavever.ganklock.model.Gank;

/**
 * Created by WAVE on 2016/3/4.
 */
public interface IBaseView {

    //获取数据
    void fillData(List<Gank> list,String girlUrl);

    //是否获取到今日数据
    boolean isGetTodayData();


}
