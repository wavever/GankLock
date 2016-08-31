package me.wavever.ganklock.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.R;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.service.LockService;
import me.wavever.ganklock.util.ToastUtil;

/**
 * Created by wavever on 2016/2/23.
 */
public class SettingFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener,
        Preference.OnPreferenceClickListener {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        findPreference(
                getString(R.string.key_is_open)).setOnPreferenceChangeListener(
                this);
        findPreference(getString(
                R.string.key_change_style)).setOnPreferenceClickListener(this);
        findPreference(getString(
                R.string.key_auto_refresh)).setOnPreferenceChangeListener(this);
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        if (key.equals(getString(R.string.key_is_open))) {
            if ((Boolean) newValue) {
                ToastUtil.showToastShort(getActivity(),"Gank锁屏已经开启");
                MyApplication.getSp().putBoolean(Config.LOCK_IS_OPEN, true);
                getActivity().startService(
                        new Intent(getActivity(), LockService.class));
            }
            else {
                ToastUtil.showToastShort(getActivity(),"Gank锁屏已经关闭");
                MyApplication.getSp().putBoolean(Config.LOCK_IS_OPEN, false);
                getActivity().stopService(
                        new Intent(getActivity(), LockService.class));
            }
        }
        return true;
    }


    @Override public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (key.equals(getString(R.string.key_change_style))) {
            ToastUtil.showToastShort(getContext(),"开发中");
        }
        return true;
    }
}
