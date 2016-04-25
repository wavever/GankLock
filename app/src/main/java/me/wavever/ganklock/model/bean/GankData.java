package me.wavever.ganklock.model.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by WAVE on 2016/3/5.
 */
public class GankData {

    public List<String> category;

    public boolean error;

    public Result results;

    public class Result {
        @SerializedName("Android") public List<Gank> androidList;
        @SerializedName("iOS") public List<Gank> iosList;
        @SerializedName("App") public List<Gank> appList;
        @SerializedName("前端") public List<Gank> htmlList;
        @SerializedName("瞎推荐") public List<Gank> recommendList;
        @SerializedName("休息视频") public List<Gank> restVideoList;
        @SerializedName("福利") public List<Gank> girlList;
    }

}
