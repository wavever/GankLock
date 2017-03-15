package me.wavever.ganklock.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.bilibili.magicasakura.utils.ThemeUtils;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation.OnMenuItemSelectionListener;
import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.fragment.DailyGankFragment;
import me.wavever.ganklock.ui.fragment.LikeFragment;
import me.wavever.ganklock.ui.fragment.MeizhiFragment;
import me.wavever.ganklock.ui.fragment.MoreFragment;

/**
 * Created by wavever on 2016/5/28.
 */
public class MainActivity extends BaseActivity
    implements OnMenuItemSelectionListener {

    private static final String CURRENT_FRAGMENT_TAG = "=CurrentFragmentTag";
    private static final String CURRENT_FRAGMENT_INDEX = "=CurrentFragmentIndex";

    private BottomNavigation mBottomNavigation;
    private Fragment mCurrentFragment;
    private int mCurrentFragmentIndex;
    private DailyGankFragment dailyGankFragment;
    private LikeFragment likeFragment;
    private MeizhiFragment meizhiFragment;
    private MoreFragment moreFragment;
    private FragmentManager manager;

    private View mContainer;


    @Override protected int loadView() {
        return R.layout.activity_main;
    }

    @Override protected void initView() {
        mContainer = findViewById(R.id.main_activity_container);
        mBottomNavigation = (BottomNavigation) findViewById(R.id.BottomNavigation);
        if (mBottomNavigation != null) {
            mBottomNavigation.setOnMenuItemClickListener(this);
            mBottomNavigation.setBackgroundColor(
                ThemeUtils.getColorById(this, R.color.theme_color_primary));
        }
        manager = getFragmentManager();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决内存重启导致Fragment重叠的问题
        if (savedInstanceState != null) {
            dailyGankFragment = (DailyGankFragment) manager.findFragmentByTag(
                DailyGankFragment.class.getSimpleName());
            likeFragment = (LikeFragment) manager.findFragmentByTag(
                LikeFragment.class.getSimpleName());
            meizhiFragment = (MeizhiFragment) manager.findFragmentByTag(
                MeizhiFragment.class.getSimpleName());
            moreFragment = (MoreFragment) manager.findFragmentByTag(
                MeizhiFragment.class.getSimpleName());
            //TODO 只是展示一个，如果有另外的，没有hide，会不会出现重叠？
            switch (savedInstanceState.getInt(CURRENT_FRAGMENT_TAG)) {
                case 0:
                    manager.beginTransaction().show(dailyGankFragment).commit();
                    mCurrentFragmentIndex = 0;
                    break;
                case 1:
                    manager.beginTransaction().show(likeFragment).commit();
                    mCurrentFragmentIndex = 1;
                    break;
                case 2:
                    manager.beginTransaction().show(meizhiFragment).commit();
                    mCurrentFragmentIndex = 2;
                    break;
                case 3:
                    manager.beginTransaction().show(moreFragment).commit();
                    mCurrentFragmentIndex = 3;
                    break;
            }
        } else {
            dailyGankFragment = DailyGankFragment.getInstance();
            replaceFragmentByTag(dailyGankFragment);
            mCurrentFragmentIndex = 0;
        }
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


    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_FRAGMENT_TAG, mCurrentFragment.getClass().getSimpleName());
        outState.putInt(CURRENT_FRAGMENT_INDEX, mCurrentFragmentIndex);
    }


    @Override
    public void onMenuItemSelect(int itemId, int position) {
        switch (itemId) {
            case R.id.bbn_daliy:
                if (mCurrentFragment != dailyGankFragment) {
                    if (dailyGankFragment == null) {
                        dailyGankFragment = DailyGankFragment.getInstance();
                    }
                    replaceFragmentByTag(dailyGankFragment);
                    mCurrentFragmentIndex = 0;
                }
                break;
            case R.id.bbn_like:
                if (mCurrentFragment != likeFragment) {
                    if (likeFragment == null) {
                        likeFragment = new LikeFragment();
                    }
                    replaceFragmentByTag(likeFragment);
                    mCurrentFragmentIndex = 1;
                }
                break;
            case R.id.bbn_meizhi:
                if (mCurrentFragment != meizhiFragment) {
                    if (meizhiFragment == null) {
                        meizhiFragment = new MeizhiFragment();
                    }
                    replaceFragmentByTag(meizhiFragment);
                    mCurrentFragmentIndex = 2;
                }

                break;
            case R.id.bbn_more:
                if (mCurrentFragment != moreFragment) {
                    if (moreFragment == null) {
                        moreFragment = new MoreFragment();
                    }
                    replaceFragmentByTag(moreFragment);
                    mCurrentFragmentIndex = 3;
                }
                break;
            default:
                break;
        }
    }


    //双击
    @Override
    public void onMenuItemReselect(int itemId, int position) {
        switch (itemId) {
            case R.id.bbn_daliy:
                dailyGankFragment.loadTodayDailyData();
                break;
            case R.id.bbn_like:
                break;
            case R.id.bbn_meizhi:
                break;
            case R.id.bbn_more:
                break;
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
