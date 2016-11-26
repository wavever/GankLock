package me.wavever.ganklock.ui.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

/**
 * Created by wavever on 2016/9/25.
 */

public class BaseRVAdapter<VH extends ViewHolder> extends Adapter<VH>{

    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override public void onBindViewHolder(VH holder, int position) {

    }


    @Override public int getItemCount() {
        return 0;
    }
}
