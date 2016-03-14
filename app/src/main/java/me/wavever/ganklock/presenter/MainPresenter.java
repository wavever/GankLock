package me.wavever.ganklock.presenter;

import android.content.Context;
import android.widget.Toast;
import com.activeandroid.ActiveAndroid;
import java.util.ArrayList;
import java.util.List;
import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.data.GankData;
import me.wavever.ganklock.model.Gank;
import me.wavever.ganklock.model.GankCategory;
import me.wavever.ganklock.model.Today;
import me.wavever.ganklock.retrofit.GankService;
import me.wavever.ganklock.retrofit.WaveverFactory;
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

    private boolean isGetData;

    private IMainView mainView;
    private Context mContext;

    private GankService service = WaveverFactory.getSingle();
    private List<Gank> mList;


    public MainPresenter(Context context, IMainView mainView) {
        mContext = context;
        this.mainView = mainView;
        mList = new ArrayList<>();
    }


    public void getData(final String date) {
        this.date = date;
        service.getGankData(date)
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
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
               .subscribe(new Subscriber<List<Gank>>() {
                   @Override public void onCompleted() {
                       mainView.completeGetData();
                       isGetData = true;
                   }


                   @Override public void onError(Throwable e) {
                       isGetData = false;
                       DialogUtil.showSingleDialog(mContext,
                               "今天的干货还没有更新呢!\n先看看上次的吧~(∩_∩)~");
                       String lastDate = MyApplication.getSp()
                                                      .getString(
                                                              Config.LAST_GET_DATE,
                                                              "2016/03/11");
                       mainView.getLastData(lastDate);
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
                           MyApplication.getSp()
                                        .putString(Config.LAST_GET_DATE, date);
                       }
                       else {
                           Toast.makeText(MyApplication.getContext(), "咦~没有数据",
                                   Toast.LENGTH_SHORT).show();
                       }
                   }
               });
    }


    private boolean saveToDB(String date, List<Gank> ganks) {
        Today t = new Today();
        t.todayDate = date;
        t.save();

        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0;i<ganks.size();i++){
                Gank gank = new Gank();

            }
        }finally {

        }
        return false;
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

    private void saveToDataBase(GankData.Result result){
        if(result.girlList!=null){
        }
    }


    public String getGirlUrl() {
        return girlUrl;
    }


    public boolean isGetData() {
        return isGetData;
    }
}
