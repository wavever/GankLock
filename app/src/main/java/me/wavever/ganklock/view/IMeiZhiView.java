package me.wavever.ganklock.view;

import android.graphics.Bitmap;
import java.util.List;

/**
 * Created by wavever on 2016/9/2.
 */
public interface IMeiZhiView extends IBaseView{

    void showMeizhi(List<Bitmap> list);
    void showEmptyView();
    void showErrorView();
}
