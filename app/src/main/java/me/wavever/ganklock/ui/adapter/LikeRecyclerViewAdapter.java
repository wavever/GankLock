package me.wavever.ganklock.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.ui.adapter.LikeRecyclerViewAdapter.LikeViewHolder;
import me.wavever.ganklock.ui.widget.ColorfulCircleView;

/**
 * Created by waveverht on 2016/10/13.
 */

public class LikeRecyclerViewAdapter extends Adapter<LikeViewHolder> {

    private Context mContext;
    private List<Gank> mList;

    public LikeRecyclerViewAdapter(Context context) {
        mContext = context;
    }


    public void setDataList(List<Gank> list) {
        mList = list;
    }


    @Override
    public LikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_like_rv, parent, false);
        return new LikeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(LikeViewHolder holder, int position) {
        holder.ccv.setText(mList.get(position).getType());
        holder.desc.setText(mList.get(position).getDesc());
    }

    @Override public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class LikeViewHolder extends ViewHolder {

        ColorfulCircleView ccv;
        TextView desc;


        public LikeViewHolder(View itemView) {
            super(itemView);
            ccv = (ColorfulCircleView) itemView.findViewById(R.id.item_like_ccv);
            desc = (TextView) itemView.findViewById(R.id.item_like_desc);
        }
    }
}
