package me.wavever.ganklock.ui.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import me.wavever.ganklock.R;
import me.wavever.ganklock.model.bean.GankDaily;
import me.wavever.ganklock.utils.ToastUtil;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by wavever on 2016/2/23.
 */
public class SettingFragment extends PreferenceFragment
    implements Preference.OnPreferenceChangeListener,
    Preference.OnPreferenceClickListener {

    private Subscription subscription;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        findPreference(getString(R.string.key_lock_style)).setOnPreferenceClickListener(this);
        findPreference(getString(R.string.key_auto_refresh)).setOnPreferenceChangeListener(this);
        findPreference(getString(R.string.key_statusbar_hide)).setOnPreferenceChangeListener(this);
        findPreference(getString(R.string.key_statusbar_transparent)).setOnPreferenceChangeListener(
            this);
        findPreference(getString(R.string.key_shake_feed_back)).setOnPreferenceChangeListener(this);
        findPreference("key_clear_cache").setOnPreferenceClickListener(this);
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        if (key.equals(getString(R.string.key_is_open))) {
            if ((Boolean) newValue) {
            } else {
            }
        }else if(key.equals(getString(R.string.key_statusbar_hide))){

        }
        return true;
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (key.equals(getString(R.string.key_clear_cache))) {
            Observable.create(new Observable.OnSubscribe<Integer>() {
                @Override public void call(Subscriber<? super Integer> subscriber) {
                    new Delete().from(GankDaily.class).execute();
                    subscriber.onNext(new Select().from(GankDaily.class).count());
                }
            }).subscribe(new Action1<Integer>() {
                @Override public void call(Integer count) {
                    if (count == 0) {
                        ToastUtil.showToastShort(getActivity(), "清除缓存成功~");
                    } else {
                        ToastUtil.showToastShort(getActivity(), "清除缓存失败~");
                    }
                }
            });
        } else if (key.equals(getString(R.string.key_lock_style))) {
            ToastUtil.showToastShort(getActivity(), "锁屏样式");
        }
        return false;
    }


    @Override public void onDestroy() {
        super.onDestroy();
    }
}
