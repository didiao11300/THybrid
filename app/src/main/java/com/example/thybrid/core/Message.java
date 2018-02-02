package com.example.thybrid.core;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

/**
 * Created by tory on 2018/2/2.
 * java 到
 */
public class Message {
    //Java 回调函数的id
    String callbackId;
    //Java调用js函数 js的method
    String jsFunctionName;
    //Java 调用js函数 js的数据
    String data;
    //Js执行完了之后返回给Java的数据
    String rspData;

    public static final String CALLBACK_ID = "callbackId";
    public static final String JS_FUNCTION_NAME= "jsFunctionName";
    public static final String DATA = "data";
    public static final String RSP_DATA = "rspData";

    public String toJson(){
        String s = JSON.toJSONString(this);
        return s;
    }
}
