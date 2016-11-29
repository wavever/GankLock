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
import me.wavever.ganklock.utils.DateUtil;

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
        holder.type.setText(mList.get(position).getType());
        holder.ccv.setText(mList.get(position).getType());
        holder.desc.setText(mList.get(position).getDesc());
        holder.date.setText(DateUtil.formatDate(mList.get(position).getPublishedAt()));
    }


    @Override public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class LikeViewHolder extends ViewHolder {

        ColorfulCircleView ccv;
        TextView type;
        TextView desc;
        TextView date;

        public LikeViewHolder(View itemView) {
            super(itemView);
            ccv = (ColorfulCircleView) itemView.findViewById(R.id.item_like_ccv);
            type = (TextView) itemView.findViewById(R.id.item_like_type);
            desc = (TextView) itemView.findViewById(R.id.item_like_desc);
            date = (TextView) itemView.findViewById(R.id.item_like_date);
        }
    }
}
