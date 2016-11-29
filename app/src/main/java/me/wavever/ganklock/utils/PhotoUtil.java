package me.wavever.ganklock.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wavever on 2016/9/27.
 */

public class PhotoUtil {

    public static final String KEY_PHOTO_URL = "key_photo_url";

    /**
     * @param name 图片的_id
     */
    public static void savePhotoByBitmap(final Context context, final Bitmap bitmap, final String name) {
        final String fileName = name + ".jpg";
        final File fileDir = new File(Environment.getExternalStorageDirectory(),
            "GankLock");//GankLock/Images
        if (!fileDir.exists()) {
            fileDir.mkdir();
        } else if (fileDir.listFiles().length != 0) {
            for (File file : fileDir.listFiles()) {
                if (file.getName().equals(fileName)) {
                    Toast.makeText(context, "图片已经下载了", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        Observable.create(new OnSubscribe<String>() {
            @Override public void call(Subscriber<? super String> subscriber) {
                String photoUrl = createPhoto(context, fileDir, fileName, bitmap);
                subscriber.onNext("图片已保存至" + photoUrl);
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<String>() {
                @Override public void call(String s) {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                }
            });
    }


    public static void sharePhoto(Context context,File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
        intent.setType("image/*");
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }

    public static void setWallPaper(Context context,File file){
        WallpaperManager manager = WallpaperManager.getInstance(context);
        try {
            manager.setBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String createPhoto(Context context, File fileDir, String fileName, Bitmap bitmap) {
        File file = new File(fileDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*//把文件插入系统图库
        try {
            Media.insertImage(context.getContentResolver(),
                file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
            Uri.parse("file://" + file.getAbsolutePath())));*/
        return file.getAbsolutePath();
    }

    /**
     * 从File中加载缩放过的图片
     *
     * @param path
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {
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
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
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
