package me.wavever.ganklock.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import me.wavever.ganklock.R;

/**
 * Created by wavever on 2016/8/12.
 */
public class LikeFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_like, container);
    }

    @Override
    public MvpPresenter createPresenter() {
        return null;
    }
}
