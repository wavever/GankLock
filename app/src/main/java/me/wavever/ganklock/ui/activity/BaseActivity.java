package me.wavever.ganklock.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.bilibili.magicasakura.utils.ThemeUtils;
import me.wavever.ganklock.R;

/**
 * Created by waveverht on 2016/10/19.
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected Toolbar mToolbar;

    protected abstract int loadView();
    protected abstract void initView();


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(loadView());
        initToolbar();
        if (Build.VERSION.SDK_INT >= 21) {
            //设置statusbar的颜色
            getWindow().setStatusBarColor(ThemeUtils.getColorById(this, R.color.theme_color_primary_dark));
        }
        initView();
    }


    protected void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
