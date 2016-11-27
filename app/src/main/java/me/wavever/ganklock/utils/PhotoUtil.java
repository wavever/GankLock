package me.wavever.ganklock.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wavever on 2016/9/27.
 */

public class PhotoUtil {

    public static final String KEY_PHOTO_URL = "key_photo_url";
    private static Bundle bundle = null;

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
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override public void call(Subscriber<? super String> subscriber) {
                String photoUrl = createPhoto(context, fileDir, fileName, bitmap);
                bundle = new Bundle();
                bundle.putString(KEY_PHOTO_URL,photoUrl);
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


    public static void sharePhoto(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, "");
        intent.setType("image/*");
        context.startActivity(Intent.createChooser(intent, "分享到"));
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
        //把文件插入系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
            Uri.parse("file://" + file.getAbsolutePath())));
       /* subscriber.onNext(
            file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4));*/
        return file.getAbsolutePath();
    }

}
