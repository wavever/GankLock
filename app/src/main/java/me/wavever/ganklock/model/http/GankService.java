package me.wavever.ganklock.model.http;

import me.wavever.ganklock.model.bean.GankData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WAVE on 2016/3/4.
 */
public interface GankService {
    @GET("/day/{date}")
    Observable<GankData> getGankData(@Path("date") String date);

    @GET("/data/福利/{number}/{page}")
    Observable<GankData> getGirlData(@Path("number") int number, @Path("page") int page);

    @GET("/category/{}/count/{}/page/{}")
    Observable<GankData> searchGankData(@Query("category") String category,@Path("count") int count,@Path("page") int page);
}
