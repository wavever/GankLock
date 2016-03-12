package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.fragment.ContentFragment;
import me.wavever.ganklock.ui.fragment.LockFragment;

/**
 * Created by WAVE on 2015/12/24.
 */
public class LockScreenActivity extends BaseActivity {

    private ViewPager mPager;
    private FragmentPagerAdapter mAdapter;
    private Fragment mContent;
    private Fragment mLock;
    private List<Fragment> mFragments;


    @Override protected int getLayoutId() {
        return R.layout.activity_lock;
    }


    @Override protected void initPresenter() {

    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(4);
        mPager = (ViewPager) findViewById(R.id.lock_viewpager);
        mFragments = new ArrayList<>();
        mContent = new ContentFragment();
        mLock = new LockFragment();
        mFragments.add(mContent);
        mFragments.add(mLock);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override public Fragment getItem(int position) {
                return mFragments.get(position);
            }


            @Override public int getCount() {
                return mFragments.size();
            }
        };
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(1);
    }


    @Override public void onBackPressed() {

    }
}
