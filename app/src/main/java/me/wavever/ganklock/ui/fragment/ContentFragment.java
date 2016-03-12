package me.wavever.ganklock.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.wavever.ganklock.R;

/**
 * Created by WAVE on 2016/2/23.
 */
public class ContentFragment extends Fragment {

    private static ContentFragment contentFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        return view;
    }

    public static ContentFragment getInstance() {

        if (contentFragment == null) {
            contentFragment = new ContentFragment();
            return contentFragment;
        } else {
            return contentFragment;
        }
    }

}
