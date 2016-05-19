package com.free.diary.support.config;

import android.content.Context;

import com.free.diary.DiaryApplication;

/**
 * 全局配置
 * Created by tangqi on 16/5/17.
 */
public class Global {

    public static Context getContext(){
        return DiaryApplication.getContext();
    }

    public class SubjectType{
        public static final String APP = "APP";
    }
}
