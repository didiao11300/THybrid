package com.example.thybrid.core;

import android.webkit.JavascriptInterface;

/**
 * Created by tory on 2018/2/1.
 */

public interface AbsJsBridge {

    public void callJava(String action, String data);

    /**
     * @param callBack callBack 为js函数名称，且带一个String参数的
     * */
    public void callJava(String action, String data, String callBack);

    public void callJs(String js);

    public void callJs(String js, FuncCallBack callBack);
}
