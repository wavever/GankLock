package me.wavever.ganklock.config;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by WAVE on 2016/3/5.
 */
public interface GithubServer {
    @GET("/user/{user}") Call<List<gitmodel>> listRepos(
            @Path("user") String user);
}
