package me.wavever.ganklock.ui;

import java.util.List;
import me.wavever.ganklock.model.Gank;

/**
 * Created by WAVE on 2016/3/12.
 */
public interface OnMainGetGankListener {
    void onGank(List<Gank> gankList,String girl);
}
