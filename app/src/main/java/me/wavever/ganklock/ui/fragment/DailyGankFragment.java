package me.wavever.ganklock.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.DailyGankPresenter;
import me.wavever.ganklock.view.IDailyGankView;

/**
 * Created by wavever on 2016/8/12.
 */
public class DailyGankFragment extends Fragment{

    private static DailyGankFragment sFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily,container);
    }

    public static DailyGankFragment getInstance(){
        if(sFragment==null){
            synchronized (DailyGankFragment.class){
                sFragment = new DailyGankFragment();
            }
        }
        return sFragment;
    }

}
