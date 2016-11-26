package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by waveverht on 2016/10/20.
 */

public class LoadMoreLayout extends LinearLayout{

    private RecyclerView mRecyclerView;
    private ImageView mArrow;
    private TextView mTip;
    private ProgressBar mProgressBar;
    private FrameLayout mLoadMoreLayout;

    public LoadMoreLayout(Context context) {
        this(context,null);
    }


    public LoadMoreLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public LoadMoreLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        
    }
}
