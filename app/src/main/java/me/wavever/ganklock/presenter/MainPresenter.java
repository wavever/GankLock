package me.wavever.ganklock.presenter;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.R;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.model.GankDataImpl;
import me.wavever.ganklock.model.IGankModel;
import me.wavever.ganklock.model.bean.GankData;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.model.bean.GankCategory;
import me.wavever.ganklock.model.http.RetrofitUtil;
import me.wavever.ganklock.model.http.GankService;
import me.wavever.ganklock.util.DateUtil;
import me.wavever.ganklock.util.DialogUtil;
import me.wavever.ganklock.view.IMainView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by WAVE on 2016/3/4.
 */
public class MainPresenter extends BasePresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private String date;
    private String girlUrl;

    private boolean isGetTodayGank;

    private IMainView mainView;
    private IGankModel gankModel;
    private Context mContext;

    private GankService service = RetrofitUtil.getSingleton();
    private List<Gank> mList;


    public MainPresenter(Context context, IMainView mainView) {
        mContext = context;
        this.mainView = mainView;
        gankModel = new GankDataImpl();
        mList = new ArrayList<>();
    }

    public void getData(final String date) {
        this.date = date;
        service.getGankData(date)
               .map(new Func1<GankData, GankData.Result>() {
                   @Override public GankData.Result call(GankData gankData) {
                       return gankData.results;
                   }
               })
               .map(new Func1<GankData.Result, List<Gank>>() {
                   @Override public List<Gank> call(GankData.Result result) {
                       return addAllResult(result);
                   }
               })
               .subscribeOn(Schedulers.io())   //在io线程进行数据的读取
               .observeOn(AndroidSchedulers.mainThread())  //在主线程处理数据
               .subscribe(new Subscriber<List<Gank>>() {
                   @Override public void onCompleted() {
                       mainView.completeGetData(date);
                   }

                   @Override public void onError(Throwable e) {
                       mainView.showErrorSnack("出了一个小问题~");
                   }


                   @Override public void onNext(List<Gank> ganks) {
                       if (!ganks.isEmpty()) {
                           for (Gank gank : ganks) {
                               if (gank.getType()
                                       .equals(GankCategory.福利.name())) {
                                   girlUrl = gank.getUrl();
                               }
                           }
                           mainView.fillData(ganks, girlUrl);
                           gankModel.saveToDB(date, ganks);
                           MyApplication.getSp()
                                        .putString(Config.LAST_GET_DATE, date);
                       }
                       else {
                           mainView.showErrorSnack("今天的干货还没有更新呢，先看看上次的吧(∩_∩)");
                          /* DialogUtil.showSingleDialog(mContext,
                                   R.string.tips_load_today_empty);*/
                           String lastDate = MyApplication.getSp()
                                                          .getString(
                                                                  Config.LAST_GET_DATE,
                                                                  DateUtil.getLastGankDate());
                           getData(lastDate);
                       }
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

    public String getGirlUrl() {
        return girlUrl;
    }

    public boolean isGetData() {
        return isGetTodayGank;
    }

    public void loadFromDB(){
        mainView.completeGetData( MyApplication.getSp().getString(Config
                .LAST_GET_DATE,""));
        mainView.fillData(gankModel.loadFromDB(),
                MyApplication.getSp().getString("girl","null"));
    }
}
