package com.example.thybrid.core;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.thybrid.SchemeEngine;
import com.example.thybrid.util.Util;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Date;

import static com.example.thybrid.util.Util.TAG;

/**
 * Created by tory on 2018/1/30.
 * WebViewClient主要帮助WebView处理各种通知、请求事件的
 */

public class TWebViewClient extends WebViewClient {
    /**
     * 在webview加载URL的时候可以截获这个动作, 这里主要说它的返回值的问题：
     * 1、返回: return true;  webview处理url是根据程序来执行的。
     * 2、返回: return false; webview处理url是在webview内部执行。
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();
        setJsEnable(view, url);
        return handleUrl(view.getContext(), url);
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        setJsEnable(view, url);
        return handleUrl(view.getContext(), url);
    }

    private boolean handleUrl(Context context, String url) {
        if (url.contains(".3gp") || url.contains(".flv") || url.contains(".mp4")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            context.startActivity(intent);
            return true;
        } else if (Util.isNetUrl(url) && url.endsWith(".apk")) {
            //前往下载页面
            return true;
        } else if (url.contains("weixin://wap/pay")) {
            //微信支付页面
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context.getApplicationContext(), "请安装微信", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (SchemeEngine.couldHandleUrl(url)) {
            // FIXME: 2018/2/1 
            SchemeEngine.handleUrl(context, url,null);
            return true;
        } else if (url.contains("#") || Util.isNetUrl(url)) {
            //自己处理web页面
            return false;
        } else if (!Util.isNetUrl(url)) {
            try {
                //根据schme协议跳转
                Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setComponent(null);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                if (Build.VERSION.SDK_INT >= 15) {
                    intent.setSelector(null);
                }
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }


    @SuppressLint("SetJavaScriptEnable")
    private void setJsEnable(WebView view, String url) {
        //避免漏洞攻击
        /**
         * 1. 把恶意的 js 代码输出到攻击应用的目录下，随机命名为 xx.html，修改该目录的权限；
         * 2. 修改后休眠 1s，让文件操作完成；
         * 3. 完成后通过系统的 Chrome 应用去打开该 xx.html 文件
         * 4. 等待 4s 让 Chrome 加载完成该 html，最后将该 html 删除，并且使用 ln -s 命令为 Chrome 的 Cookie 文件创建软连接
         * */
        Log.i("tory_test", "shouldOverrideUrlLoading()...url:" + url);
        if (url.startsWith("file://")) {
            view.getSettings().setJavaScriptEnabled(false);
        } else {
            view.getSettings().setJavaScriptEnabled(true);
        }
    }

    /**
     * 开始加载页面回调
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.i("tory_test", "onPageStarted()...---->" + new Date());
        Log.i(TAG, "onPageStarted()...---->" + System.currentTimeMillis());
        Log.i(TAG, "onPageStarted()...url:" + url);
    }

    /**
     * 加载页面完成回调
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.i("tory_test", "onPageFinished()...---->" + new Date());
        Log.i(TAG, "onPageFinished()...---->" + System.currentTimeMillis());
        Log.i(TAG, "onPageFinished()...url:" + url);
    }

    /**
     * 页面加载失败回调 api23 才会收到
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        Log.e(TAG, "onPageFinished()...url:" + error.getDescription());
    }

    /**
     * 是否应该拦截请求，这里可以做本地匹配网络请求
     * 通过mustache来匹配模版
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return interceptRequest(view, request.getUrl().toString());
//        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//        return super.shouldInterceptRequest(view, url);
        return interceptRequest(view, url);
    }

    public WebResourceResponse interceptRequest(WebView view, String url) {
        WebResourceResponse response = null;
        if (url.contains("www.baidu.com")) {
            Log.i("tory_test", "URL:-> 原始URL是:" + url);
            try {
                AssetManager am = view.getResources().getAssets();
                String filename = "html/intercept_baidu.html";
                InputStream is = am.open(filename);
                response = new WebResourceResponse("text/html", "utf-8", is);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

}
