package me.wavever.ganklock.model.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wavever on 2016/9/9.
 */
@Table(name = "GankDaily")
public class GankDaily extends Model {
    @SerializedName(value = "_id")
    @Column(name = "_id")
    public String _id;
    @SerializedName(value = "publishedAt")
    @Column(name = "publishedAt")
    public String publishedAt;
    @SerializedName(value = "title")
    @Column(name = "title")
    public String title;
    @SerializedName(value = "content")
    @Column(name = "content")
    public String content;//妹纸图的url
}
