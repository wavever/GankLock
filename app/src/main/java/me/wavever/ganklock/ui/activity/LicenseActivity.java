package me.wavever.ganklock.ui.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.artitk.licensefragment.ScrollViewLicenseFragment;
import com.artitk.licensefragment.model.License;
import com.artitk.licensefragment.model.LicenseID;
import com.artitk.licensefragment.model.LicenseType;
import java.util.ArrayList;
import me.wavever.ganklock.R;

/**
 * Created by waveverht on 2016/10/18.
 */

public class LicenseActivity extends BaseActivity {

    @Override protected int loadView() {
        return R.layout.fragment_license;
    }


    @Override protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(R.string.more_fragment_item_open_source);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        FragmentManager fragmentManager = getFragmentManager();
        ScrollViewLicenseFragment licenseFragment
            = (ScrollViewLicenseFragment) fragmentManager.findFragmentById(R.id.fragment_license);
        licenseFragment.setLog(true);
        licenseFragment.addLicense(
            new int[] { LicenseID.GSON, LicenseID.LICENSE_FRAGMENT, LicenseID.OKHTTP,
                LicenseID.RETROFIT, LicenseID.PICASSO });
        ArrayList<License> customLicenses = new ArrayList<>();
        customLicenses.add(
            new License(this, "MaterialPreference", LicenseType.MIT_LICENSE, "2015",
                "Jens Driller"));
        customLicenses.add(
            new License(this, "ActiveAndroid", LicenseType.APACHE_LICENSE_20, "2010",
                "Michael Pardo"));
        customLicenses.add(
            new License(this, "MaterialBottomNavigation ", LicenseType.MIT_LICENSE, "2016",
                " Alessandro Crugnola"));
        customLicenses.add(
            new License(this, "PhotoView", LicenseType.APACHE_LICENSE_20, "2011-2012",
                "Chris Banes"));
        customLicenses.add(
            new License(this, "MagicaSakura", LicenseType.APACHE_LICENSE_20, "2016", "Bilibili"));
        customLicenses.add(
            new License(this, "Android Open Source Project", LicenseType.APACHE_LICENSE_20,
                "2009-2012", "Android Open Source Project"));
        licenseFragment.addCustomLicense(customLicenses);
    }


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
