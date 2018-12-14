package me.wavever.ganklock.presenter;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.model.data.ContentGankData;
import me.wavever.ganklock.view.IGankContentView;

/**
 * Created by wavever on 2016/3/4.
 */
public class GankContentPresenter extends BasePresenter<IGankContentView> {

    private static final String TAG = "GankContentPresenter-->";
    private final ContentGankData contentGankData = new ContentGankData();

    public void loadContentData(String date) {

        Observer observer = new Observer<List<Gank>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Gank> list) {
                getView().showData(list);
            }

            @Override
            public void onError(Throwable e) {
                getView().showError();
            }

            @Override
            public void onComplete() {

            }
        };
        contentGankData.getDailyGankDataFromServer(date, observer);
    }


}
