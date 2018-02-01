package com.example.thybrid.core;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tory on 2018/1/30.
 * Tory JsBridge 实现java 和js通信的桥
 */
public class TJsBridge implements AbsJsBridge {
    private Context context;
    private WebView webView;

    public TJsBridge(WebView webView) {
        this.webView = webView;
        this.context = webView.getContext().getApplicationContext();
    }

    /**
     * 1，当JS拿到Android这个对象后，就可以调用这个Android对象中所有的方法，
     * 包括系统类（java.lang.Runtime 类），从而进行任意代码执行
     * 2，4.2之前
     */
    //android 4.2之后才有这个注解
    @JavascriptInterface
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface //这个annote不能继承
    @Override
    public void callJava(String action, String data) {
        handle(action, data, null);
    }

    @JavascriptInterface //这个annote不能继承
    @Override
    public void callJava(String action, String data, String jscallback) {
        handle(action, data, jscallback);
    }

    @Override
    public void callJs(String js) {

    }

    @Override
    public void callJs(String js, FuncCallBack callBack) {

    }

    private void handle(String action, String data, String jscallBack) {
        Uri parse = Uri.parse(action);
        String scheme = parse.getScheme();
        try {
            JSONObject jb = new JSONObject(data);
            Toast.makeText(context, jb.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadJs(jscallBack,"'成功'");

    }

    public void loadJs(final String jsmethod, final String data) {
        // 必须要找主线程才会将数据传递出去 --- 划重点
//        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
//            webView.loadUrl(javascriptCommand);

        webView.post(new Runnable() {
            @Override
            public void run() {
                String javascriptCommand = String.format("javascript:%s(%s)", jsmethod, data);
                webView.loadUrl(javascriptCommand);
            }
        });

//        }
    }
}
