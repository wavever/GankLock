package me.wavever.ganklock.model.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;
import me.wavever.ganklock.config.GankApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wavever on 2016/3/26.
 */
public class RetrofitUtil {

    private static GankService sService;
    private static Gson sGson;
    private static OkHttpClient sOkHttpClient;

    static {
        sGson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss" + ".SSS'Z'")
            .setDateFormat("yyyy/MM/dd")
            .serializeNulls() //导出null值
            .create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        sOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();
    }


    public static GankService getSingleton() {

        if (sService == null) {
            synchronized (RetrofitUtil.class) {
                if (sService == null) {
                    String api = GankApi.BASE_URL;
                    Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(api)
                        .client(sOkHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(sGson))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                    sService = retrofit.create(GankService.class);
                }
            }
        }

        return sService;
    }

}
