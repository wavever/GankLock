package me.wavever.ganklock.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    private static final String TAG = MeiZhiPresenter.class.getSimpleName()+"-->";

    private List<Bitmap> mList = new ArrayList<>();

    public void loadMeizhi() {
        File fileDir = new File(Environment.getExternalStorageDirectory(), "GankLock");
        if (!fileDir.exists()||fileDir.listFiles().length==0) {
            getView().showEmptyView();
            return;
        }
        Observable.from(fileDir.listFiles())
            .filter(new Func1<File, Boolean>() {
                @Override public Boolean call(File file) {
                    return file.getName().endsWith(".jpg");
                }
            })
            .map(new Func1<File, Bitmap>() {
                @Override public Bitmap call(File file) {
                    return decodeSampledBitmapFromFile(file.getAbsolutePath(),90,170);
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Bitmap>() {
                @Override public void onCompleted() {
                    getView().showMeizhi(mList);
                }

                @Override public void onError(Throwable e) {
                    LogUtil.d(TAG+e.getMessage());
                    getView().showErrorView();
                }

                @Override public void onNext(Bitmap bitmap) {
                    mList.add(bitmap);
                }
            });
    }

    /**
     * 从File中加载缩放过的图片
     *
     * @param path
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;  //BitmapFactory只会解析图片的原始宽高，不会真正的加载图片
        BitmapFactory.decodeFile(path,options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 计算合适的采样率inSampleSize
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if(reqWidth == 0 || reqHeight == 0){
            return 1;
        }
        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;
            while (halfWidth / inSampleSize >= reqWidth && halfHeight / inSampleSize >= reqHeight) {
                inSampleSize *= 2;          //inSampleSize的值为2的指数
            }
        }

        return inSampleSize;
    }


}
