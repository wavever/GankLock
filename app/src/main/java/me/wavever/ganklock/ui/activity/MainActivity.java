package me.wavever.ganklock.ui.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.R;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.presenter.MainPresenter;
import me.wavever.ganklock.ui.adapter.MainRecycleViewAdapter;
import me.wavever.ganklock.util.DateUtil;
import me.wavever.ganklock.util.DialogUtil;
import me.wavever.ganklock.util.LogUtil;
import me.wavever.ganklock.util.SharedPreferencesUtil;
import me.wavever.ganklock.view.IMainView;

public class MainActivity extends BaseActivity implements IMainView<Gank> {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private ImageView mImg;

    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayout collapsToolbar;
    private List<String> mDatas;
    private MainRecycleViewAdapter mAdapter;

    private List<Gank> mList;

    private MainPresenter mainPresenter;

    private String mGirl;

    private SharedPreferencesUtil sp = MyApplication.getSp();


    @Override protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getData();
    }


    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mImg = (ImageView) findViewById(R.id.img_main);
        collapsToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapsing_toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        setSupportActionBar(mToolbar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mainPresenter = new MainPresenter(this, this);
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting) {
            Intent intent = new Intent(MainActivity.this,
                    SettingActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.action_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void showErrorSnack(String msg) {
        Snackbar.make(mRecyclerView, msg, Snackbar.LENGTH_LONG).show();
    }


    @Override public void completeGetData(String date) {
        dismissLoading();
       /* Snackbar.make(mRecyclerView, R.string.tips_load_finish,
                Snackbar.LENGTH_LONG).show();*/
        collapsToolbar.setTitle(date);
    }


    @Override public void showLoading() {
        DialogUtil.showLoadingDialog(this);
    }


    @Override public void dismissLoading() {
        DialogUtil.dismissLoadingDialog(this);
        if(sp.getBoolean(Config.IS_FIRST_RUN, true)){
            DialogUtil.showSingleDialog(this,R.string.tips_first_run);
            sp.putBoolean(Config.IS_FIRST_RUN,false);
        }
    }


    @Override public void fillData(List<Gank> list, String girlUrl) {
        mList = list;
        mAdapter = new MainRecycleViewAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
        Picasso.with(this).load(girlUrl).into(mImg);
        MyApplication.getSp().putString("girl", girlUrl);
    }


    @Override public boolean isGetTodayData() {
        return mainPresenter.isGetData();
    }


    private void getData() {
        if (today.equals(
                sp.getString(Config.LAST_GET_DATE, ""))) {
            mainPresenter.loadFromDB();
        }
        else {
            showLoading();
            mainPresenter.getData(today);
        }
    }


    @Override public void onBackPressed() {
        if (System.currentTimeMillis() - time > 2000) {
            Toast.makeText(this, "再按一次退出Gank锁屏", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        }
        else {
            super.onBackPressed();
        }
    }
}
