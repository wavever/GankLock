package me.wavever.ganklock.presenter;

import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import me.wavever.ganklock.view.IMeiZhiView;
import io.reactivex.Observable;

/**
 * Created by wavever on 2016/9/2.
 */
public class MeiZhiPresenter extends BasePresenter<IMeiZhiView> {

    private static final String TAG = "MeiZhiPresenter-->";

    private List<File> mList = new ArrayList<>();

    public void loadMeizhi() {
        File fileDir = new File(Environment.getExternalStorageDirectory(), "GankLock");
        if (!fileDir.exists()||fileDir.listFiles().length==0) {
            getView().showEmptyView();
            return;
        }
        if(!mList.isEmpty()){
            mList.clear();
        }
        Observable.fromArray(fileDir.listFiles())
                .filter(new Predicate<File>() {
                    @Override
                    public boolean test(File file) {
                        return file.getName().endsWith(".jpg");
                    }
                })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<File>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(File file) {
                    mList.add(file);
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onComplete() {
                    if (getView() != null) {
                        getView().showMeizhi(mList);
                    }
                }
            });
    }


}
