package me.wavever.ganklock.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.event.ClickEvent;
import me.wavever.ganklock.event.RxBus;
import me.wavever.ganklock.model.bean.GankDaily;
import me.wavever.ganklock.utils.StringUtil;

/**
 * Created by wavever on 2016/9/8.
 */
public class DailyListAdapter extends Adapter<DailyListAdapter.DailyListViewHolder> {

    private static final String TAG = DailyListAdapter.class.getSimpleName()+"-->";

    private List<GankDaily> mDatas;
    private Activity mContext;

    public DailyListAdapter(List<GankDaily> datas,Activity context) {
        mDatas = datas;
        mContext = context;
    }

    @Override
    public DailyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//设置为null会导致宽度高度失效--》parent
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_recycler_view,parent,false);
        return new DailyListViewHolder(view);
    }

    @Override
        public void onBindViewHolder(DailyListViewHolder holder, int position) {
        holder.gankDaily = mDatas.get(position);
        holder.title.setText(mDatas.get(position).title);
        holder.date.setText(StringUtil.convertDateNum(mDatas.get(position).publishedAt));
        Picasso.with(mContext).load(mDatas.get(position).content).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public class DailyListViewHolder extends RecyclerView.ViewHolder{

        GankDaily gankDaily;
        ImageView img;
        TextView title;
        TextView date;
        ImageView newTag;

        public DailyListViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.daily_item_image);
            img.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    RxBus.getInstance().post(new ClickEvent(ClickEvent.CLICK_TYPE_DAILY_PHOTO,gankDaily));
                }
            });
            date = (TextView) itemView.findViewById(R.id.daily_item_date);
            title = (TextView) itemView.findViewById(R.id.daily_item_title);
            title.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    RxBus.getInstance().post(new ClickEvent(ClickEvent.CLICK_TYPE_DAILY_TITLE,gankDaily));
                }
            });
            newTag = (ImageView) itemView.findViewById(R.id.daily_item_new_tag);
        }

    }




}
