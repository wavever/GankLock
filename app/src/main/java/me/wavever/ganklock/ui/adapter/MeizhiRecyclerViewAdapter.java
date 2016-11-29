package me.wavever.ganklock.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.event.ClickEvent;
import me.wavever.ganklock.event.RxBus;
import me.wavever.ganklock.ui.adapter.MeizhiRecyclerViewAdapter.MeizhiViewHolder;

/**
 * Created by wavever on 2016/9/27.
 */

public class MeizhiRecyclerViewAdapter extends Adapter<MeizhiViewHolder> {

    private List<File> mList;
    private Context mContext;

    public void setList(List<File> list) {
        this.mList = list;
    }

    public MeizhiRecyclerViewAdapter(Context context){
        mContext = context;
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
        Picasso.with(mContext).load(mList.get(position)).resize(300,300).into(holder.img);
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
                    RxBus.getInstance()
                        .post(new ClickEvent(ClickEvent.CLICK_TYPE_MEIZHI, position));
                }
            });
        }
    }
}
