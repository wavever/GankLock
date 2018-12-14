package me.wavever.ganklock.model.data;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.model.bean.GankContent;
import me.wavever.ganklock.model.http.GankService;
import me.wavever.ganklock.model.http.RetrofitUtil;

/**
 * Created by wavever on 2016/9/20.
 */

public class ContentGankData {

    private GankService service = RetrofitUtil.getSingleton();
    private List<Gank> mList = new ArrayList<>();

    public void getDailyGankDataFromServer(String date, Observer observer) {

        service.getGankData(date)
                .map(new Function<GankContent, GankContent.Result>() {
                    @Override
                    public GankContent.Result apply(GankContent gankContent) throws Exception {
                        return gankContent.results;
                    }
                }).map(new Function<GankContent.Result, List<Gank>>() {
            @Override
            public List<Gank> apply(GankContent.Result result) throws Exception {
                return addAllResult(result);
            }
        }).subscribeOn(Schedulers.io())   //在io线程进行数据的读取  放在任何地方都可以
                .observeOn(AndroidSchedulers.mainThread())  //在主线程处理数据  指定的是它之后的操作的线程，因此如果需要多次切换线程，可指定多次
                .subscribe(observer);
        /*service.getGankData(date).flatMap(new Func1<GankContent, Observable<Gank>>() {
            @Override public Observable<Gank> call(GankContent gankContent) {
                return Observable.from(gankContent.results);
            }
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);*/
    }


    private List<Gank> addAllResult(GankContent.Result results) {
        if (!mList.isEmpty()) mList.clear();
        if (results.androidList != null) mList.addAll(0, results.androidList);
        if (results.iosList != null) mList.addAll(results.iosList);
        if (results.htmlList != null) mList.addAll(results.htmlList);
        if (results.appList != null) mList.addAll(results.appList);
        if (results.recommendList != null) mList.addAll(results.recommendList);
        if(results.extendRespurseList != null) mList.addAll(results.extendRespurseList);
        if (results.restVideoList != null) mList.addAll(results.restVideoList);
        return mList;
    }
}
