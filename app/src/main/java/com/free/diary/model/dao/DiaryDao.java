package com.free.diary.model.dao;

import android.content.Context;

import com.free.diary.model.dao.base.DaoImpl;
import com.free.diary.model.bean.Diary;

/**
 * Created by tangqi on 16/5/18.
 */
public class DiaryDao extends DaoImpl<Diary> {

    public DiaryDao(Context context) {
        super(context, Diary.class);
    }
}
