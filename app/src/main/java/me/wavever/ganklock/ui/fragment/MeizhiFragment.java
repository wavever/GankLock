package me.wavever.ganklock.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.MeiZhiPresenter;
import me.wavever.ganklock.view.IMeiZhiView;

/**
 * Created by wavever on 2016/8/12.
 */
public class MeizhiFragment extends BaseFragment<IMeiZhiView,MeiZhiPresenter> implements IMeiZhiView{

    @Override
    protected int loadView() {
        return R.layout.fragment_meizhi;
    }

    @Override
    public MeiZhiPresenter createPresenter() {
        return new MeiZhiPresenter();
    }

    @Override
    public void initViews() {

    }
}
