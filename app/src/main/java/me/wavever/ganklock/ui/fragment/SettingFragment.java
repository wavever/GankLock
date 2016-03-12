package me.wavever.ganklock.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.widget.Toast;
import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.R;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.service.LockService;

/**
 * Created by WAVE on 2016/2/23.
 */
public class SettingFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener,
        Preference.OnPreferenceClickListener {

    SwitchPreference isOpen;
    Preference isRefresh;
    Preference changeStyle;


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        findPreference(
                getString(R.string.key_is_open)).setOnPreferenceChangeListener(
                this);
        findPreference(getString(
                R.string.key_change_style)).setOnPreferenceClickListener(this);
        findPreference(getString(
                R.string.notify_refresh)).setOnPreferenceChangeListener(this);
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        if (key.equals(getString(R.string.key_is_open))) {
            if ((Boolean) newValue == true) {
                Toast.makeText(getActivity(), "Gank锁屏已经开启", Toast.LENGTH_SHORT)
                     .show();
                MyApplication.getSp().putBoolean(Config.LOCK_IS_OPEN, true);
                getActivity().startService(
                        new Intent(getActivity(), LockService.class));
            }
            else {
                Toast.makeText(getActivity(), "Gank锁屏已经关闭", Toast.LENGTH_SHORT)
                     .show();
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
            Toast.makeText(getActivity(), "开发中", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
