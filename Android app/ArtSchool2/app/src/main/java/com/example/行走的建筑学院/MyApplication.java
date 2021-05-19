package com.example.行走的建筑学院;

import android.app.Application;

import com.lzy.okgo.OkGo;

import java.util.logging.Level;

public class MyApplication extends Application {
@Override
public void onCreate() {
super.onCreate();
    OkGo.init(this);
    try {
        OkGo.getInstance()
        .debug("OkGo", Level.INFO, true)
        .setConnectTimeout(60*1000)
        .setReadTimeOut(60*1000)
        .setWriteTimeOut(60*1000)
        .setRetryCount(3);
         }catch (Exception e)
    {
    e.printStackTrace();
     }
    }
}

