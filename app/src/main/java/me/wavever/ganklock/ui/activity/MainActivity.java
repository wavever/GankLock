package me.wavever.ganklock.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import me.wavever.ganklock.R;
import me.wavever.ganklock.ui.fragment.DailyGankFragment;
import me.wavever.ganklock.ui.fragment.SettingFragment;
import me.wavever.ganklock.util.ToastUtil;

/**
 * Created by wavever on 2016/5/28.
 */
public class MainActivity extends AppCompatActivity implements BottomNavigation.OnMenuItemSelectionListener{

    private BottomNavigation mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(R.string.app_name);
        mBottomNavigation = (BottomNavigation) findViewById(R.id.BottomNavigation);
        if (null != mBottomNavigation) {
            mBottomNavigation.setOnMenuItemClickListener(this);
        }
        //replaceFragment(R.id.main_activity_container,new DailyGankFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting) {
            Intent intent = new Intent(this,
                    SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    long time = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time > 2000) {
            ToastUtil.showToastShort(this,R.string.exit_with_two_click);
            time = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onMenuItemSelect(int itemId, int position) {
       /* if(itemId == R.id.bbn_more){
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.main_activity_container, new SettingFragment()).commit();
        }else if(itemId == R.id.bbn_daliy){
            setTitle("干货");
            replaceFragment(R.id.main_activity_container,new DailyGankFragment());
        }*/
    }

    //双击
    @Override
    public void onMenuItemReselect(int itemId, int position) {

    }

    public void replaceFragment(int resId,Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(resId, fragment).commit();
    }
}
