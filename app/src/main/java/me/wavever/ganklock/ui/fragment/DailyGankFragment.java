package me.wavever.ganklock.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.DailyGankPresenter;
import me.wavever.ganklock.view.IDailyGankView;


/**
 * Created by wavever on 2016/8/12.
 */
public class DailyGankFragment extends BaseFragment<IDailyGankView,DailyGankPresenter>{

    private static DailyGankFragment sFragment;

    private RecyclerView mRecyclerView = null;

    private IDailyGankView dailyGankView = null;

    @Override
    protected int loadView() {
        return R.layout.fragment_daily;
    }

    public static DailyGankFragment getInstance(){
        if(sFragment==null){
            synchronized (DailyGankFragment.class){
                sFragment = new DailyGankFragment();
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
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.daily_recycler_view);
        mPresenter.a();
    }
}
