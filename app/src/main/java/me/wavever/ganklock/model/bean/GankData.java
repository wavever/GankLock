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
        @SerializedName(value = "Android") public List<Gank> androidList;
        @SerializedName(value = "iOS") public List<Gank> iosList;
        @SerializedName(value = "App") public List<Gank> appList;
        @SerializedName(value = "前端") public List<Gank> htmlList;
        @SerializedName(value = "瞎推荐") public List<Gank> recommendList;
        @SerializedName(value = "休息视频") public List<Gank> restVideoList;
        @SerializedName(value = "拓展资源") public List<Gank> extendRespurseList;
        @SerializedName(value = "福利") public List<Gank> girlList;
    }

}
