package me.wavever.ganklock.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bilibili.magicasakura.utils.ThemeUtils.ExtraRefreshable;
import me.wavever.ganklock.MyApplication;
import me.wavever.ganklock.R;
import me.wavever.ganklock.config.Config;
import me.wavever.ganklock.presenter.MorePresenter;
import me.wavever.ganklock.service.LockService;
import me.wavever.ganklock.theme.ThemeHelper;
import me.wavever.ganklock.ui.activity.AboutActivity;
import me.wavever.ganklock.ui.activity.LicenseActivity;
import me.wavever.ganklock.ui.activity.SettingActivity;
import me.wavever.ganklock.ui.fragment.CardPickerDialog.ClickListener;
import me.wavever.ganklock.utils.PreferenceUtil;
import me.wavever.ganklock.utils.StringUtil;
import me.wavever.ganklock.utils.ToastUtil;
import me.wavever.ganklock.view.IMoreView;

/**
 * Created by wavever on 2016/9/18.
 */

public class MoreFragment extends BaseFragment<IMoreView, MorePresenter>
    implements OnClickListener, ClickListener {

    private TextView mTitle;
    private SwitchCompat mSwitch;
    private TextView mItemLockSetting, mItemStyleSetting, mItemFeedBack, mItemOpenSource,
        mItemEvaluate, mItemAbout;
    private Toolbar mToolbar;


    @Override protected int loadView() {
        return R.layout.fragment_more;
    }


    @Override protected void initView() {
        mToolbar = (Toolbar) mContext.findViewById(R.id.toolbar_more_fragment);
        mTitle = (TextView) mContext.findViewById(R.id.more_fragment_title);
        mTitle.setTypeface(StringUtil.getTypeFace("jdjl.TTF"));
        mItemLockSetting = (TextView) mContext.findViewById(R.id.more_fragment_item_lock_setting);
        mItemLockSetting.setOnClickListener(this);
        mItemStyleSetting = (TextView) mContext.findViewById(R.id.more_fragment_item_style_setting);
        mItemStyleSetting.setOnClickListener(this);
        mItemFeedBack = (TextView) mContext.findViewById(R.id.more_fragment_item_feedback);
        mItemFeedBack.setOnClickListener(this);
        mItemOpenSource = (TextView) mContext.findViewById(R.id.more_fragment_item_open_source);
        mItemOpenSource.setOnClickListener(this);
        mItemEvaluate = (TextView) mContext.findViewById(R.id.more_fragment_item_evaluate);
        mItemEvaluate.setOnClickListener(this);
        mItemAbout = (TextView) mContext.findViewById(R.id.more_fragment_item_about);
        mItemAbout.setOnClickListener(this);
        mSwitch = (SwitchCompat) mContext.findViewById(R.id.more_fragment_switch_button);
        mSwitch.setChecked(PreferenceUtil.getBoolean(Config.GANK_LOCK_IS_OPEN));//根据存储的布尔值来判断是否是开启状态
        mSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ToastUtil.showToastShort(mContext, "open锁屏");
                    PreferenceUtil.putBoolean(Config.GANK_LOCK_IS_OPEN, true);
                    //LockManager.startLock(true);//立即显示锁屏
                    MyApplication.getContext().startService(new Intent(MyApplication.getContext(), LockService.class));
                } else {
                    ToastUtil.showToastShort(mContext, "close锁屏");
                    PreferenceUtil.putBoolean(Config.GANK_LOCK_IS_OPEN, false);
                    MyApplication.getContext().stopService(new Intent(MyApplication.getContext(), LockService.class));
                    //LockManager.cancleLock();
                }
            }
        });
    }


    @Override public MorePresenter createPresenter() {
        return new MorePresenter();
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more_fragment_item_lock_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.more_fragment_item_style_setting:
                CardPickerDialog dialog = new CardPickerDialog();
                dialog.setClickListener(this);
                dialog.show(getActivity().getFragmentManager(), CardPickerDialog.TAG);
                break;
            case R.id.more_fragment_item_feedback:
                ToastUtil.showToastShort(mContext,"意见反馈");
                break;
            case R.id.more_fragment_item_open_source:
                startActivity(new Intent(mContext, LicenseActivity.class));
                break;
            case R.id.more_fragment_item_evaluate:
                ToastUtil.showToastShort(mContext,"评价");
                break;
            case R.id.more_fragment_item_about:
                startActivity(new Intent(mContext, AboutActivity.class));
                break;
        }
    }

    @Override public void onConfirm(int currentTheme) {
        if (ThemeHelper.getTheme() != currentTheme) {
            ThemeHelper.setTheme(currentTheme);
            ThemeUtils.refreshUI(mContext, new ExtraRefreshable() {
                @Override public void refreshGlobal(Activity activity) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        mContext.getWindow().setStatusBarColor(ThemeUtils.getColorById(mContext,R.color.theme_color_primary_dark));
                    }
                }
                @Override public void refreshSpecificView(View view) {
                    mContext.findViewById(R.id.BottomNavigation)
                        .setBackgroundColor(
                            ThemeUtils.getColorById(mContext, R.color.theme_color_primary));
                }
            });
        }
    }
}
