package me.wavever.ganklock.ui.activity;

import android.R.id;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bilibili.magicasakura.widgets.TintProgressBar;
import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.WebViewPresenter;
import me.wavever.ganklock.utils.ToastUtil;
import me.wavever.ganklock.view.IWebView;

import static android.view.View.GONE;

/**
 * Created by wavever on 2016/10/18.
 */

public class WebViewActivity extends BaseMvpActivity<IWebView, WebViewPresenter>
    implements IWebView {

    private static final String TAG = WebViewActivity.class.getSimpleName();
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_URL = "key_url";
    private TintProgressBar mProgressBar;
    private WebView mWebView;
    private String mTitle;
    private String mUrl;


    @Override protected int loadView() {
        return R.layout.activity_web_view;
    }


    @Override protected void initView() {
        mUrl = getIntent().getStringExtra(KEY_URL);
        mTitle = getIntent().getStringExtra(KEY_TITLE);
        setTitle(mTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProgressBar = (TintProgressBar) findViewById(R.id.progress_bar_web_view);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);//支持JavaScript
        mWebView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress <= 90) {
                    if (mProgressBar.getVisibility() == GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                } else {
                    mProgressBar.setVisibility(GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        mWebView.loadUrl(mUrl);
    }


    @Override public WebViewPresenter createPresenter() {
        return new WebViewPresenter();
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.web_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case id.home:
                onBackPressed();
                break;
            /*case R.id.action_webview_share:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,mTitle+mUrl);
                startActivity(Intent.createChooser(intent, "分享到"));
                break;
            case R.id.action_webview_refresh:
                mWebView.reload();
                break;
            case R.id.action_webview_copy_url:
                ClipboardManager copy = (ClipboardManager) getSystemService(
                    Context.CLIPBOARD_SERVICE);
                copy.setText(mUrl);
                ToastUtil.showToastShort(this, "复制成功");
                break;
            case R.id.action_webview_open_browser:
                mWebView.setWebViewClient(null);
                mWebView.loadUrl(mUrl);
                break;*/
        }
        return true;
    }
}
