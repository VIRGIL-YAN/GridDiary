package com.free.diary.support.util;

import android.app.Activity;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by tangqi on 16/5/26.
 */
public class ActivityManager {

    private static HashMap<String, Activity> activitys = new HashMap<String, Activity>();

    /**
     * 添加运行的Activity
     **/
    public static void addActivity(Activity activity) {
        activitys.put(activity.getClass().getName(), activity);
    }

    /**
     * 移出Activity
     **/
    public static void removeActivity(Activity activity) {
        removeActivity(activity.getClass().getName());
    }

    /**
     * 移除Activity
     **/
    public static void removeActivity(String className) {
        activitys.remove(className);
    }

    /**
     * 得到Activity
     **/
    public static Activity getActivity(String className) {
        return activitys.get(className);
    }

    /**
     * 关闭Activity
     **/
    public static void finishActivity(String className) {
        Activity activity = getActivity(className);
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 清空管理集合，并finish所有Activity
     **/
    public static void removeAllActivity() {
        Iterator<String> iterator = activitys.keySet().iterator();
        while (iterator.hasNext()) {
            activitys.get(iterator.next()).finish();
        }
        activitys.clear();
    }

    /**
     * 获得当前管理集合的大小
     **/
    public static int getActivitysLength() {
        return activitys.size();
    }

}
