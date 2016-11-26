package me.wavever.ganklock.model.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by wavever on 2016/9/21.
 */

public class GankDailyContent {

    @SerializedName(value = "error")
    public String error;

    @SerializedName(value = "results")
    public List<GankDaily> results;

}
