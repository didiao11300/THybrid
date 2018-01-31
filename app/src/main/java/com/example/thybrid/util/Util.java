package com.example.thybrid.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/**
 * Created by tory on 2018/1/30.
 */

public class Util {
    public static final String TAG = "tory_test";

    /**
     * 将 数据流 InputStream 包装成 ByteArrayInputStream, 可以让 InputStream 多次使用
     *
     * @param input 输入 InputStream
     * @return 返回 ByteArrayInputStream
     */
    public static boolean isHttpUrl(String url) {
        return (null != url) && (url.length() > 6) && url.substring(0, 7).equalsIgnoreCase("http://");
    }

    public static boolean isHttpsUrl(String url) {
        return (null != url) && (url.length() > 7) && url.substring(0, 8).equalsIgnoreCase("https://");
    }

    /**
     * 是网络url
     * */
    public static boolean isNetUrl(String url) {
        return (isHttpUrl(url) || isHttpsUrl(url));
    }

}
