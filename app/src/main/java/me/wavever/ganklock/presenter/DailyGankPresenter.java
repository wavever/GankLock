package me.wavever.ganklock.presenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.wavever.ganklock.model.bean.GankDaily;
import me.wavever.ganklock.model.data.DailyGankData;
import me.wavever.ganklock.utils.PreferenceUtil;
import me.wavever.ganklock.utils.SystemUtil;
import me.wavever.ganklock.view.IDailyGankView;

/**
 * Created by wavever on 2016/8/14.
 */
public class DailyGankPresenter extends BasePresenter<IDailyGankView> {

    private static String TAG = DailyGankPresenter.class.getSimpleName() + "-->";

    private static final int COUNT = 5;//每页加载的数据个数

    private DailyGankData dailyGankData = new DailyGankData();
    private List<GankDaily> mList = new ArrayList<>();

    public void loadDailyData(int page) {
        if (!SystemUtil.isNetworkAvailable()) {
            Observer<List<GankDaily>> observer = new Observer<List<GankDaily>>() {

                @Override public void onError(Throwable e) {
                    getView().loadFailure();
                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override public void onNext(List<GankDaily> gankDailies) {
                    getView().showDailyData(gankDailies);
                }
            };
            dailyGankData.getDailyDataFromDB(observer);

        } else {
            Observer<GankDaily> observer = new Observer<GankDaily>() {

                @Override public void onError(Throwable e) {
                    getView().loadFailure();
                }

                @Override
                public void onComplete() {
                    getView().showDailyData(mList);
                    PreferenceUtil.putString("url", mList.get(0).content);
                    dailyGankData.saveDailyDataToDB(mList);
                }

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override public void onNext(GankDaily gankDaily) {
                    gankDaily.content = convertContent2Url(gankDaily.content);
                    mList.add(gankDaily);
                }
            };
            dailyGankData.getDailyDataFromServer(observer, COUNT, page);
        }

    }


    private String convertContent2Url(String content) {
        int start = content.indexOf("src=") + 5;
        int end = content.indexOf("jpg") + 3;
        return content.substring(start, end);
    }

}
