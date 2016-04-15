package me.wavever.ganklock.view;

import java.util.List;
import me.wavever.ganklock.model.bean.Gank;

/**
 * Created by WAVE on 2016/3/4.
 */
public interface IBaseView {

    /**
     * 填充干货数据
     * @param list
     * @param girlUrl
     */
    void fillData(List<Gank> list,String girlUrl);



}
