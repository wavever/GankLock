package me.wavever.ganklock.model.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import me.wavever.ganklock.config.GankApi;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by wavever on 2016/3/26.
 */
public class RetrofitUtil {

    private static  GankService sService;
    private static String api =GankApi.BASE_URL;

    static Gson gson = new GsonBuilder().setDateFormat
            ("yyyy-MM-dd'T'HH:mm:ss" +
            ".SSS'Z'").serializeNulls().create();

    public static GankService getSingleton(){

        if(sService == null){
            synchronized (RetrofitUtil.class){
                if(sService == null){
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
