package me.wavever.ganklock.keyguard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import me.wavever.ganklock.R;
import me.wavever.ganklock.utils.DateUtil;

/**
 * Created by wavever on 2016/10/9.
 */

public class LockView extends FrameLayout{

    private TextView mLockViewDate;
    private Button mBtn;

    public LockView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_lock,this);
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        mLockViewDate = (TextView) findViewById(R.id.lock_view_date);
        mLockViewDate.setText(DateUtil.getLockDateText());
        mBtn = (Button) findViewById(R.id.lock_view_btn);
        mBtn.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                LockManager.removeLockView();
            }
        });
    }


}
