package me.wavever.ganklock.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.util.StringStyleUtils;

/**
 * Created by WAVE on 2016/3/5.
 */
public class MainRecycleViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Gank> mList;
    private Context mContext;

    private final int ITEM_CATEGORY = 0;
    private final int ITEM_TITLE = 1;


    public MainRecycleViewAdapter(Context context, List<Gank> list) {
        mContext = context;
        mList = list;
    }


    @Override public int getItemViewType(int position) {
        Gank gank = mList.get(position);
        if (position == ITEM_CATEGORY) {
            return ITEM_CATEGORY;
        }
        else {
            boolean isSame = mList.get(position - 1)
                                  .getType()
                                  .equals(mList.get(position).getType());
            if (!isSame) {
                return ITEM_CATEGORY;
            }
            else {
                return ITEM_TITLE;
            }
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_CATEGORY) {
            return new CategoryViewHolder(LayoutInflater.from(mContext)
                                                        .inflate(
                                                                R.layout.item_category,
                                                                null, false));
        }
        if (viewType == ITEM_TITLE) {
            return new TitleViewHolder(LayoutInflater.from(mContext)
                                                     .inflate(
                                                             R.layout.item_main_list,
                                                             null, false));
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Gank gank = mList.get(position);
        if (holder instanceof CategoryViewHolder) {
            ((CategoryViewHolder) holder).category.setText(gank.getType());
            ((CategoryViewHolder) holder).title2.setText(
                    StringStyleUtils.getGankInfoSequence(mContext, gank));
        }
        if (holder instanceof TitleViewHolder) {
            ((TitleViewHolder) holder).title.setText(
                    StringStyleUtils.getGankInfoSequence(mContext, gank));
        }
    }


    @Override public int getItemCount() {
        return mList.size();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView category;
        TextView title2;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.tv_category);
            title2 = (TextView) itemView.findViewById(R.id.tv_title2);
        }
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {

        TextView title;


        public TitleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
