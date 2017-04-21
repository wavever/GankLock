package me.wavever.ganklock.presenter;

import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.wavever.ganklock.utils.LogUtil;
import me.wavever.ganklock.view.IMeiZhiView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
        Observable.from(fileDir.listFiles())
            .filter(new Func1<File, Boolean>() {
                @Override public Boolean call(File file) {
                    return file.getName().endsWith(".jpg");
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<File>() {
                @Override public void onCompleted() {
                    getView().showMeizhi(mList);
                }

                @Override public void onError(Throwable e) {
                    LogUtil.d(TAG+e.getMessage());
                    getView().showErrorView();
                }

                @Override public void onNext(File file) {
                    mList.add(file);
                }
            });
    }


}
