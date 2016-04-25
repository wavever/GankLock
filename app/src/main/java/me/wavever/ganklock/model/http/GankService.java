package me.wavever.ganklock.model.http;

import me.wavever.ganklock.model.bean.GankData;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by WAVE on 2016/3/4.
 */
public interface GankService {
    @GET("/api/day/{date}") Observable<GankData> getGankData(
            @Path("date") String date);
/*
    @GET("/api/day/{year}/{month}/{day}") Observable<GankData> getGankData(
            @Path("year") int year,@Path("month")int month,@Path("day")int day);*/
}
