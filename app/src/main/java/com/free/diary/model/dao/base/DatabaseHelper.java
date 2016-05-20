package com.free.diary.model.dao.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.free.diary.model.bean.Diary;
import com.free.diary.model.bean.Grid;
import com.free.diary.model.bean.Subject;
import com.free.diary.support.test.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangqi on 16/5/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    public static final String DB_NAME = "grid_diary.db";
    public static final int DB_VERSION = 9;
    private static DatabaseHelper mInstance;
    private Map<String, Dao> mDaoMaps = new HashMap<>();

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 单例获取Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (mInstance == null) {
            synchronized (DatabaseHelper.class) {
                if (mInstance == null)
                    mInstance = new DatabaseHelper(context);
            }
        }

        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Diary.class);
            TableUtils.createTable(connectionSource, Grid.class);
            TableUtils.createTable(connectionSource, Subject.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int
            i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Diary.class, true);
            TableUtils.dropTable(connectionSource, Grid.class, true);
            TableUtils.dropTable(connectionSource, Subject.class, true);

            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public SQLiteDatabase getWritableDatabase() {
//        super.getWritableDatabase();
//        return SQLiteDatabase.openDatabase(DiaryApplication.getInstance().getDbPath(),
//                null,
//                SQLiteDatabase.OPEN_READWRITE);
//    }
//
//    @Override
//    public SQLiteDatabase getReadableDatabase() {
//        super.getReadableDatabase();
//        return SQLiteDatabase.openDatabase(DiaryApplication.getInstance().getDbPath(),
//                null,
//                SQLiteDatabase.OPEN_READONLY);
//    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao<Class, Integer> dao = null;
        String className = clazz.getSimpleName();
        if (mDaoMaps.containsKey(className)) {
            dao = mDaoMaps.get(className);
        }

        if (dao == null) {
            dao = super.getDao(clazz);
            mDaoMaps.put(className, dao);
        }

        return dao;
    }

    @Override
    public void close() {
        super.close();

        for (String key : mDaoMaps.keySet()) {
            Dao dao = mDaoMaps.get(key);
            dao = null;
        }
    }

    public void close(Class clazz) {
        super.close();

        String className = clazz.getSimpleName();
        if (mDaoMaps.containsKey(className)) {
            Dao dao = mDaoMaps.get(className);
            dao = null;
        }
    }
}
