package me.wavever.ganklock.ui.activity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.adapter.LockRecycleViewAdapter;
import me.wavever.ganklock.util.DateUtil;

/**
 * Created by wavever on 2016/8/6.
 */
public class LockView extends FrameLayout {

    private ImageView mBgImageView = null;
    private ViewPager mViewPager = null;
    private TextView mWeek = null;
    private TextView mDate = null;
    private RecyclerView mRecyclerView = null;
    private List<View> mViews = null;

    public LockView(Context context) {
        super(context);
        initUI();
    }

    public LockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public LockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        View container = LayoutInflater.from(getContext()).inflate(R.layout.activity_lock, this);
        mBgImageView = (ImageView) container.findViewById(R.id.img_pager_bg);
        mViewPager = (ViewPager) container.findViewById(R.id.lock_viewpager);

        View lockView = View.inflate(MyApplication.getContext(), R.layout.view_lock, null);
        mWeek = (TextView) lockView.findViewById(R.id.tv_week);
        mDate = (TextView) lockView.findViewById(R.id.tv_year);
        mWeek.setText(DateUtil.getWeek());
        mDate.setText(DateUtil.getTodayFormatDate());

        View gankView = View.inflate(MyApplication.getContext(), R.layout.view_content, null);
        mRecyclerView = (RecyclerView) gankView.findViewById(R.id.rv_lock);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new LockRecycleViewAdapter(null, getContext()));//TODO设置recyclerview数据

        mViews = new ArrayList<>();
        mViews.add(lockView);
        mViews.add(gankView);
        mViewPager.setAdapter(new LockViewPager(mViews));
    }



    class LockViewPager extends PagerAdapter {

        List<View> views = new ArrayList<>();

        public LockViewPager(List<View> views) {
            this.views = views;
        }


        @Override
        public int getCount() {
            return views.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position), 0);
            return views.get(position);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }

}
