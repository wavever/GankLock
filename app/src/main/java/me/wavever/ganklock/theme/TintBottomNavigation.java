package me.wavever.ganklock.theme;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import com.bilibili.magicasakura.utils.TintManager;
import com.bilibili.magicasakura.widgets.AppCompatBackgroundHelper;
import com.bilibili.magicasakura.widgets.AppCompatBackgroundHelper.BackgroundExtensible;
import com.bilibili.magicasakura.widgets.Tintable;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * Created by wavever on 2016/10/10.
 */

public class TintBottomNavigation extends BottomNavigation implements Tintable,BackgroundExtensible{

    private AppCompatBackgroundHelper mBackgroundHelper;

    public TintBottomNavigation(Context context) {
        this(context, null);
    }

    public TintBottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            return;
        }
        TintManager mTintManager = TintManager.get(getContext());

        mBackgroundHelper = new AppCompatBackgroundHelper(this, mTintManager);
    }

    @Override public void setBackgroundTintList(int resId) {

    }


    @Override public void setBackgroundTintList(int resId, PorterDuff.Mode mode) {

    }


    @Override public void tint() {

    }
}
