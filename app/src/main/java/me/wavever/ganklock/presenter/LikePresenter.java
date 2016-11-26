package me.wavever.ganklock.presenter;

import com.activeandroid.query.Select;
import java.util.List;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.utils.LogUtil;
import me.wavever.ganklock.view.ILikeView;

/**
 * Created by wavever on 2016/9/2.
 */
public class LikePresenter extends BasePresenter<ILikeView>{

    private static final String TAG = LikePresenter.class.getSimpleName()+"-->";

    public void getLikeList(){
        List<Gank> list = new Select().from(Gank.class).execute();
        for(Gank gank:list){
            LogUtil.d(TAG+"收藏"+gank.getDesc());
        }
        if(list == null || list.isEmpty()){
            getView().showEmptyTip();
        }else {
            getView().showLikeData(list);
        }
    }

}
