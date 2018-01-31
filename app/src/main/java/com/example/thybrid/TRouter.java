package com.example.thybrid;

import android.app.Activity;

import com.chenenyu.router.Router;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tory on 2018/1/31.
 */

public class TRouter extends Router {
    public static Map<String, Class<? extends Activity>> map = new HashMap<>();

    static {
        map.put("tory://activity/main", MainActivity.class);
        map.put("tory://activity/home", MainActivity.class);
    }

    public static boolean isRouter(String url) {
        for (String key : map.keySet()) {
            if (url.startsWith(key)) {
                return true;
            }
        }
        return false;
    }

}
