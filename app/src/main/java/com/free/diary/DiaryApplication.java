package com.free.diary;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;

import com.free.diary.model.DatabaseHelper;

import java.io.File;

/**
 * Created by tangqi on 16/5/17.
 */
public class DiaryApplication extends Application {

    private static DiaryApplication mInstance;

    public static DiaryApplication getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

//        initOrmLite();
    }

    private void initOrmLite() {
        File file = new File(getDbPath());
        if (!file.exists()) {
            file.mkdirs();
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                    getDbPath(), null);
            DatabaseHelper orm;
            orm = DatabaseHelper.getHelper(this);
            orm.onCreate(db);
            db.close();

        } else {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                    getDbPath(), null);
            DatabaseHelper orm;

            try {
                orm = DatabaseHelper.getHelper(this);
                if (db.getVersion() != getPackageManager().getPackageInfo(getPackageName(), 0)
                        .versionCode) {
                    orm.onUpgrade(db, db.getVersion(), getPackageManager().getPackageInfo
                            (getPackageName(), 0).versionCode);
                }
                db.close();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getDbPath() {
        return DiaryApplication.getContext().getCacheDir().getPath() + "ormlite";
    }
}
