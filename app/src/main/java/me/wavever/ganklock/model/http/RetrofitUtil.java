package me.wavever.ganklock.model.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.wavever.ganklock.config.GankApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wavever on 2016/3/26.
 */
public class RetrofitUtil {

    private static GankService sService;

    static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss" + ".SSS'Z'")
            .serializeNulls() //导出null值
            .create();

    public static GankService getSingleton() {
        if (sService == null) {
            synchronized (RetrofitUtil.class) {
                if (sService == null) {
                    String api = GankApi.BASE_URL;
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(api)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(new OkHttpClient())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    sService = retrofit.create(GankService.class);
                }
            }
        }

        return sService;
    }

}
