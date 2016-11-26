package me.wavever.ganklock.model.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by wavever on 2016/10/13.
 */
@Table(name = "Likes")
public class Like extends Model{

    @Column(name = "_id")
    public String _id;
    @Column(name = "desc")
    public String desc;
    @Column(name = "publishedAt")
    public String publishedAt;
    @Column(name = "type")
    public String type;
    @Column(name = "url")
    public String url;
    @Column(name = "who")
    public String who;


    public Like(String _id, String desc, String publishedAt, String type, String url, String who) {
        this._id = _id;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.type = type;
        this.url = url;
        this.who = who;
    }
}
