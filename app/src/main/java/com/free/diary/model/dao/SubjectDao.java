package com.free.diary.model.dao;

import android.content.Context;

import com.free.diary.model.bean.Subject;
import com.free.diary.model.dao.base.DaoImpl;

/**
 * Created by tangqi on 16/5/19.
 */
public class SubjectDao extends DaoImpl<Subject> {

    public SubjectDao(Context context) {
        super(context, Subject.class);
    }
}
