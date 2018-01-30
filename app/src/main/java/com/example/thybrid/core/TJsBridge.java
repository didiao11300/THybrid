package com.example.thybrid.core;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by tory on 2018/1/30.
 * Tory JsBridge 实现java 和js通信的桥
 */
public class TJsBridge {
    private Context context;

    public TJsBridge(Context context) {
        if (null != context) {
            this.context = context.getApplicationContext();
        }
    }

    public void request() {
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
}
