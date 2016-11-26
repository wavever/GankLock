package me.wavever.ganklock.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.WebViewPresenter;
import me.wavever.ganklock.view.IWebView;

import static android.view.View.GONE;

/**
 * Created by wavever on 2016/10/18.
 */

public class WebViewActivity extends BaseMvpActivity<IWebView, WebViewPresenter> implements IWebView {

    private static final String TAG = WebViewActivity.class.getSimpleName();
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_URL = "key_url";
    private ProgressBar mProgressBar;
    private WebView mWebView;
    private String mTitle;
    private String mUrl;


    @Override protected int loadView() {
        return R.layout.activity_web_view;
    }


    @Override protected void initView() {
        mUrl = getIntent().getStringExtra(KEY_URL);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_web_view);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);//支持JavaScript
        mWebView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress<=90){
                    if(mProgressBar.getVisibility()== GONE){
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }else{
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


    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
