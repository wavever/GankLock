package me.wavever.ganklock.presenter;


import java.util.ArrayList;
import java.util.List;

import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.model.GankDataImpl;
import me.wavever.ganklock.model.IGankModel;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.view.IGankContentView;

/**
 * Created by wavever on 2016/3/4.
 */
public class GankContentPresenter extends BasePresenter<IGankContentView>{

    private static final String TAG = GankContentPresenter.class.getSimpleName();

    private String date;
    private String girlUrl;

    private IGankModel gankModel;


    private List<Gank> mList;
    private IGankContentView gankContentView;


    public GankContentPresenter() {
        gankModel = new GankDataImpl();
        mList = new ArrayList<>();
        gankContentView = getView();
    }


    public String getGirlUrl() {
        return girlUrl;
    }


    public void loadFromDB() {
        gankContentView.completeGetData(MyApplication.getSp().getString(Config
                .LAST_GET_DATE, ""));
        gankContentView.fillData(gankModel.loadFromDB(),
                MyApplication.getSp().getString("girl", "null"));
    }

}
