package me.wavever.ganklock.presenter;


import java.util.List;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.model.data.ContentGankData;
import me.wavever.ganklock.utils.LogUtil;
import me.wavever.ganklock.view.IGankContentView;
import rx.Subscriber;

/**
 * Created by wavever on 2016/3/4.
 */
public class GankContentPresenter extends BasePresenter<IGankContentView>{

    private static String TAG = GankContentPresenter.class.getSimpleName()+"-->";
    private ContentGankData contentGankData = new ContentGankData();

    public void loadContentData(String date){
        Subscriber<List<Gank>> subscriber = new Subscriber<List<Gank>>() {
            @Override public void onCompleted() {
            }

            @Override public void onError(Throwable e) {
                LogUtil.d(TAG+e.getMessage());
                getView().showError();
            }


            @Override public void onNext(List<Gank> list) {
                getView().showData(list);
            }
        };
        contentGankData.getDailyGankDataFromServer(date,subscriber);
    }




}
