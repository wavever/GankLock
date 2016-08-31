package me.wavever.ganklock.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import me.wavever.ganklock.R;

/**
 * Created by waveverht on 2016/5/30.
 */
public class GirlPhotoActivity extends MvpActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return null;
    }
}
