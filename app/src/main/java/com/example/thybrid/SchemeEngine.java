package com.example.thybrid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.chenenyu.router.Router;
import com.example.thybrid.core.FuncCallBack;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tory on 2018/1/31.
 */

public class SchemeEngine extends Router {
    private static Map<String, Class<? extends Activity>> mMapRouter = new HashMap<>();

    public static Map getRouterMap() {
        return mMapRouter;
    }

    public static final String HYBRID_SCHEME = "hybrid://";
    //处理java调用js后的回调path是methodid 参数是?data=
    public static final String HYBRID_JAVA_CALL_JS_RETURE = "hybrid://callback/";
    public static final String HYBRID_JAVA_CALL_JS_PARAM = "data";

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
        if (url.startsWith(HYBRID_SCHEME)) {
            return true;
        }
        return false;
    }

    //是否是java调用js之后的回调函数
    public static boolean containReturn(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        if (url.startsWith(HYBRID_JAVA_CALL_JS_RETURE)) {
            return true;
        }
        return false;
    }

    static {
        mMapRouter.put(HYBRID_ACTIVITY_MAIN, MainActivity.class);
        mMapRouter.put(HYBRID_ACTIVITY_HOME, HomeActivity.class);
        mMapRouter.put(HYBRID_ACTIVITY_H5, H5Activity.class);
    }

    public static boolean couldRouter(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        for (String key : mMapRouter.keySet()) {
            if (url.startsWith(key)) {
                return true;
            }
        }
        return false;
    }

    public static void handleUrl(Context context, String url) {
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
