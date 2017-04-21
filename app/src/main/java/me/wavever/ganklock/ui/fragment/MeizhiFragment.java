package me.wavever.ganklock.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.bilibili.magicasakura.widgets.TintProgressBar;
import java.io.File;
import java.util.List;
import me.wavever.ganklock.R;
import me.wavever.ganklock.event.ClickEvent;
import me.wavever.ganklock.event.RxBus;
import me.wavever.ganklock.presenter.MeiZhiPresenter;
import me.wavever.ganklock.ui.activity.PhotoActivity;
import me.wavever.ganklock.ui.adapter.MeizhiRecyclerViewAdapter;
import me.wavever.ganklock.view.IMeiZhiView;
import rx.functions.Action1;

/**
 * Created by wavever on 2016/8/12.
 */
public class MeizhiFragment extends BaseFragment<IMeiZhiView, MeiZhiPresenter>
    implements IMeiZhiView {

    private static final String TAG = "MeizhiFragment-->";
    private static int sClickCount;
    private RecyclerView mRecyclerView;
    private MeizhiRecyclerViewAdapter mAdapter;
    private TextView mEmptyTip;
    private TintProgressBar mProgressBar;
    private List<File> mList;


    @Override
    protected int loadView() {
        return R.layout.fragment_meizhi;
    }


    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) mContext.findViewById(R.id.recycler_view_meizhi_fragment);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(manager);
        mEmptyTip = (TextView) mContext.findViewById(R.id.empty_tip_meizhi_fragment);
        mProgressBar = (TintProgressBar) mContext.findViewById(R.id.progress_bar_meizhi_fragment);
        mAdapter = new MeizhiRecyclerViewAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mProgressBar.setVisibility(View.VISIBLE);
        getPresenter().loadMeizhi();
        mSubscrition = RxBus.getInstance().tObservable(ClickEvent.class).subscribe(
            new Action1<ClickEvent>() {
                @Override public void call(ClickEvent clickEvent) {
                    if (clickEvent.eventType == ClickEvent.CLICK_TYPE_MEIZHI) {
                        Intent intent = new Intent(mContext, PhotoActivity.class);
                        intent.putExtra(PhotoActivity.KEY_ACTIVITY_JUMPED,
                            PhotoActivity.ACTIVITY_JUMPER_FROM_MEIZHI);
                        intent.putExtra(PhotoActivity.KEY_PHOTO_URL,
                            mList.get(clickEvent.position));
                        startActivity(intent);
                    }
                }
            });
    }


    @Override
    public MeiZhiPresenter createPresenter() {
        return new MeiZhiPresenter();
    }


    @Override public void showMeizhi(List<File> list) {
        mProgressBar.setVisibility(View.GONE);
        mEmptyTip.setVisibility(View.GONE);
        mList = list;
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }


    @Override public void showEmptyView() {
        mProgressBar.setVisibility(View.GONE);
        mEmptyTip.setVisibility(View.VISIBLE);
    }


    @Override public void showErrorView() {
        mProgressBar.setVisibility(View.GONE);
        mEmptyTip.setText("出问题了");
        mEmptyTip.setVisibility(View.VISIBLE);
    }


    @Override public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            sClickCount++;
            if (sClickCount % 2 == 0) {
                getPresenter().loadMeizhi();
            }
        }
    }
}
