package me.wavever.ganklock.receiver;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.R;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.model.Gank;
import me.wavever.ganklock.presenter.MainPresenter;
import me.wavever.ganklock.ui.adapter.LockRecycleViewAdapter;
import me.wavever.ganklock.util.DateUtil;

/**
 * Created by WAVE on 2015/12/24.
 */
public class GankLockReceiver extends BroadcastReceiver {

    private static final String TAG = GankLockReceiver.class.getSimpleName();

    private KeyguardManager keyguardManager;
    private KeyguardManager.KeyguardLock keyguardLock;
    private View mContainer;
    private String girl;
    private MainPresenter mainPresenter;
    private long firstTouch;
    private long lastTouch;
    private int count;


    @Override public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            keyguardManager = (KeyguardManager) context.getSystemService(
                    Context.KEYGUARD_SERVICE);
            keyguardLock = keyguardManager.newKeyguardLock("");
            keyguardLock.disableKeyguard();
            if (MyApplication.getSp().getBoolean(Config.LOCK_IS_OPEN, false)) {
                createLockView(context);
            }
        }
        else if (action.equals(Intent.ACTION_SCREEN_ON)) {
        }
    }


    private void createLockView(Context context) {
        mContainer = View.inflate(context, R.layout.activity_lock, null);
        final View lockView = View.inflate(MyApplication.getContext(),
                R.layout.view_lock, null);
        View gankView = View.inflate(context, R.layout.view_content, null);
        final WindowManager windowManager
                = (WindowManager) MyApplication.getContext()
                                               .getSystemService(
                                                       Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = -1;
        lp.height = -1;
        lp.type = 2010;
        lp.flags |= WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

        //设置viewpager
        ViewPager viewPager = (ViewPager) mContainer.findViewById(
                R.id.lock_viewpager);
        List<View> viewList = new ArrayList<>();
        viewList.add(lockView);
        viewList.add(gankView);
        viewPager.setAdapter(new LockViewPager(viewList));

        //设置时间
        TextView week = (TextView) lockView.findViewById(R.id.tv_week);
        TextView date = (TextView) lockView.findViewById(R.id.tv_year);
        ImageView lockImg = (ImageView) mContainer.findViewById(
                R.id.img_pager_bg);
        //设置RcycleView
        RecyclerView rvLock = (RecyclerView) gankView.findViewById(
                R.id.rv_lock);
        rvLock.setLayoutManager(new LinearLayoutManager(context));
        rvLock.setAdapter(new LockRecycleViewAdapter(loadFromDB(), context));
        week.setText(DateUtil.getWeek());
        date.setText(DateUtil.getTodayFormatDate());

        girl = MyApplication.getSp().getString("girl", "null");

        if (!girl.equals("null")) {
            Picasso.with(context).load(girl).into(lockImg);
        }
        else {
            lockImg.setImageResource(R.drawable.gank);
        }

        Button btn = (Button) lockView.findViewById(R.id.btn_unlock);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                windowManager.removeView(mContainer);
            }
        });

        windowManager.addView(mContainer, lp);
    }


    private List<Gank> loadFromDB() {
        List<Gank> ganks = new Select().from(Gank.class).execute();
        return ganks;
    }


    class LockViewPager extends PagerAdapter {

        List<View> views = new ArrayList<>();


        public LockViewPager(List<View> views) {
            this.views = views;
        }


        @Override public int getCount() {
            return views.size();
        }


        @Override public boolean isViewFromObject(View view, Object object) {
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
