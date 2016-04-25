package me.wavever.ganklock.model;

import java.util.List;
import me.wavever.ganklock.model.bean.Gank;

/**
 * Created by wavever on 2016/3/26.
 */
public interface IGankModel {
    void getGankData(String today);
    boolean isGetToday();
    void saveToDB(String date, List<Gank> ganks);
    List<Gank> loadFromDB();
}
