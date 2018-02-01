package com.example.thybrid;

import android.app.Application;

import com.chenenyu.router.RouteTable;

import java.util.Map;

/**
 * Created by tory on 2018/1/31.
 */

public class TApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SchemeEngine.initialize(this);
        SchemeEngine.handleRouteTable(new RouteTable() {
            @Override
            public void handle(Map<String, Class<?>> map) {
                map.putAll(SchemeEngine.getRouterMap());
            }
        });

    }
}
