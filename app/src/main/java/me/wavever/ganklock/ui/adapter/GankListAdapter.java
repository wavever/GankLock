package me.wavever.ganklock.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.activeandroid.query.Select;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.ui.adapter.GankListAdapter.GankListHolder;
import me.wavever.ganklock.ui.widget.ColorfulCircleView;

/**
 * Created by wavever on 2016/3/5.
 */
public class GankListAdapter extends Adapter<GankListHolder> {

    private List<Gank> mList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    private final int ITEM_CATEGORY = 0;
    private final int ITEM_TITLE = 1;


    public GankListAdapter(Context context, List<Gank> list) {
        mContext = context;
        mList = list;
    }


    public void setOnItemClickListener(GankListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public GankListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_gank_rv_content, parent, false);
        if (viewType == ITEM_CATEGORY) {
            return new GankListHolder(view, true);
        } else {
            return new GankListHolder(view, false);
        }
    }


    @Override public void onBindViewHolder(GankListHolder holder, int position) {
        Gank gank = mList.get(position);
        holder.setIsRecyclable(false);
        holder.gank = gank;
        holder.category.setText(gank.getType());
        holder.ccv.setText(gank.getWho());
        holder.who.setText(gank.getWho());
        holder.desc.setText(gank.getDesc());
        if (new Select().from(Gank.class).where("_id=?", gank.get_id()).exists()) {
            holder.like.setImageResource(R.drawable.ic_favorite_red_500_24dp);
        }
    }


    @Override public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_CATEGORY;
        } else {
            boolean isSame = mList.get(position - 1)
                .getType()
                .equals(mList.get(position).getType());
            if (!isSame) {
                return ITEM_CATEGORY;
            } else {
                return ITEM_TITLE;
            }
        }
    }


    @Override public int getItemCount() {
        return mList.size();
    }


    class GankListHolder extends ViewHolder implements View.OnClickListener {

        Gank gank;
        CardView cardView;
        TextView category;
        ColorfulCircleView ccv;
        TextView who;
        ImageView like;
        ImageView share;
        TextView desc;


        public GankListHolder(View itemView, boolean isShow) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.item_gank_card_view);
            cardView.setOnClickListener(this);
            category = (TextView) itemView.findViewById(R.id.item_gank_category);
            ccv = (ColorfulCircleView) itemView.findViewById(R.id.item_gank_ccv);
            who = (TextView) itemView.findViewById(R.id.item_gank_who);
            like = (ImageView) itemView.findViewById(R.id.item_gank_like);
            like.setOnClickListener(this);
            share = (ImageView) itemView.findViewById(R.id.item_gank_share);
            share.setOnClickListener(this);
            desc = (TextView) itemView.findViewById(R.id.item_gank_desc);
            if (isShow) {
                category.setVisibility(View.VISIBLE);
            } else {
                category.setVisibility(View.GONE);
            }
        }


        @Override public void onClick(View v) {
            onItemClickListener.onItemClick(v, cardView, like, share, gank);
        }

    }


    public interface OnItemClickListener {
        void onItemClick(View view, View layout, ImageView like, ImageView share, Gank gank);
    }
}
