package com.free.diary.model.dao.base;

import android.content.Context;

import com.free.diary.model.bean.Subject;

/**
 * Created by tangqi on 16/5/19.
 */
public class SubjectDao extends DaoImpl<Subject>{

    public SubjectDao(Context context) {
        super(context, Subject.class);
    }
}
