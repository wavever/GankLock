package me.wavever.ganklock.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by WAVE on 2016/3/10.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override public Fragment getItem(int position) {
        return null;
    }


    @Override public int getCount() {
        return 2;
    }


    @Override public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
