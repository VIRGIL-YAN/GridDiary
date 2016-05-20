package com.free.diary.support.app;

import android.content.Context;

import com.free.diary.R;
import com.free.diary.model.bean.Subject;
import com.free.diary.model.dao.SubjectDao;
import com.free.diary.support.config.Global;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangqi on 16/5/19.
 */
public class SubjectManager {
    private Context mContext;

    public SubjectManager(Context context) {
        this.mContext = context;
    }

    public void init() {
//        if (!SpfUtils.getBoolean(mContext, SpfConfig.IS_INIT_SUBJECT, false)) {
        List<Subject> subjectList = getSubjectList();
        SubjectDao subjectDao = new SubjectDao(mContext);
        subjectDao.delelteAll();
        subjectDao.insert(subjectList);

//            SpfUtils.putBoolean(mContext, SpfConfig.IS_INIT_SUBJECT, true);
//        }

    }

    public List<Subject> getSubjectList() {
        String[] questionArray = mContext.getResources().getStringArray(R.array.question_list);

        List<Subject> subjectList = new ArrayList<>();
        for (String question : questionArray) {
            Subject subject = new Subject();
            subject.setType(Global.SubjectType.APP);
            subject.setSubject(question);
            subjectList.add(subject);
        }

        return subjectList;
    }
}
