package me.wavever.ganklock.model.http;

import me.wavever.ganklock.model.bean.GankContent;
import me.wavever.ganklock.model.bean.GankDailyContent;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wavever on 2016/3/4.
 */
public interface GankService {
    @GET("day/{date}")
    Observable<GankContent> getGankData(@Path("date") String date);

    @GET("history/content/{count}/{page}")
    Observable<GankDailyContent> getDailyData(@Path("count")int count, @Path("page")int page);

    @GET("data/福利/{number}/{page}")
    Observable<GankContent> getGirlData(@Path("number") int number, @Path("page") int page);

    @GET("category/{}/count/{}/page/{}")
    Observable<GankContent> searchGankData(@Query("category") String category,@Path("count") int count,@Path("page") int page);
}
