package com.example.thybrid.core;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by tory on 2018/1/30.
 * WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等比如
 * 如下接口
 */

public class TWebChromeClient extends WebChromeClient{
    public static final String TAG="webchrome";
    /**
     * 当H5页面调用js的Alert方法的时候回调该方法
     */
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    /**
     * 当加载到H5页面title的时候回调该方法
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }

    /**
     * 当webview加载进度变化时回调该方法
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    /**
     * 当H5页面调用js的Confirm方法的时候回调该方法
     */
    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    /**
     * 当H5页面调用js的Prompt方法的时候回调该方法
     */
    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
}
