package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.R;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.presenter.GankContentPresenter;
import me.wavever.ganklock.ui.adapter.MainRecycleViewAdapter;
import me.wavever.ganklock.util.DialogUtil;
import me.wavever.ganklock.util.SPUtil;
import me.wavever.ganklock.view.IGankContentView;

public class GankContentActivity extends BaseActivity<IGankContentView, GankContentPresenter> implements IGankContentView {

    private static final String TAG = GankContentActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private ImageView mImg;

    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayout collapsToolbar;
    private List<String> mDatas;
    private MainRecycleViewAdapter mAdapter;

    private List<Gank> mList;

    private String mGirl;

    private SPUtil sp = MyApplication.getSp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_content);
        initView();
        getData();
    }


    @NonNull
    @Override
    public GankContentPresenter createPresenter() {
        return new GankContentPresenter();
    }

    @Override
    public GankContentPresenter getPresenter() {
        return new GankContentPresenter();
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gank_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_gank_go_brower) {
            Toast.makeText(this, R.string.action_brower, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_gank_copy_link) {
            Toast.makeText(this, R.string.copy_link_finish, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showErrorSnack(String msg) {
        Snackbar.make(mRecyclerView, msg, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void completeGetData(String date) {
        dismissLoading();
       /* Snackbar.make(mRecyclerView, R.string.tips_load_finish,
                Snackbar.LENGTH_LONG).show();*/
        collapsToolbar.setTitle(date);
    }


    @Override
    public void showLoading() {
        DialogUtil.showLoadingDialog(this);
    }


    @Override
    public void dismissLoading() {
        DialogUtil.dismissLoadingDialog();
        if (sp.getBoolean(Config.IS_FIRST_RUN, true)) {
            DialogUtil.showSingleDialog(this, R.string.tips_first_run);
            sp.putBoolean(Config.IS_FIRST_RUN, false);
        }
    }


    @Override
    public void fillData(List<Gank> list, String girlUrl) {
        mList = list;
        mAdapter = new MainRecycleViewAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
        Picasso.with(this).load(girlUrl).into(mImg);
        MyApplication.getSp().putString("girl", girlUrl);
    }


    private void getData() {
        getPresenter().loadFromDB();

    }

    @Override
    public void initViews() {

    }
}
