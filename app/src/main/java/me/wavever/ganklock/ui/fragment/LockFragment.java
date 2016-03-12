package me.wavever.ganklock.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import me.wavever.ganklock.R;
import me.wavever.ganklock.util.DateUtil;

/**
 * Created by WAVE on 2016/2/23.
 */
public class LockFragment extends Fragment {

    private ImageView mImageView;
    private TextClock mTime;
    private TextView mYear;
    private TextView mWeek;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lock, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageView = (ImageView) getView().findViewById(R.id.gank_img);
        mTime = (TextClock) getView().findViewById(R.id.tc_time);
        mYear = (TextView) getView().findViewById(R.id.tv_year);
        mWeek = (TextView) getView().findViewById(R.id.tv_week);
        mYear.setText(DateUtil.getTodayFormatDate());
        mWeek.setText(DateUtil.getWeek());
    }


}
