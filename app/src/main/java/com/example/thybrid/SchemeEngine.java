package com.example.thybrid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.chenenyu.router.Router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tory on 2018/1/31.
 */

public class SchemeEngine extends Router {
    private static Map<String, Class<? extends Activity>> routerMap = new HashMap<>();
    private static List<String> schemeMap = new ArrayList<>();

    public static Map getRouterMap(){
        return routerMap;
    }
    //处理android的跳转
    public static final String HYBRID_ACTIVITY_MODULE = "hybrid://activity/";
    public static final String HYBRID_ACTIVITY_MAIN = "hybrid://activity/main";
    public static final String HYBRID_ACTIVITY_HOME = "hybrid://activity/home";
    public static final String HYBRID_ACTIVITY_H5 = "hybrid://activity/h5";


    //处理各种action的对话框toast和定位和文件浏览
    public static final String HYBRID_ACTION_MODULE = "hybrid://action/";
    public static final String HYBRID_ACTION_DIALOG = "hybrid://action/dialog";
    public static final String HYBRID_ACTION_TOAST = "hybrid://action/toast";
    public static final String HYBRID_ACTION_LOCATION = "hybrid://action/location";
    public static final String HYBRID_ACTION_FILE = "hybrid://action/file";

    //处理浏览器本身增强
    public static final String HYBRID_BROWSE_MODULE = "hybrid://browse/";
    public static final String HYBRID_BROWSE_BACK = "hybrid://browse/back";
    public static final String HYBRID_BROWSE_FORWARD = "hybrid://browse/forward";
    public static final String HYBRID_BROWSE_SAVE_COOKIE = "hybrid://browse/save_cookie";
    public static final String HYBRID_BROWSE_CLEAR_COOKIE = "hybrid://browse/clear_cookie";
    public static final String HYBRID_BROWSE_POST = "hybrid://browse/post";
    public static final String HYBRID_BROWSE_GET = "hybrid://browse/get";


    public static boolean couldHandleUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        for (String value : schemeMap) {
            if (url.startsWith(value)) {
                return true;
            }
        }
        return false;
    }

    static {
        routerMap.put(HYBRID_ACTIVITY_MAIN, MainActivity.class);
        routerMap.put(HYBRID_ACTIVITY_HOME, HomeActivity.class);
        routerMap.put(HYBRID_ACTIVITY_H5, H5Activity.class);
    }

    public static boolean couldRouter(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        for (String key : routerMap.keySet()) {
            if (url.startsWith(key)) {
                return true;
            }
        }
        return false;
    }

    public static void handleUrl(Context context, String url, String data) {
        Uri uri = Uri.parse(url);
        String scheme = uri.getScheme();
        String host = uri.getHost();
        String path = uri.getPath();
        if (host.equalsIgnoreCase("activity")) {
            //自己的路由地址
            SchemeEngine.build(url).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).go(context);
        } else if (host.equalsIgnoreCase("action")) {
            switch (path) {
                case "toast":
                    break;
                case "dialog":
                    break;
                case "location":
                    break;
                case "file":
                    break;
                default:
                    break;
            }
        } else if (host.equalsIgnoreCase("browse")) {

        }
    }

}
