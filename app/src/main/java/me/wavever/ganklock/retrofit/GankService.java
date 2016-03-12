package me.wavever.ganklock.retrofit;

import me.wavever.ganklock.data.GankData;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by WAVE on 2016/3/4.
 */
public interface GankService {
    @GET("/api/day/{date}") Observable<GankData> getGankData(
            @Path("date") String date);
}
