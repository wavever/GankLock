package me.wavever.ganklock.model.data;

import com.activeandroid.query.Select;
import java.util.List;
import me.wavever.ganklock.model.bean.GankDaily;
import me.wavever.ganklock.model.bean.GankDailyContent;
import me.wavever.ganklock.model.http.GankService;
import me.wavever.ganklock.model.http.RetrofitUtil;
import me.wavever.ganklock.utils.LogUtil;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wavever on 2016/9/1.
 */
public class DailyGankData {

    private static String TAG = DailyGankData.class.getSimpleName() + "-->";

    private GankService service = RetrofitUtil.getSingleton();

    private List<GankDaily> mList;


    public List<GankDaily> getDailyDataFromDB(Observer<List<GankDaily>> observer) {
        Observable.create(new Observable.OnSubscribe<List<GankDaily>>() {
            @Override public void call(Subscriber<? super List<GankDaily>> subscriber) {
                List<GankDaily> list = new Select().from(GankDaily.class)
                    .orderBy("publishedAt DESC")//DESC ASC
                    .execute();
                subscriber.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer);
        return mList;
    }


    public void getDailyDataFromServer(Observer<GankDaily> observer, int count, int pager) {
        service.getDailyData(count, pager)
            .flatMap(new Func1<GankDailyContent, Observable<GankDaily>>() {
                @Override public Observable<GankDaily> call(GankDailyContent gankDailyContent) {
                    return Observable.from(gankDailyContent.results);
                }
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer);
    }


    public void saveDailyDataToDB(List<GankDaily> datas) {
        Observable.from(datas)
            .doOnNext(new Action1<GankDaily>() {
                @Override public void call(GankDaily gankDaily) {
                    if (!new Select().from(GankDaily.class)
                        .where("_id=?", gankDaily._id)
                        .exists()) {
                        gankDaily.save();
                        LogUtil.d(TAG + "保存成功" + gankDaily.publishedAt);
                    }
                    LogUtil.d(TAG + "已经存在" + gankDaily.publishedAt);
                }
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<GankDaily>() {
                @Override public void call(GankDaily gankDaily) {

                }
            }, new Action1<Throwable>() {
                @Override public void call(Throwable throwable) {
                    LogUtil.d(TAG + "保存失败" + throwable.getMessage());
                }
            });
    }

}
