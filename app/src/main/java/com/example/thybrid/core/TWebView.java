package com.example.thybrid.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.thybrid.util.Util;

/**
 * Created by tory on 2018/1/30.
 * 自己定位Webview
 */

public class TWebView extends WebView {
    public TWebView(Context context) {
        super(context);
        initWebView(context);
    }

    public TWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebView(context);
    }

    public TWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebView(context);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(Context context) {
        // FIXME: 2018/1/30 decode
        WebSettings webSettings = getSettings();
        //允许js调用
        webSettings.setJavaScriptEnabled(true);
        //设置webview推荐使用的窗口，使html界面自适应屏幕
        webSettings.setUseWideViewPort(false);
        //缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
//        设置允许访问本地文件 false
        webSettings.setAllowFileAccess(false);
        //设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDatabaseEnabled(false);
        webSettings.setAppCacheEnabled(false);
//        webSettings.setAppCachePath("");
        webSettings.setBlockNetworkImage(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //漏洞地址http://www.cnvd.org.cn/webinfo/show/4365?from=groupmessage
            //设置不允许通过file域url中的Javascript读取其他本地文件
            //4.1之前默认是开启的，4.1之后默认禁止
            //4.1之前设置这两个值没有用
            webSettings.setAllowFileAccessFromFileURLs(false);
            //设置不允许通过file域url中的Javascript访问其他的源
            webSettings.setAllowUniversalAccessFromFileURLs(false);
            Log.i(Util.TAG,"setAllowFileAccessFromFileURLs()...false");
        }
        setWebViewClient(new TWebViewClient());
        setWebChromeClient(new TWebChromeClient());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //do some init
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //do some clean
    }
}
