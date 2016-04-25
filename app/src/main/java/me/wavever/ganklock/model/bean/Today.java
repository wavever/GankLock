package me.wavever.ganklock.model.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import java.util.List;

/**
 * Created by WAVE on 2016/3/13.
 */
@Table(name = "Todays") public class Today extends Model {

    @Column(name = "Tdate") public String todayDate;

    public List<Gank> ganks() {
        return getMany(Gank.class, "Today");
    }
}
