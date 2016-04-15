package me.wavever.ganklock.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.model.bean.Gank;
import me.wavever.ganklock.util.StringStyleUtils;

/**
 * Created by WAVE on 2016/3/15.
 */
public class LockRecycleViewAdapter
        extends RecyclerView.Adapter<LockRecycleViewAdapter.MyViewHolder> {

    private List<Gank> mList;
    private Context mContext;


    public LockRecycleViewAdapter(List<Gank> list, Context context) {
        mList = list;
        mContext = context;
    }


    @Override
    public LockRecycleViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R
                .layout.item_lock_rv,null,false));
    }


    @Override
    public void onBindViewHolder(LockRecycleViewAdapter.MyViewHolder holder, int position) {

        Gank gank = mList.get(position);

        if (position == 0) {
            showCategory(holder);
        }
        else {
            boolean theCategoryOfLastEqualsToThis =
                    mList.get(position - 1).getType().equals(
                            mList.get(position).getType());
            if (!theCategoryOfLastEqualsToThis) {
                showCategory(holder);
            }
            else {
                hideCategory(holder);
            }
        }
        holder.category.setText(gank.getType());
        SpannableStringBuilder builder = new SpannableStringBuilder(gank.getDesc())
                .append(
                StringStyleUtils.format(holder.title2.getContext(),
                        " (" + gank.getWho() + ")",R.style.LockViaTextAppearance));
        CharSequence gankText = builder.subSequence(0, builder.length());

        holder.title2.setText(gankText);
    }


    @Override public int getItemCount() {
        return mList.size();
    }


    private void showCategory(MyViewHolder holder) {
        if (!isVisibleOf(holder.category)) {
            holder.category.setVisibility(View.VISIBLE);
        }
    }


    private void hideCategory(MyViewHolder holder) {
        if (isVisibleOf(holder.category)) {
            holder.category.setVisibility(View.GONE);
        }
    }


    private boolean isVisibleOf(View view) {
        return view.getVisibility() == View.VISIBLE;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView category;
        TextView title2;

        public MyViewHolder(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.tv_category);
            title2 = (TextView) itemView.findViewById(R.id.tv_title2);
        }
    }
}
