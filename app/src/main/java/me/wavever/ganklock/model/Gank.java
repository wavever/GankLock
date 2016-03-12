package me.wavever.ganklock.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WAVE on 2015/12/24.
 */
public class Gank implements Serializable {

    private String url;
    private String desc;
    private String who;
    private String type;
    private boolean used;
    private Date createAt;
    private Date updateAt;

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
        return createAt;
    }


    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    public Date getUpdateAt() {
        return updateAt;
    }


    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
