package me.wavever.ganklock.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import me.wavever.ganklock.config.GankApi;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by WAVE on 2016/3/11.
 */
public class WaveverRetrofit {

    final GankService service;

    public static final String api = GankApi.BASE_URL;

    final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss" +
            ".SSS'Z'").serializeNulls().create();

    public WaveverRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api)
                                                  .addConverterFactory(
                                                          GsonConverterFactory.create(gson))
                                                  .client(new OkHttpClient())
                                                  .addCallAdapterFactory(
                                                          RxJavaCallAdapterFactory
                                                                  .create())
                                                  .build();

        service = retrofit.create(GankService.class);
    }


    public GankService getService() {
        return service;
    }
}
