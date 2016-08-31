package me.wavever.ganklock.model.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by WAVE on 2015/12/24.
 */
@Table(name = "Ganks") public class Gank extends Model implements Serializable {

    @Column(name = "url") private String url;
    @Column(name = "desc") private String desc;
    @Column(name = "who") private String who;
    @Column(name = "type") private String type;
    @Column(name = "used") private boolean used;
    @Column(name = "createdAt") private Date createdAt;
    //2016-03-10T12:54:31.68Z->2016/03/10
    @Column(name = "publishedAt") private Date publishedAt;


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getWho() {
        return who;
    }


    public void setWho(String who) {
        this.who = who;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public boolean isUsed() {
        return used;
    }


    public void setUsed(boolean used) {
        this.used = used;
    }


    public Date getCreateAt() {
        return createdAt;
    }


    public void setCreateAt(Date createAt) {
        this.createdAt = createAt;
    }


    public Date getPublishedAt() {
        return publishedAt;
    }


    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}
