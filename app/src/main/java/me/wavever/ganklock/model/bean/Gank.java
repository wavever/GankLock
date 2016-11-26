package me.wavever.ganklock.model.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wavever on 2015/12/24.
 */
@Table(name = "Ganks") public class Gank extends Model implements Serializable {

    @Column(name = "_id") private String _id;
    @Column(name = "url") private String url;
    @Column(name = "desc") private String desc;
    @Column(name = "who") private String who;
    @Column(name = "type") private String type;
    @Column(name = "used") private boolean used;
    @Column(name = "createdAt") private Date createdAt;
    //2016-03-10T12:54:31.68Z->2016/03/10
    @Column(name = "publishedAt") private Date publishedAt;
    private boolean isLike;


    public String get_id() {
        return _id;
    }


    public Date getCreatedAt() {
        return createdAt;
    }


    public String getUrl() {
        return url;
    }


    public String getDesc() {
        return desc;
    }


    public String getWho() {
        return who;
    }


    public String getType() {
        return type;
    }


    public boolean isUsed() {
        return used;
    }


    public Date getCreateAt() {
        return createdAt;
    }


    public Date getPublishedAt() {
        return publishedAt;
    }


    public void setIsLike(boolean like) {
        isLike = like;
    }

    public boolean getIsLike() {
        return isLike;
    }
}
