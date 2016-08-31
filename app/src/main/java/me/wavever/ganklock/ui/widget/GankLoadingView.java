package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wavever on 2016/8/30.
 */
public class GankLoadingView extends LinearLayout{

    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;

    public GankLoadingView(Context context) {
        this(context,null);
    }

    public GankLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GankLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI(){
        mTv1.setText("G");
        mTv2.setText("a");
        mTv3.setText("n");
        mTv4.setText("k");
    }

    private void startAnim(){

    }

    private void endAnim(){

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}

