package com.free.diary.model.dao;

import android.content.Context;

import com.free.diary.model.dao.base.DaoImpl;
import com.free.diary.model.bean.Diary;

import java.sql.SQLException;

/**
 * Created by tangqi on 16/5/18.
 */
public class DiaryDao extends DaoImpl<Diary> {

    public DiaryDao(Context context) {
        super(context, Diary.class);
    }

    public Diary query(String date) {
        try {
            return (Diary) mDao.queryBuilder().where().eq("date", date).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
