package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.GirlListPresenter;
import me.wavever.ganklock.view.IGirlListView;

/**
 * Created by wavever on 2016/5/28.
 */
public class GirlListActivity extends BaseActivity<IGirlListView,GirlListPresenter> implements IGirlListView{

    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_list);
        mRecycleView = (RecyclerView) findViewById(R.id.recycle_view_girl_list);
    }

    @Override
    public GirlListPresenter createPresenter() {
        return new GirlListPresenter();
    }

}
