package me.wavever.ganklock.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.event.ClickEvent;
import me.wavever.ganklock.event.RxBus;
import me.wavever.ganklock.model.bean.GankDaily;
import me.wavever.ganklock.presenter.DailyGankPresenter;
import me.wavever.ganklock.ui.activity.GankContentActivity;
import me.wavever.ganklock.ui.activity.PhotoActivity;
import me.wavever.ganklock.ui.adapter.DailyListAdapter;
import me.wavever.ganklock.view.IDailyGankView;
import rx.functions.Action1;

import static android.view.View.GONE;

/**
 * Created by wavever on 2016/8/12.
 */
public class DailyGankFragment extends BaseFragment<IDailyGankView, DailyGankPresenter>
    implements IDailyGankView {

    private static final String TAG = DailyGankFragment.class.getSimpleName() + "-->";

    private static DailyGankFragment sFragment = null;
    private Toolbar mToolbar;
    private AppCompatActivity mActivity;
    private RecyclerView mRecyclerView = null;
    private ProgressBar mProgressBar = null;
    private TextView mFailureTextView = null;
    private DailyListAdapter mAdapter = null;
    private LinearSnapHelper mSnapHelper;

    private int mPage = 1;


    @Override
    protected int loadView() {
        return R.layout.fragment_daily;
    }


    @Override
    protected void initView() {
        mActivity = (AppCompatActivity) mContext;
        mToolbar = (Toolbar) mContext.findViewById(R.id.toolbar_daily_fragment);
        mActivity.setSupportActionBar(mToolbar);
        mActivity.setTitle(R.string.bottom_bar_gank);
        mToolbar.inflateMenu(R.menu.main_menu);
        mRecyclerView = (RecyclerView) mContext.findViewById(R.id.daily_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSnapHelper = new LinearSnapHelper();
        if (mRecyclerView.getOnFlingListener() == null) {
            mSnapHelper.attachToRecyclerView(mRecyclerView);
        }
        mProgressBar = (ProgressBar) mContext.findViewById(R.id.daily_loading_progress_bar);
        mFailureTextView = (TextView) mContext.findViewById(R.id.daily_get_failure_tip);
    }


    public static DailyGankFragment getInstance() {
        if (sFragment == null) {
            synchronized (DailyGankFragment.class) {
                if (sFragment == null) {
                    sFragment = new DailyGankFragment();
                }
            }
        }
        return sFragment;
    }


    @Override
    public DailyGankPresenter createPresenter() {
        return new DailyGankPresenter();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        loadDailyData();
        mSubscrition = RxBus.getInstance().tObservable(ClickEvent.class).subscribe(
            new Action1<ClickEvent>() {
                @Override public void call(ClickEvent clickEvent) {
                    if (clickEvent.eventType == ClickEvent.CLICK_TYPE_DAILY_PHOTO) {
                        Intent intent = new Intent(mContext, PhotoActivity.class);
                        intent.putExtra(PhotoActivity.KEY_PHOTO_URL, clickEvent.gankDaily.content);
                        intent.putExtra(PhotoActivity.KEY_PHOTO_ID, clickEvent.gankDaily._id);
                        intent.putExtra(PhotoActivity.KEY_ACTIVITY_JUMPED,PhotoActivity.ACTIVITY_JUMPER_FROM_DAILY);
                        startActivity(intent);
                    } else if (clickEvent.eventType == ClickEvent.CLICK_TYPE_DAILY_TITLE) {
                        jumpToContentActivity(clickEvent.gankDaily.publishedAt.substring(0, 10));
                    }
                }
            });
    }


    private void jumpToContentActivity(String date) {
        date = date.replace('-', '/');
        Intent intent = new Intent(mContext, GankContentActivity.class);
        intent.putExtra(GankContentActivity.KEY_DATE, date);
        startActivity(intent);
    }


    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }


    @Override public void loadDailyData() {
        mPresenter.loadDailyData(mPage);
    }


    @Override
    public void loadFailure() {
        mProgressBar.setVisibility(GONE);
        mFailureTextView.setVisibility(View.VISIBLE);
    }


    @Override public void loadTodayDailyData() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void showDailyData(List<GankDaily> ganks) {
        mAdapter = new DailyListAdapter(ganks, mContext);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(GONE);
        mFailureTextView.setVisibility(GONE);
    }
}
