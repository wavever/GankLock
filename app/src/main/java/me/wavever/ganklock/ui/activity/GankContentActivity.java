package me.wavever.ganklock.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.event.RxBus;
import me.wavever.ganklock.event.StatusEvent;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.presenter.GankContentPresenter;
import me.wavever.ganklock.ui.adapter.GankListAdapter;
import me.wavever.ganklock.ui.adapter.GankListAdapter.OnItemClickListener;
import me.wavever.ganklock.view.IGankContentView;

public class GankContentActivity extends BaseMvpActivity<IGankContentView, GankContentPresenter>
    implements IGankContentView, OnItemClickListener {

    public static final String KEY_DATE = "key_date";

    private static final String TAG = "GankContentActivity-->";
    private String date;
    private RecyclerView mRecyclerView;
    private TextView mEmptyTip;
    private ProgressBar mLoadingBar;
    private GankListAdapter mAdapter;
    private List<Gank> mList;

    @Override public GankContentPresenter createPresenter() {
        return new GankContentPresenter();
    }


    @Override protected int loadView() {
        return R.layout.activity_gank_content;
    }


    @Override protected void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_gank_content);
        mEmptyTip = (TextView) findViewById(R.id.empty_tip_gank_content);
        mLoadingBar = (ProgressBar) findViewById(R.id.progress_bar_gank_content);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date = getIntent().getStringExtra(KEY_DATE);
        setTitle(date);
        loadData(date);
    }


    @Override public void loadData(String date) {
        mLoadingBar.setVisibility(View.VISIBLE);
        getPresenter().loadContentData(date);
    }


    @Override public void showData(List<Gank> list) {
        mLoadingBar.setVisibility(View.GONE);
        mList = list;
        mAdapter = new GankListAdapter(this, mList);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    @Override public void showError() {
        mEmptyTip.setVisibility(View.VISIBLE);
        mLoadingBar.setVisibility(View.GONE);
    }


    @Override public void onItemClick(View view, View cardView, ImageView like,ImageView share,Gank gank) {
        if (view == cardView) {
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra(WebViewActivity.KEY_TITLE, gank.getDesc());
            intent.putExtra(WebViewActivity.KEY_URL, gank.getUrl());
            startActivity(intent);
        } else if (view == like) {
            if (gank.getIsLike()) {
                like.setImageResource(R.drawable.ic_favorite_grey_500_24dp);
                gank.setIsLike(false);
            } else {
                like.setImageResource(R.drawable.ic_favorite_red_500_24dp);
                //TODO 待优化
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(like,"scaleX",1,1.7f,1);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(like,"scaleY",1,1.7f,1);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator1,animator2);
                set.setDuration(400);
                set.setInterpolator(new OvershootInterpolator(3f));
                set.start();
                gank.setIsLike(true);
            }
        }else if(view == share){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,gank.getDesc()+gank.getUrl());
            startActivity(Intent.createChooser(intent, "分享到"));
        }
    }


    @Override public void onBackPressed() {
        super.onBackPressed();
        //TODO 遍历数据，将isLike==true的数据存到数据库，优化线程
        if (mList == null || mList.isEmpty()) {
            return;
        }
        for (Gank gank : mList) {
            if (!new Select().from(Gank.class).where("_id=?", gank.get_id()).exists() &&
                gank.getIsLike()) {
                gank.save();
            }
            if (!gank.getIsLike() &&
                new Select().from(Gank.class)
                    .where("_id=?", gank.get_id())
                    .exists()) {//从数据库中将取消收藏的删除
                new Delete().from(Gank.class).where("_id=?", gank.get_id()).execute();
            }
            RxBus.getInstance().post(new StatusEvent(StatusEvent.TYPE_ON_LIKE_CHANGE));
        }
    }
}
