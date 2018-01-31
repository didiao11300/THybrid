package com.example.thybrid;

import android.app.Application;

import com.chenenyu.router.RouteTable;
import com.chenenyu.router.Router;

import java.util.Map;

/**
 * Created by tory on 2018/1/31.
 */

public class TApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        TRouter.initialize(this);
        TRouter.handleRouteTable(new RouteTable() {
            @Override
            public void handle(Map<String, Class<?>> map) {
                map.putAll(TRouter.map);
            }
        });

    }
}
