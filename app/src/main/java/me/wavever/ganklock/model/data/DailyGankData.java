package me.wavever.ganklock.model.data;

import java.util.List;

import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.model.bean.GankCategory;
import me.wavever.ganklock.model.bean.GankData;
import me.wavever.ganklock.model.http.GankService;
import me.wavever.ganklock.model.http.RetrofitUtil;
import me.wavever.ganklock.util.DateUtil;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wavever on 2016/9/1.
 */
public class DailyGankData {

    private List<Gank> mList;
    private GankService service = RetrofitUtil.getSingleton();
    private OnGetDailyGankDataListener onGetDailyGankDataListener;

    public DailyGankData(OnGetDailyGankDataListener listener){
        onGetDailyGankDataListener = listener;
    }

    public void getDailyGankData(final String date) {
        service.getGankData(date)
                .map(new Func1<GankData, GankData.Result>() {
                    @Override
                    public GankData.Result call(GankData gankData) {
                        return gankData.results;
                    }
                })
                .map(new Func1<GankData.Result, List<Gank>>() {
                    @Override
                    public List<Gank> call(GankData.Result result) {
                        return addAllResult(result);
                    }
                })
                .subscribeOn(Schedulers.io())   //在io线程进行数据的读取  放在任何地方都可以
                .observeOn(AndroidSchedulers.mainThread())  //在主线程处理数据  指定的是它之后的操作的线程，因此如果需要多次切换线程，可指定多次
                .subscribe(new Subscriber<List<Gank>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        onGetDailyGankDataListener.onGetFailure();
                    }

                    @Override
                    public void onNext(List<Gank> ganks) {
                        onGetDailyGankDataListener.onGetSuccess(ganks);
                    }
                });
    }

    private List<Gank> addAllResult(GankData.Result results) {
        if (!mList.isEmpty()) mList.clear();
        if (results.androidList != null) mList.addAll(0, results.androidList);
        if (results.iosList != null) mList.addAll(results.iosList);
        if (results.htmlList != null) mList.addAll(results.htmlList);
        if (results.appList != null) mList.addAll(results.appList);
        if (results.recommendList != null) mList.addAll(results.recommendList);
        if (results.restVideoList != null) mList.addAll(results.restVideoList);
        if (results.girlList != null) mList.addAll(results.girlList);
        return mList;
    }

    public interface OnGetDailyGankDataListener{
        void onGetSuccess(List<Gank> ganks);
        void onGetFailure();
    }
}
