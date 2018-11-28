package me.wavever.ganklock.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.fragment.DailyGankFragment;
import me.wavever.ganklock.ui.fragment.LikeFragment;
import me.wavever.ganklock.ui.fragment.MeizhiFragment;
import me.wavever.ganklock.ui.fragment.MoreFragment;

/**
 * Created by wavever on 2016/5/28.
 */
public class MainActivity extends BaseActivity {

    private Fragment mCurrentFragment;
    private DailyGankFragment dailyGankFragment;
    private LikeFragment likeFragment;
    private MeizhiFragment meizhiFragment;
    private MoreFragment moreFragment;
    private FragmentManager manager;

    private View mContainer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bbn_daliy:
                    if (mCurrentFragment != dailyGankFragment) {
                        if (dailyGankFragment == null) {
                            dailyGankFragment = DailyGankFragment.getInstance();
                        }
                        replaceFragmentByTag(dailyGankFragment);
                    }
                    break;
                case R.id.bbn_like:
                    if (mCurrentFragment != likeFragment) {
                        if (likeFragment == null) {
                            likeFragment = new LikeFragment();
                        }
                        replaceFragmentByTag(likeFragment);
                    }
                    break;
                case R.id.bbn_meizhi:
                    if (mCurrentFragment != meizhiFragment) {
                        if (meizhiFragment == null) {
                            meizhiFragment = new MeizhiFragment();
                        }
                        replaceFragmentByTag(meizhiFragment);
                    }
                    break;
                case R.id.bbn_more:
                    if (mCurrentFragment != moreFragment) {
                        if (moreFragment == null) {
                            moreFragment = new MoreFragment();
                        }
                        replaceFragmentByTag(moreFragment);
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    @Override protected int loadView() {
        return R.layout.activity_main;
    }


    @Override protected void initView() {
        mContainer = findViewById(R.id.main_activity_container);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        manager = getFragmentManager();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决内存重启导致Fragment重叠的问题
        dailyGankFragment = DailyGankFragment.getInstance();
        replaceFragmentByTag(dailyGankFragment);
    }


    long time = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time > 2000) {
            Snackbar.make(mContainer, R.string.exit_with_two_click, Snackbar.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    public void replaceFragmentByTag(Fragment fragment) {
        if (mCurrentFragment == null) {
            manager.beginTransaction().add(R.id.main_activity_container, fragment,
                fragment.getClass().getSimpleName()).commit();
        } else {
            if (fragment.isAdded()) {
                manager.beginTransaction().hide(mCurrentFragment).show(fragment).commit();
            } else {
                manager.beginTransaction().hide(mCurrentFragment)
                    .add(R.id.main_activity_container, fragment,
                        fragment.getClass().getSimpleName())
                    .commit();
            }
        }
        mCurrentFragment = fragment;
    }
}
