package me.wavever.ganklock.ui.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.event.ClickEvent;
import me.wavever.ganklock.event.RxBus;

/**
 * Created by wavever on 2016/9/27.
 */

public class MeizhiRecyclerViewAdapter
    extends RecyclerView.Adapter<MeizhiRecyclerViewAdapter.MeizhiViewHolder> {

    private List<Bitmap> mList;


    public void setList(List<Bitmap> list) {
        this.mList = list;
    }


    @Override
    public MeizhiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_meizhi_recycler_view, parent, false);
        return new MeizhiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeizhiViewHolder holder, int position) {
        holder.position = position;
        holder.img.setImageBitmap(mList.get(position));
       //Picasso.with(MyApplication.getContext()).load().
    }


    @Override public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MeizhiViewHolder extends ViewHolder {

        ImageView img;
        int position;

        public MeizhiViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_photo);
            img.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    RxBus.getInstance().post(new ClickEvent(ClickEvent.CLICK_TYPE_MEIZHI,position));
                }
            });
        }
    }
}
