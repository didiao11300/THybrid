package com.example.thybrid.core;

import android.webkit.JavascriptInterface;

/**
 * Created by tory on 2018/2/1.
 */

public interface IJsBridge {

    /**
     * action 和should
     */
    public void callJava(String action);

    public void callJs(String jsFuncName, String data);

    public void callJs(String jsFuncName, String data, FuncCallBack callBack);
}
