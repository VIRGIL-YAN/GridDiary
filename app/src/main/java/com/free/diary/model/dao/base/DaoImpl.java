package com.free.diary.model.dao.base;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by tangqi on 16/5/18.
 */
public class DaoImpl<T> implements IDao<T> {

    protected Dao mDao;

    public DaoImpl(Context context, Class clazz) {
        try {
            mDao = DatabaseHelper.getHelper(context).getDao(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(T t) {
        try {
            mDao.createIfNotExists(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(List<T> list) {
        if (list == null) {
            return;
        }

        try {
            for (T t : list) {
                mDao.create(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T t) {
        try {
            mDao.update(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> queryAll() {
        try {
            return mDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> query(int pageNum, int pageSize) {
        // TODO
        return null;
    }

    @Override
    public void delete(T t) {
        try {
            mDao.delete(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delelteAll() {
        try {
            mDao.delete(queryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
