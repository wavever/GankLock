package me.wavever.ganklock.model.data;

import com.activeandroid.query.Select;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.wavever.ganklock.model.bean.GankDaily;
import me.wavever.ganklock.model.bean.GankDailyContent;
import me.wavever.ganklock.model.http.GankService;
import me.wavever.ganklock.model.http.RetrofitUtil;
import me.wavever.ganklock.utils.LogUtil;

/**
 * Created by wavever on 2016/9/1.
 */
public class DailyGankData {

    private static String TAG = DailyGankData.class.getSimpleName() + "-->";

    private GankService service = RetrofitUtil.getSingleton();

    private List<GankDaily> mList;

    public List<GankDaily> getDailyDataFromDB(Observer<List<GankDaily>> observer) {
        Observable.create(new ObservableOnSubscribe<List<GankDaily>>() {
            @Override
            public void subscribe(ObservableEmitter<List<GankDaily>> emitter) throws Exception {
                List<GankDaily> list = new Select().from(GankDaily.class)
                        .orderBy("publishedAt DESC")//DESC ASC
                        .execute();
                emitter.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
            .subscribe(observer);
        return mList;
    }

    public void getDailyDataFromServer(Observer<GankDaily> observer, int count, int pager) {
        service.getDailyData(count, pager)
            .flatMap(new Function<GankDailyContent, ObservableSource<GankDaily>>() {
                @Override
                public ObservableSource<GankDaily> apply(GankDailyContent gankDailyContent) throws Exception {
                    return Observable.fromIterable(gankDailyContent.results);
                }
            }).subscribeOn(Schedulers.io())
            .subscribe(observer);
    }

    public void saveDailyDataToDB(List<GankDaily> datas) {
        Observable.fromIterable(datas)
                .doOnNext(new Consumer<GankDaily>() {
                    @Override
                    public void accept(GankDaily gankDaily) throws Exception {
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
            .subscribe(new Observer<GankDaily>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(GankDaily gankDaily) {

                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.d(TAG + "保存失败" + e);
                }

                @Override
                public void onComplete() {

                }
            });
    }

}
