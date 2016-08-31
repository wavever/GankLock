package me.wavever.ganklock.model;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.model.bean.Today;
import me.wavever.ganklock.util.LogUtil;
import me.wavever.ganklock.util.SPUtil;

/**
 * Created by wavever on 2016/3/26.
 */
public class GankDataImpl implements IGankModel {

    private static final String TAG = GankDataImpl.class.getSimpleName();
    private SPUtil sp = MyApplication.getSp();

    @Override
    public void getGankData(String today) {

    }


    /**
     * 是否获取到今天的干货
     *
     * @return true/false
     */
    @Override
    public boolean isGetToday() {
        return sp.getBoolean(Config.GET_TODAY_DATA, false);
    }


    /**
     * 将数据保存到数据库
     *
     * @param date  干货日期
     * @param ganks 干货数据
     */
    @Override
    public void saveToDB(final String date, final List<Gank> ganks) {
        if (!date.equals(Config.LAST_GET_DATE)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Delete().from(Gank.class).execute();
                    Today t = new Today();
                    t.todayDate = date;
                    t.save();
                    ActiveAndroid.beginTransaction();
                    try {
                        for (int i = 0; i < ganks.size(); i++) {
                            ganks.get(i).save();
                        }
                        ActiveAndroid.setTransactionSuccessful();
                        LogUtil.d(TAG + "数据保存成功");
                    } finally {
                        ActiveAndroid.endTransaction();
                        LogUtil.d(TAG + "数据保存结束");
                    }
                }
            }).start();
        }
    }


    /**
     * 从数据库中加载
     *
     * @return 查询到的数据
     */
    @Override
    public List<Gank> loadFromDB() {
        List<Gank> ganks = new Select().from(Gank.class).execute();
        LogUtil.d(TAG + "从数据库获取数据成功");
        return ganks;
    }
}
