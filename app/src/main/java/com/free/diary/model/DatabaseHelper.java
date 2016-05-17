package com.free.diary.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.free.diary.model.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by tangqi on 16/5/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    public static final String DB_NAME = "test.db";
    public static final int DB_VERSION = 3;

    private static DatabaseHelper mInstance;
    private Dao<User, Integer> userDao;


    /**
     * 单例获取该Helper
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

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int
            i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);

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

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    @Override
    public void close() {
        super.close();
        userDao = null;
    }
}
